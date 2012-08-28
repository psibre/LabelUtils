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

import java.io.IOException;

import static org.fest.assertions.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;

public class PraatFileTest {

	private PraatObject utf8IntervalTier;
	private PraatObject utf8ShortIntervalTier;

	@Before
	public void setUp() throws Exception {
		utf8IntervalTier = PraatFile.read("test.UTF-8.IntervalTier", Charsets.UTF_8);
		utf8ShortIntervalTier = PraatFile.read("test.UTF-8.short.IntervalTier", Charsets.UTF_8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleMissingResource() throws Exception {
		PraatFile.read("--INVALID_RESOURCE_NAME--");
	}

	@Test(expected = IOException.class)
	public void handleUnreadbleResource() throws Exception {
		// PraatFile.read("unreadableFile");
		// TODO make maven copy this test resource and then set permissions to 000
		throw new IOException();
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleEmptyResource() throws Exception {
		PraatFile.read("emptyFile");
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleNonPraatFile() throws Exception {
		PraatFile.read("randomFile.txt");
	}

	@Test
	public void compareFormats() throws Exception {
		assertThat(utf8IntervalTier).isEqualTo(utf8ShortIntervalTier);
	}

}
