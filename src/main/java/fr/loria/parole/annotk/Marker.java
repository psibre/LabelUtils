package fr.loria.parole.annotk;

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
		String id = Objects.toStringHelper(this).addValue(time).addValue(label).addValue(type).toString();
		return id;
	}
}
