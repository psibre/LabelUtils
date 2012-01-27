package fr.loria.parole.annotk.praat;

import fr.loria.parole.annotk.Marker;
import fr.loria.parole.annotk.Marker.Anchor;

public class Point {
	private double time;
	private String text;

	public Point(double time, String text) {
		this.time = time;
		this.text = text;
	}

	public Marker asMarker() {
		Marker marker = new Marker(time, text, Anchor.POINT);
		return marker;
	}
}
