package fr.loria.parole.annotk.praat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

import jregex.Matcher;
import jregex.Pattern;

import com.google.common.io.Files;

public class PraatTextFile extends PraatFile {

	private BufferedReader reader;

	public PraatObject read(File file) throws IOException, ClassNotFoundException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		return read(file, Charset.defaultCharset());
	}

	public PraatObject read(File file, Charset charset) throws IOException, ClassNotFoundException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		reader = Files.newReader(file, charset);
		reader.readLine(); // discard first line

		// determine payload class
		String classString = readString();

		// use reflection to create payload instance
		String packageName = getClass().getPackage().getName();
		String fullyQuallifiedClassString = packageName + "." + classString;
		Class<?> praatClass;
		try {
			praatClass = Class.forName(fullyQuallifiedClassString);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Unsupported Praat class: " + classString);
		}
		Class<?> superClass = getClass().getSuperclass();
		Constructor<?> constructor = praatClass.getConstructor(superClass);
		PraatObject payload;
		try {
			payload = (PraatObject) constructor.newInstance(this);
		} catch (ClassCastException e) {
			throw new ClassCastException("");
		}
		return payload;
	}

	@Override
	public String readString() throws IOException {
		// get string from buffer
		Pattern pattern = new Pattern("\"(.+)\"");
		String target;
		try {
			target = readPattern(pattern);
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
		Pattern pattern = new Pattern("(\\d+)");
		String target = null;
		try {
			target = readPattern(pattern);
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
		Pattern pattern = new Pattern("(\\d+(\\.\\d+)?)");
		String target = null;
		try {
			target = readPattern(pattern);
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
		// read line from BufferedReader
		String line = reader.readLine();

		// match pattern to line
		Matcher matcher = pattern.matcher(line);

		// find pattern in line
		if (!matcher.find()) {
			throw new IllegalArgumentException("Could not find expected pattern /" + pattern + "/ in " + line);
		}

		// get match target
		String target = null;
		try {
			target = matcher.group(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Pattern /" + pattern + "/ does not contain required group");
		}
		return target;
	}
}
