package fr.loria.parole.annotk.praat;

import java.util.List;

public class TextGrid extends Collection implements PraatObject {

	private double xmin;
	private double xmax;

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
			AbstractTier tier;
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
}
