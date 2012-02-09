package org.praat;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class IntervalTier extends Tier {

	private List<Interval> items = Lists.newArrayList();

	public IntervalTier(String name, double startTime, double endTime) {
		super(name, startTime, endTime);
		Interval interval = new Interval(startTime, endTime, "");
		items.add(interval);
	}

	public IntervalTier(PraatFile file) throws IOException {
		read(file);
	}

	@Override
	public PraatObject read(PraatFile file) throws IOException {
		xmin = file.readDouble();
		xmax = file.readDouble();
		int numItems = file.readInteger();

		// iterate over intervals
		for (int i = 0; i < numItems; i++) {
			double start = file.readDouble();
			double end = file.readDouble();
			String label = file.readString();
			Interval interval = new Interval(start, end, label);
			items.add(interval);
		}
		return this;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("xmin", xmin).add("xmax", xmax).add("name", name).add("intervals", items)
				.toString();
	}

}