package org.praat;

import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Objects;

public class PointTier extends Tier implements Iterable<Point> {

	public PointTier(String name, double startTime, double endTime) {
		super(name, startTime, endTime);
	}

	public PointTier(String name, double startTime, double endTime, List<Point> points) {
		this(name, startTime, endTime);
		items = points;
	}

	public PointTier(PraatFile file) throws Exception {
		read(file);
	}

	@Override
	public PraatObject read(PraatFile file) throws Exception {
		xmin = file.readDouble();
		xmax = file.readDouble();
		int numItems = file.readInteger();

		// iterate over intervals
		for (int i = 0; i < numItems; i++) {
			double time = file.readDouble();
			String label = file.readString();
			Point point = new Point(time, label);
			items.add(point);
		}
		return this;
	}

	@Override
	public ListIterator<Point> iterator() {
		ListIterator<Point> iterator = items.listIterator();
		return iterator;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("xmin", xmin).add("xmax", xmax).add("name", name).add("points", items).toString();
	}

}
