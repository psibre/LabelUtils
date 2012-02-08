package fr.loria.parole.annotk.praat;

import java.io.IOException;
import java.nio.charset.Charset;

public class PraatTextFile extends PraatFile {

	public PraatTextFile(File file) throws IOException {
		this(file, Charset.defaultCharset());
	}

	public PraatTextFile(File file, Charset charset) throws IOException {
	}

}
