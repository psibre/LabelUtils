package fr.loria.parole.annotk;

import java.util.List;

import org.praat.PraatObject;
import org.praat.TextGrid;
import org.praat.Tier;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Annotation {

	private List<Layer> layers = Lists.newArrayList();

	public Annotation(TextGrid textGrid) {
		for (PraatObject object : textGrid) {
			Tier tier = (Tier) object;
			String name = tier.getName();
			Layer layer = new Layer(name, tier);
			layers.add(layer);
		}
	}

	@Override
	public String toString() {
		String id = Objects.toStringHelper(this).add("layers", layers).toString();
		return id;
	}

}
