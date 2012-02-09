package fr.loria.parole.annotk;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;

import org.praat.Interval;
import org.praat.IntervalTier;
import org.praat.Point;
import org.praat.PointTier;
import org.praat.TextTier;
import org.praat.Tier;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Sets;

import fr.loria.parole.annotk.Marker.Anchor;

public class Layer {

	private String name;
	private SortedSet<Marker> markers = Sets.newTreeSet();

	public Layer(String name, Tier tier) {
		if (tier instanceof IntervalTier) {
			IntervalTier intervalTier = (IntervalTier) tier;
			for (Interval interval : intervalTier) {
				Marker marker = new Marker(interval);
				markers.add(marker);
			}
			Marker marker = new Marker(tier.getEndTime(), null, Anchor.END);
			markers.add(marker);
		} else if (tier instanceof PointTier || tier instanceof TextTier) {
			PointTier pointTier = (PointTier) tier;
			for (Point point : pointTier) {
				Marker marker = new Marker(point);
				markers.add(marker);
			}
		} else {
			throw new IllegalArgumentException("Unsupported tier class: " + tier.getClass());
		}
	}

	public IntervalTier toIntervalTier() {
		// temporarily store intervals in List
		List<Interval> intervals = Lists.newArrayList();

		// iterate through markers, stopping before the terminal one
		PeekingIterator<Marker> iterator = Iterators.peekingIterator(markers.iterator());
		while (iterator.hasNext()) {
			Marker marker = iterator.next();
			try {
				Marker nextMarker = iterator.peek();
				Interval interval = new Interval(marker.getTime(), nextMarker.getTime(), marker.getLabel());
				intervals.add(interval);
			} catch (NoSuchElementException e) {
				// this is already the last Marker
				break;
			}
		}

		IntervalTier intervalTier = new IntervalTier(name, intervals);
		return intervalTier;
	}

	@Override
	public String toString() {
		String id = Objects.toStringHelper(this).add("name", name).add("markers", markers).toString();
		return id;
	}

}
