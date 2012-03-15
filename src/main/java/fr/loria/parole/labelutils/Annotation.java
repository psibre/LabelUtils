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
