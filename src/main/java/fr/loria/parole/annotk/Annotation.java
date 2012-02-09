package fr.loria.parole.annotk;

import java.util.List;

import com.google.common.collect.Lists;

import fr.loria.parole.annotk.praat.PraatObject;
import fr.loria.parole.annotk.praat.TextGrid;
import fr.loria.parole.annotk.praat.Tier;

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
