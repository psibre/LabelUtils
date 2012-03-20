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

import java.util.List;

import org.praat.PraatObject;
import org.praat.TextGrid;
import org.praat.Tier;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Annotation {

	private String name;
	private List<Layer> layers = Lists.newArrayList();

	public Annotation(TextGrid textGrid) {
		name = textGrid.getName();
		for (PraatObject object : textGrid) {
			Tier tier = (Tier) object;
			Layer layer = new Layer(tier);
			layers.add(layer);
		}
	}

	public TextGrid toTextGrid() {
		// temporarily store tiers in List
		List<Tier> tiers = Lists.newArrayList();

		// iterate over Layers
		for (Layer layer : layers) {
			Tier tier;
			if (layer.containsOnlyPoints()) {
				tier = layer.toTextTier();
			} else {
				tier = layer.toIntervalTier();
			}
			tiers.add(tier);
		}

		// construct new PointTier
		TextGrid textGrid = new TextGrid(name, tiers);
		return textGrid;
	}

	@Override
	public String toString() {
		String id = Objects.toStringHelper(this).add("layers", layers).toString();
		return id;
	}

}
