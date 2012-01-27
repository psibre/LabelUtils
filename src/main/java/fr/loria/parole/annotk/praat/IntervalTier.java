package fr.loria.parole.annotk.praat;

import java.util.List;

import com.google.common.collect.Lists;

import fr.loria.parole.annotk.Layer;
import fr.loria.parole.annotk.Marker;
import fr.loria.parole.annotk.Marker.Anchor;

public class IntervalTier extends Tier {

	private List<Interval> intervals = Lists.newArrayList();

	public IntervalTier(String name, double startTime, double endTime) {
		super(name, startTime, endTime);
		Interval interval = new Interval(startTime, endTime, "");
		intervals.add(interval);
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
