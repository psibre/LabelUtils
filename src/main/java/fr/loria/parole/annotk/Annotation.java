package fr.loria.parole.annotk;

import java.util.List;

import org.praat.PraatObject;
import org.praat.TextGrid;
import org.praat.Tier;

import com.google.common.collect.Lists;

public class Annotation {
	private List<Layer> layers = Lists.newArrayList();

	public Annotation(TextGrid textGrid) {
		for (PraatObject object : textGrid) {
			Tier tier = (Tier) object;
			Layer layer = new Layer(tier);
			layers.add(layer);
		}
	}
}
