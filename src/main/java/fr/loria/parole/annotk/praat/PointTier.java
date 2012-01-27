package fr.loria.parole.annotk.praat;

import java.util.List;

import com.google.common.collect.Lists;

public class PointTier extends Tier {

	List<Point> points = Lists.newArrayList();

	public PointTier(double startTime, double endTime) {
		super(startTime, endTime);
	}

}
