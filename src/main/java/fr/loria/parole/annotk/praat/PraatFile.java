package fr.loria.parole.annotk.praat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

import com.google.common.io.Files;
import com.google.common.io.Resources;

abstract public class PraatFile {

	public static PraatObject read(String resource) throws IOException, ClassNotFoundException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		return read(resource, Charset.defaultCharset());
	}

	public static PraatObject read(String resource, Charset charset) throws IOException, ClassNotFoundException,
			NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
			InvocationTargetException {
		// get path from resource
		String path;
		try {
			path = Resources.getResource(resource).getPath();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Resource not found: " + resource);
		}

		// inspect file reading first line
		File file = new File(path);
		String firstLine;
		try {
			firstLine = Files.readFirstLine(file, charset);
		} catch (IOException e) {
			throw new IOException("File not readable: " + file);
		}

		// file must not be empty
		if (firstLine == null) {
			throw new IllegalArgumentException("File is empty: " + file);
		}

		// determine whether this is a text or binary file and return instance of corresponding subclass
		if (firstLine.contains("ooTextFile")) {
			PraatTextFile praatFile = new PraatTextFile();
			return praatFile.read(file, charset);
		} else if (firstLine.equals("ooBinaryFile")) {
			PraatBinaryFile praatFile = new PraatBinaryFile();
			return praatFile.read(file);
		} else {
			throw new IllegalArgumentException("Not a Praat file: " + file);
		}
	}

	abstract String readString() throws IOException;

	abstract int readInteger() throws IOException;

	abstract double readDouble() throws IOException;

}
