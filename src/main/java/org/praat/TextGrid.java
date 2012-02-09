package org.praat;

import java.util.List;

import com.google.common.base.Objects;

public class TextGrid extends Collection implements PraatObject {

	private double xmin;
	private double xmax;
	private String name;

	public TextGrid(PraatFile file) throws Exception {
		read(file);
	}

	/**
	 * Default TextGrid constructor mimicking Praat's <b>Create TextGrid...</b> command
	 * 
	 * @param startTime
	 *            Start time (s)
	 * @param endTime
	 *            End time (s)
	 * @param tierNames
	 *            All tier names
	 * @param pointTiers
	 *            Which of these are point tiers?
	 */
	public TextGrid(double startTime, double endTime, List<String> tierNames, List<String> pointTiers) {
		xmin = startTime;
		xmax = endTime;
		for (String tierName : tierNames) {
			Tier tier;
			if (pointTiers.contains(tierName)) {
				tier = new PointTier(tierName, startTime, endTime);
			} else {
				tier = new IntervalTier(tierName, startTime, endTime);
			}
			items.add(tier);
		}
	}

	@Override
	public PraatObject read(PraatFile file) throws Exception {
		xmin = file.readDouble();
		xmax = file.readDouble();
		return super.read(file);
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
	public String toString() {
		return Objects.toStringHelper(this).add("name", name).add("xmin", xmin).add("xmax", xmax).add("tiers", items).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TextGrid) {
			final TextGrid other = (TextGrid) obj;
			return Objects.equal(this.name, other.name) && Objects.equal(this.xmin, other.xmin)
					&& Objects.equal(this.xmax, other.xmax) && Objects.equal(this.items, other.items);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, xmin, xmax, items);
	}
}
