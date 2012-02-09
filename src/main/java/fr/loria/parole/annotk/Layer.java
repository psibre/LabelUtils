package fr.loria.parole.annotk;

import java.util.SortedSet;

import com.google.common.collect.Sets;

import fr.loria.parole.annotk.Marker.Anchor;
import fr.loria.parole.annotk.praat.Interval;
import fr.loria.parole.annotk.praat.IntervalTier;
import fr.loria.parole.annotk.praat.Point;
import fr.loria.parole.annotk.praat.Tier;

public class Layer {
	private SortedSet<Marker> markers = Sets.newTreeSet();

	public Layer() {

	}

	public Layer(Tier tier) {
		for (Point point : tier) {
			Marker marker;
			if (tier instanceof IntervalTier) {
				Interval interval = (Interval) point;
				marker = new Marker(interval);
			} else {
				marker = new Marker(point);
			}
			markers.add(marker);
		}
		if (tier instanceof IntervalTier) {
			Marker marker = new Marker(tier.getEndTime(), null, Anchor.END);
			markers.add(marker);
		}
	}

	public void addMarker(Marker marker) {
		markers.add(marker);
	}
}
