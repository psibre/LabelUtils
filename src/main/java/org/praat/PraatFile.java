/*
 * #%L
 * LabelUtils
 * %%
 * Copyright (C) 2012 INRIA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.praat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.praat.PraatTextFile.EOL;

import com.google.common.io.Files;
import com.google.common.io.Resources;

abstract public class PraatFile {

	public static PraatObject read(String resource) throws Exception {
		return read(resource, Charset.defaultCharset());
	}

	public static PraatObject read(String resource, Charset charset) throws Exception {
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
			PraatTextFile textFile = new PraatTextFile();
			return textFile.read(file, charset);
		} else if (firstLine.equals("ooBinaryFile")) {
			PraatBinaryFile binaryFile = new PraatBinaryFile();
			return binaryFile.read(file);
		} else {
			throw new IllegalArgumentException("Not a Praat file: " + file);
		}
	}

	abstract public String readString() throws IOException;

	abstract public int readInteger() throws IOException;

	abstract public double readDouble() throws IOException;

	abstract public PraatObject readPayLoad() throws Exception;

	public static void writeText(PraatObject object, File file) throws IOException {
		writeText(object, file, Charset.defaultCharset());
	}

	public static void writeText(PraatObject object, File file, Charset charset) throws IOException {
		writeText(object, file, charset, EOL.WINDOWS);
	}

	public static void writeText(PraatObject object, File file, Charset charset, EOL eol) throws IOException {
		PraatTextFile textFile = new PraatTextFile(file, charset, eol);
		textFile.write(object);
	}

	public static void writeShortText(PraatObject object, File file) throws IOException {
		writeShortText(object, file, Charset.defaultCharset());
	}

	public static void writeShortText(PraatObject object, File file, Charset charset) throws IOException {
		writeShortText(object, file, charset, EOL.WINDOWS);
	}

	public static void writeShortText(PraatObject object, File file, Charset charset, EOL eol) throws IOException {
		PraatShortTextFile shortTextFile = new PraatShortTextFile(file, charset, eol);
		shortTextFile.write(object);
	}

	public static void writeBinary(PraatObject object, File file) {
		// TODO Auto-generated method stub
	}

	abstract public void writeString(String decorator, String value) throws IOException;

	abstract public void writeInteger(String decorator, int value) throws IOException;

	abstract public void writeDouble(String decorator, double value) throws IOException;

	abstract public void writeLine(String format, Object... args) throws IOException;

	abstract public void increaseIndent();

	abstract public void decreaseIndent();

}
