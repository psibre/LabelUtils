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
	public void write(PraatFile file) throws IOException {
		file.writeDouble("xmin =", xmin);
		file.writeDouble("xmax =", xmax);
		file.writeInteger("points: size =", items.size());

		// iterate over items
		int p = 1;
		for (Point point : items) {
			file.writeLine("points [%d]:", p++);
			file.increaseIndent();
			file.writeDouble("number =", point.getTime());
			file.writeString("mark =", point.getText());
			file.decreaseIndent();
		}
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
