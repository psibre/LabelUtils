package org.praat;

import com.google.common.base.Objects;

public class Point {

	protected double time;
	protected String text;

	public Point(double time, String text) {
		this.time = time;
		this.text = text;
	}

	public double getTime() {
		return time;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("time", time).add("text", text).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			final Point other = (Point) obj;
			return Objects.equal(this.time, other.time) && Objects.equal(this.text, other.text);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(time, text);
	}

}
