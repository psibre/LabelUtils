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

import static org.fest.assertions.api.Assertions.*;

import java.io.File;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.praat.PraatTextFile.EOL;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

public class IntervalTierTest {

	private PraatObject utf8IntervalTier;
	private PraatObject utf8ShortIntervalTier;

	@Rule
	public TemporaryFolder tempDir = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		utf8IntervalTier = PraatFile.read("test.UTF-8.IntervalTier");
		utf8ShortIntervalTier = PraatFile.read("test.UTF-8.short.IntervalTier");
	}

	@Test
	public void compareFormats() {
		assertThat(utf8IntervalTier).isEqualTo(utf8ShortIntervalTier);
	}

	private void compareTextIO(String resource, Charset charset, EOL eol) throws Exception {
		compareTextIO(resource, charset, eol, false);
	}

	private void compareTextIO(String resource, Charset charset, EOL eol, boolean shortFormat) throws Exception {
		File expected = new File(Resources.getResource(resource).getPath());
		File actual = tempDir.newFile();
		PraatObject object = PraatFile.read(resource, charset);
		if (shortFormat == true) {
			PraatFile.writeShortText(object, actual, charset, eol);
		} else {
			PraatFile.writeText(object, actual, charset, eol);
		}
		assertThat(Files.equal(actual, expected)).isTrue();
	}

	@Test
	public void compareTextIO_ISO8859_WINDOWS() throws Exception {
		compareTextIO("test.ISO-8859.CRLF.IntervalTier", Charsets.ISO_8859_1, EOL.WINDOWS);
	}

	@Test
	public void compareTextIO_UTF8_UNIX() throws Exception {
		compareTextIO("test.UTF-8.IntervalTier", Charsets.UTF_8, EOL.UNIX);
	}

	@Test
	public void compareTextIO_UTF16_UNIX() throws Exception {
		compareTextIO("test.UTF-16.IntervalTier", Charsets.UTF_16, EOL.UNIX);
	}

	@Test
	public void compareShortTextIO_ISO8859_WINDOWS() throws Exception {
		compareTextIO("test.ISO-8859.CRLF.short.IntervalTier", Charsets.ISO_8859_1, EOL.WINDOWS, true);
	}

	@Test
	public void compareShortTextIO_UTF8_UNIX() throws Exception {
		compareTextIO("test.UTF-8.short.IntervalTier", Charsets.UTF_8, EOL.UNIX, true);
	}

	@Test
	public void compareShortTextIO_UTF16_UNIX() throws Exception {
		compareTextIO("test.UTF-16.short.IntervalTier", Charsets.UTF_16, EOL.UNIX, true);
	}

}
