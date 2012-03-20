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

public class PraatShortTextFile extends PraatTextFile {

	public PraatShortTextFile(File file) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void writeDouble(String descriptor, double value) throws IOException {
		writeLine(number.format(value));
	}

}
