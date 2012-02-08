package fr.loria.parole.annotk.praat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;

public class PraatFile {

	protected InputSupplier<?> inputSupplier;

	public PraatFile(String resource) throws IOException {
		this(resource, Charset.defaultCharset());
	}

	public PraatFile(String resource, Charset charset) throws IOException {
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

		// determine whether this is a text or binary file
		if (firstLine.contains("ooTextFile")) {
			readText(file, charset);
		} else if (firstLine.equals("ooBinaryFile")) {
			readBinary(file);
		} else {
			throw new IllegalArgumentException("Not a Praat file: " + file);
		}
	}

	private void readText(File file, Charset charset) throws IOException {
		inputSupplier = Files.newReaderSupplier(file, charset);
	}

	private void readBinary(File file) throws IOException {
		inputSupplier = Files.newInputStreamSupplier(file);
	}

}
