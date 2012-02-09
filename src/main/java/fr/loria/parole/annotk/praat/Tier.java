package fr.loria.parole.annotk.praat;

import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

abstract public class Tier implements PraatObject, Iterable<Point> {

	protected double xmin;
	protected double xmax;
	protected String name;
	protected List<Point> items = Lists.newArrayList();

	protected Tier() {
		// empty constructor
	}

	public Tier(String name, double startTime, double endTime) {
		this.name = name;
		xmin = startTime;
		xmax = endTime;
	}

	public double getStartTime() {
		return xmin;
	}

	public double getEndTime() {
		return xmax;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ListIterator<Point> iterator() {
		ListIterator<Point> iterator = items.listIterator();
		return iterator;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tier) {
			final Tier other = (Tier) obj;
			return Objects.equal(this.xmin, other.xmin) && Objects.equal(this.xmax, other.xmax)
					&& Objects.equal(this.name, other.name) && Objects.equal(this.items, other.items);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(xmin, xmax, name, items);
	}

}
