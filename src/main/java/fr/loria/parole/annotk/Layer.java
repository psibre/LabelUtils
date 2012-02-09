package fr.loria.parole.annotk;

import java.util.SortedSet;

import org.praat.Interval;
import org.praat.IntervalTier;
import org.praat.Point;
import org.praat.Tier;

import com.google.common.collect.Sets;

import fr.loria.parole.annotk.Marker.Anchor;

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
