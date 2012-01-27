package fr.loria.parole.annotk;

public class Marker {
	private double time;
	private String label;

	/**
	 * Does the time represent the marker itself (i.e., a point), or the start or end of an interval?
	 * 
	 * @author ingmar
	 * 
	 */
	public enum Anchor {
		POINT, START, END;
	}
}
