package org.praat;

import java.io.File;
import java.io.IOException;

public class PraatShortTextFile extends PraatTextFile {

	public PraatShortTextFile(File file) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void writeDouble(String descriptor, double value) throws IOException {
		writeLine(number.format(value));
	}

}
