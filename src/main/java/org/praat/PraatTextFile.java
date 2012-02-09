package fr.loria.parole.annotk.praat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;

import jregex.Matcher;
import jregex.Pattern;

import com.google.common.io.Files;

public class PraatTextFile extends PraatFile {

	private BufferedReader reader;

	private static Pattern STRING_PATTERN = new Pattern("\"({target}.+)\"");
	private static Pattern INTEGER_PATTERN = new Pattern("(?!\\[)({target}\\d+)(?!\\])");
	private static Pattern DOUBLE_PATTERN = new Pattern("(?!\\[)({target}\\d+(\\.\\d+)?)(?!\\])");

	public PraatObject read(File file) throws Exception {
		return read(file, Charset.defaultCharset());
	}

	public PraatObject read(File file, Charset charset) throws Exception {
		reader = Files.newReader(file, charset);
		reader.readLine(); // discard first line

		// determine payload class (and ignore missing name)
		String className = readString();
		return readPayload(className);
	}

	@Override
	public PraatObject readPayLoad() throws Exception {
		// determine payload class
		String className = readString();

		// read name
		String name = readString();

		// read payload
		PraatObject object = readPayload(className);
		object.setName(name);
		return object;
	}

	private PraatObject readPayload(String className) throws Exception {
		// use reflection to create payload instance
		String packageName = getClass().getPackage().getName();
		String fullyQuallifiedClassString = packageName + "." + className;
		Class<?> praatClass;
		try {
			praatClass = Class.forName(fullyQuallifiedClassString);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Unsupported Praat class: " + className);
		}
		Class<?> superClass = getClass().getSuperclass();
		Constructor<?> constructor = praatClass.getConstructor(superClass);
		PraatObject payload;
		try {
			payload = (PraatObject) constructor.newInstance(this);
		} catch (ClassCastException e) {
			throw new ClassCastException("Could not cast " + praatClass + " to PraatObject");
		}
		return payload;
	}

	@Override
	public String readString() throws IOException {
		// get string from buffer
		String target;
		try {
			target = readPattern(STRING_PATTERN);
		} catch (IOException e) {
			throw new IOException("Could not read from buffer");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Could not read string from buffer");
		}
		return target;
	}

	@Override
	public int readInteger() throws IOException {
		// get integer string from buffer
		String target = null;
		try {
			target = readPattern(INTEGER_PATTERN);
		} catch (IOException e) {
			throw new IOException("Could not read from buffer");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Could not read integer from buffer");
		}

		// parse integer
		int targetInt;
		try {
			targetInt = Integer.parseInt(target);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Could not parse integer from string: " + target);
		}
		return targetInt;
	}

	@Override
	public double readDouble() throws IOException {
		// get double string from buffer
		String target = null;
		try {
			target = readPattern(DOUBLE_PATTERN);
		} catch (IOException e) {
			throw new IOException("Could not read from buffer");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Could not read double from buffer");
		}

		// parse double
		double targetDouble;
		try {
			targetDouble = Double.parseDouble(target);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Could not parse double from string: " + target);
		}
		return targetDouble;
	}

	private String readPattern(Pattern pattern) throws IOException {
		String line;
		// keep reading line from BufferedReader until we match
		while ((line = reader.readLine()) != null) {

			// match pattern to line
			Matcher matcher = pattern.matcher(line);

			// find pattern in line
			if (!matcher.find()) {
				continue; // read next line if pattern not found
			}

			// get match target
			String target = null;
			try {
				target = matcher.group("target");
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Pattern /" + pattern + "/ does not contain required group");
			}
			return target;
		}
		throw new IllegalArgumentException("End of buffer reached without finding pattern /" + pattern + "/");
	}
}
