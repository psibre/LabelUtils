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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.praat.PraatTextFile.EOL;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

public class TextTierTest {

	private PraatObject utf8TextTier;
	private PraatObject utf8ShortTextTier;

	@Rule
	public TemporaryFolder tempDir = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		utf8TextTier = PraatFile.read("test.UTF-8.TextTier");
		utf8ShortTextTier = PraatFile.read("test.UTF-8.short.TextTier");
	}

	@Test
	public void compareFormats() {
		assertThat(utf8TextTier, is(equalTo(utf8ShortTextTier)));
	}

	@Test
	public void compareTextIO() throws Exception {
		String resource = "test.UTF-8.TextTier";
		File expected = new File(Resources.getResource(resource).getPath());
		File actual = tempDir.newFile();
		PraatObject object = PraatFile.read(resource, Charsets.UTF_8);
		PraatFile.writeText(object, actual, Charsets.UTF_8, EOL.UNIX);
		assertThat(Files.equal(actual, expected), is(true));
	}

}
