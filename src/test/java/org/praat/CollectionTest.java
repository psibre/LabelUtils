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

import org.junit.Before;
import org.junit.Test;

public class CollectionTest {

	private PraatObject utf8Collection;
	private PraatObject utf8ShortCollection;

	@Before
	public void setUp() throws Exception {
		utf8Collection = PraatFile.read("test.UTF-8.Collection");
		utf8ShortCollection = PraatFile.read("test.UTF-8.short.Collection");
	}

	@Test
	public void compareFormats() {
		assertThat(utf8Collection).isEqualTo(utf8ShortCollection);
	}

}
