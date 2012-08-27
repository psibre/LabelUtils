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

import static org.fest.assertions.api.Assertions.*;

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
		assertThat(annotation).isNotNull();
	}

	@Test
	public void testConversion() {
		Annotation annotation = new Annotation(textGrid);
		assertThat(textGrid).isEqualTo(annotation.toTextGrid());
	}

	@Test
	public void testAppend() {
		// two identical TextGrids
		Annotation first = new Annotation(textGrid);
		Annotation second = new Annotation(textGrid);
		// append second to the end of first
		first.append(second);
		// first TextGrid's first Layer should now contain twice as many Markers as second's (ignoring the first, null Marker)
		assertThat(second.getLayer(0).getMarkers().size() * 2).isEqualTo(first.getLayer(0).getMarkers().size() + 1);
		// first TextGrid's first Layer should be twice as long as second's
		assertThat(second.getLayer(0).getMarkers().last().getTime() * 2).isEqualTo(
				first.getLayer(0).getMarkers().last().getTime(), offset(1e-10));
	}

	public void testTextGridAppend() throws Exception {
		TextGrid part1 = (TextGrid) PraatFile.read("test_part1.UTF-8.TextGrid");
		TextGrid part2 = (TextGrid) PraatFile.read("test_part2.UTF-8.TextGrid");
		Annotation first = new Annotation(part1);
		Annotation second = new Annotation(part2);
		// append second to the end of first
		first.append(second);
		// convert back to TextGrid
		TextGrid appended = first.toTextGrid();
		// first should now be identical to test reference
		assertThat(textGrid).isEqualTo(appended);
	}

}
