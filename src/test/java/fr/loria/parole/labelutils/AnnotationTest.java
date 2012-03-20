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
package fr.loria.parole.labelutils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.praat.PraatFile;
import org.praat.TextGrid;

public class AnnotationTest {

	private TextGrid textGrid;

	@Before
	public void setUp() throws Exception {
		textGrid = (TextGrid) PraatFile.read("test.UTF-8.TextGrid");
	}

	@Test
	public void createFromTextGrid() throws Exception {
		Annotation annotation = new Annotation(textGrid);
		assertNotNull(annotation);
	}

	@Test
	public void testConversion() {
		Annotation annotation = new Annotation(textGrid);
		assertEquals(textGrid, annotation.toTextGrid());
	}

}
