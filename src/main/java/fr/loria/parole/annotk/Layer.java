package fr.loria.parole.annotk;

import java.util.SortedSet;

import com.google.common.collect.Sets;

public class Layer {
	private SortedSet<Marker> markers = Sets.newTreeSet();

	public Layer() {

	}

	public void addMarker(Marker marker) {
		markers.add(marker);
	}
}
