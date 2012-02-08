package fr.loria.parole.annotk.praat;

import com.google.common.base.Objects;

import fr.loria.parole.annotk.Marker;
import fr.loria.parole.annotk.Marker.Anchor;

public class Interval {
	private double xmin;
	private double xmax;
	private String text;

	public Interval(double start, double end, String label) {
		xmin = start;
		xmax = end;
		text = label;
	}

	public Marker asMarker() {
		Marker marker = new Marker(xmin, text, Anchor.START);
		return marker;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("xmin", xmin).add("xmax", xmax).add("text", text).toString();
	}
}
