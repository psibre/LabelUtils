package fr.loria.parole.annotk;

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

	public Marker(Point point) {
		time = point.getTime();
		label = point.getText();
		type = Anchor.POINT;
	}

	public Marker(Interval interval) {
		time = interval.getStartTime();
		label = interval.getText();
		type = Anchor.START;
	}

	public double getTime() {
		return time;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * Does the time represent the marker itself (i.e., a point), or the start or end of an interval?
	 * 
	 * @author ingmar
	 * 
	 */
	public enum Anchor {
		POINT, START, END;
	}

	@Override
	public int compareTo(Marker other) {
		if (this.time < other.time)
			return -1;
		if (this.time > other.time)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		String id = Objects.toStringHelper(this).add("time", time).add("label", label).add("type", type).toString();
		return id;
	}

}
