package fr.loria.parole.annotk.praat;

import java.util.List;

import com.google.common.collect.Lists;

public class IntervalTier extends Tier {

	private List<Interval> intervals = Lists.newArrayList();

	public IntervalTier(double startTime, double endTime) {
		super(startTime, endTime);
		Interval interval = new Interval(startTime, endTime, "");
		intervals.add(interval);
	}

}
