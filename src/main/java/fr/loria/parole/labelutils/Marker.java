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
package fr.loria.parole.labelutils;

import org.praat.Interval;
import org.praat.Point;

import com.google.common.base.Objects;

public class Marker implements Comparable<Marker> {

	private double time;
	private String label;
	private Anchor type;

	public Marker(double time, String label, Anchor type) {
		this.time = time;
		this.label = label;
		this.type = type;
	}

	/**
	 * Clone a Marker, applying an offset to the time
	 * 
	 * @param other
	 *            Marker to clone
	 * @param offset
	 *            to add to the clone's time
	 */
	public Marker(Marker other, double offset) {
		this.time = other.time + offset;
		this.label = other.label;
		this.type = other.type;
	}

	public Marker(Point point) {
		time = point.getTime();
		label = point.getText();
		type = Anchor.POINT;
	}

	public Marker(Interval interval) {
		time = interval.getEndTime();
		label = interval.getText();
		type = Anchor.BOUNDARY;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getLabel() {
		return label;
	}

	public Anchor getType() {
		return type;
	}

	@Override
	public int compareTo(Marker other) {
		if (this.time < other.time) {
			return -1;
		}
		if (this.time > other.time) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		String id = Objects.toStringHelper(this).add("time", time).add("label", label).add("type", type).toString();
		return id;
	}

	/**
	 * Does the time represent the marker itself (i.e., a point), or the start or end of an interval?
	 * 
	 * @author ingmar
	 * 
	 */
	public enum Anchor {
		POINT, BOUNDARY;
	}

}
