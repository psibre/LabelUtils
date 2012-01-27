package fr.loria.parole.annotk.praat;

import fr.loria.parole.annotk.Layer;

abstract public class Tier {
	double xmin;
	double xmax;
	String name;

	public Tier(String name, double startTime, double endTime) {
		this.name = name;
		xmin = startTime;
		xmax = endTime;
	}

	public abstract Layer asLayer();
}
