package fr.loria.parole.annotk.praat;

import com.google.common.base.Objects;

import fr.loria.parole.annotk.Marker;
import fr.loria.parole.annotk.Marker.Anchor;

public class Interval extends Point {

	private double endTime;

	public Interval(double start, double end, String label) {
		super(start, label);
		endTime = end;
	}

	public Marker asMarker() {
		Marker marker = new Marker(time, text, Anchor.START);
		return marker;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("xmin", time).add("xmax", endTime).add("text", text).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Interval) {
			final Interval other = (Interval) obj;
			return Objects.equal(this.time, other.time) && Objects.equal(this.endTime, other.endTime)
					&& Objects.equal(this.text, other.text);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(time, endTime, text);
	}

}
