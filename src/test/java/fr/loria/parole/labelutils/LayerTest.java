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

import org.praat.IntervalTier;
import org.praat.PraatFile;

import fr.loria.parole.labelutils.Marker.Anchor;

public class LayerTest {

	private IntervalTier intervalTier;

	@Before
	public void setUp() throws Exception {
		intervalTier = (IntervalTier) PraatFile.read("test.UTF-8.IntervalTier");
	}

	@Test
	public void test() {
		Layer layer = new Layer(intervalTier);
		assertThat(intervalTier).isEqualTo(layer.toIntervalTier());
	}

	@Test
	public void testAppend() {
		// two identical Layers
		Layer first = new Layer(intervalTier);
		Layer second = new Layer(intervalTier);
		// append second to the end of first
		first.append(second);
		// first should now contain twice as many Markers as second (ignoring the first, null Marker)
		assertThat(second.getMarkers().size() * 2).isEqualTo(first.getMarkers().size() + 1);
		// first should be twice as long as second
		assertThat(second.getMarkers().last().getTime() * 2).isEqualTo(first.getMarkers().last().getTime(), offset(1e-10));
	}

	@Test
	public void testPointLayerLogic() {
		// empty layer
		Layer empty = new Layer();
		assertThat(empty.containsOnlyPoints()).isFalse();
		// canonical point layer
		Layer layer = new Layer();
		layer.addMarker(new Marker(0, null, Anchor.BOUNDARY));
		layer.addMarker(new Marker(0.5, "fnord", Anchor.POINT));
		layer.addMarker(new Marker(1, null, Anchor.BOUNDARY));
		assertThat(layer.containsOnlyPoints()).isTrue();
		// pointless layer
		layer.removeMarker(new Marker(0.5, "fnord", Anchor.POINT));
		assertThat(layer.containsOnlyPoints()).isFalse();
		// interval layer
		layer.addMarker(new Marker(0.5, "fnord", Anchor.BOUNDARY));
		assertThat(layer.containsOnlyPoints()).isFalse();
		// mixed layer
		layer.addMarker(new Marker(0.25, "foo", Anchor.POINT));
		assertThat(layer.containsOnlyPoints()).isFalse();
	}

}
