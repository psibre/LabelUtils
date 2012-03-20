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

public class PraatBinaryFile extends PraatFile {

	public PraatObject read(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readString() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int readInteger() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double readDouble() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PraatObject readPayLoad() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeString(String decorator, String value) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeInteger(String decorator, int value) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeDouble(String decorator, double value) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeLine(String format, Object... args) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseIndent() {
		// meaningless for binary files
	}

	@Override
	public void decreaseIndent() {
		// meaningless for binary files
	}

}
