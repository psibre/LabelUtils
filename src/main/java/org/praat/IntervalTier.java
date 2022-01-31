/*
 * #%L
 * LabelUtils
 * %%
 * Copyright (C) 2012 INRIA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
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
	 *   Tier name
	 * @param intervals
	 *   Intervals
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
	public void write(PraatFile file) throws IOException {
		file.writeDouble("xmin =", xmin);
		file.writeDouble("xmax =", xmax);
		file.writeInteger("intervals: size =", items.size());

		// iterate over items
		int p = 1;
		for (Interval point : items) {
			file.writeLine("intervals [%d]:", p++);
			file.increaseIndent();
			file.writeDouble("xmin =", point.getStartTime());
			file.writeDouble("xmax =", point.getEndTime());
			file.writeString("text =", point.getText());
			file.decreaseIndent();
		}
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
