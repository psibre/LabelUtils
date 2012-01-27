package fr.loria.parole.annotk.praat;

import java.util.List;

import com.google.common.collect.Lists;

public class TextGrid {

	private double xmin;
	private double xmax;

	private List<Tier> tiers = Lists.newArrayList();

	public TextGrid(double startTime, double endTime, List<String> tierNames, List<String> pointTiers) {
		xmin = startTime;
		xmax = endTime;
		for (String tierName : tierNames) {
			Tier tier;
			if (pointTiers.contains(tierName)) {
				tier = new PointTier(tierName, startTime, endTime);
			} else {
				tier = new IntervalTier(tierName, startTime, endTime);
			}
			tiers.add(tier);
		}
	}
}
