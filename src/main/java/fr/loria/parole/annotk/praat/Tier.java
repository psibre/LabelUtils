package fr.loria.parole.annotk.praat;

abstract public class Tier {
	double xmin;
	double xmax;

	public Tier(double startTime, double endTime) {
		xmin = startTime;
		xmax = endTime;
	}
}
