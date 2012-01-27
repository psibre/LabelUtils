package fr.loria.parole.annotk.praat;

public class Interval {
	private double xmin;
	private double xmax;
	private String text;

	public Interval(double start, double end, String label) {
		xmin = start;
		xmax = end;
		text = label;
	}
}
