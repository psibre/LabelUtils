package fr.loria.parole.annotk.praat;

import fr.loria.parole.annotk.Layer;

abstract public class Tier implements PraatObject {
	double xmin;
	double xmax;
	String name;

	protected Tier() {
		// empty constructor
	}

	public Tier(String name, double startTime, double endTime) {
		this.name = name;
		xmin = startTime;
		xmax = endTime;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public abstract Layer asLayer();
}
