package fr.loria.parole.annotk.praat;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;

import fr.loria.parole.annotk.Layer;
import fr.loria.parole.annotk.Marker;
import fr.loria.parole.annotk.Marker.Anchor;

public class IntervalTier extends AbstractTier {

	private List<Interval> intervals = Lists.newArrayList();

	public IntervalTier(String name, double startTime, double endTime) {
		super(name, startTime, endTime);
		Interval interval = new Interval(startTime, endTime, "");
		intervals.add(interval);
	}

	public IntervalTier(PraatFile file) throws IOException {
		read(file);
	}

	@Override
	public PraatObject read(PraatFile file) throws IOException {
		try {
			name = file.readString();
		} catch (IllegalArgumentException e) {
			// pass; file containers don't provide names
		}
		xmin = file.readDouble();
		xmax = file.readDouble();
		int numItems = file.readInteger();

		// iterate over intervals
		for (int i = 0; i < numItems; i++) {
			double start = file.readDouble();
			double end = file.readDouble();
			String label = file.readString();
			Interval interval = new Interval(start, end, label);
			intervals.add(interval);
		}
		return this;
	}

	@Override
	public Layer asLayer() {
		Layer layer = new Layer();
		for (Interval interval : intervals) {
			layer.addMarker(interval.asMarker());
		}
		Marker end = new Marker(xmax, null, Anchor.END);
		layer.addMarker(end);
		return layer;
	}

}
