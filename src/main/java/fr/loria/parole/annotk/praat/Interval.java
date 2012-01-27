package fr.loria.parole.annotk.praat;

public class Interval {
	double xmin;
	double xmax;
	String text;

	public Interval(double start, double end, String label) {
		xmin = start;
		xmax = end;
		text = label;
	}
}
