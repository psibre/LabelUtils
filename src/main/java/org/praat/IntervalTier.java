package org.praat;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class IntervalTier extends Tier implements Iterable<Interval> {

	private List<Interval> items = Lists.newArrayList();

	/**
	 * Construct IntervalTier from a List of Intervals. The time domain of the IntervalTier will be determined by the start and
	 * end time of the first and last Interval, respectively.
	 * 
	 * @param name
	 * @param intervals
	 */
	public IntervalTier(String name, List<Interval> intervals) {
		this.name = name;

		// update time domain
		xmin = intervals.get(0).getStartTime();
		xmax = Iterables.getLast(intervals).getEndTime();

		items = intervals;
	}

	public IntervalTier(String name, double startTime, double endTime) {
		super(name, startTime, endTime);
		Interval interval = new Interval(startTime, endTime, "");
		items.add(interval);
	}

	public IntervalTier(PraatFile file) throws IOException {
		read(file);
	}

	public void addInterval(Interval interval) {
		items.add(interval);
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
	public ListIterator<Interval> iterator() {
		ListIterator<Interval> iterator = items.listIterator();
		return iterator;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("xmin", xmin).add("xmax", xmax).add("name", name).add("intervals", items)
				.toString();
	}

}
