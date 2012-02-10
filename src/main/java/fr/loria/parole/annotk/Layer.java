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

	public Layer(Tier tier) {
		name = tier.getName();
		if (tier instanceof IntervalTier) {
			Marker marker = new Marker(tier.getStartTime(), null, Anchor.BOUNDARY);
			markers.add(marker);
			IntervalTier intervalTier = (IntervalTier) tier;
			for (Interval interval : intervalTier) {
				marker = new Marker(interval);
				markers.add(marker);
			}
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

	/**
	 * Construct an IntervalTier from this Layer. The first and last Marker times will determine the time domain of the
	 * IntervalTier. <br>
	 * Note that the first Marker's label will be discarded, since its function will be reduced to providing the start time of the
	 * first Interval.
	 * 
	 * @return the IntervalTier
	 */
	public IntervalTier toIntervalTier() {
		// temporarily store intervals in List
		List<Interval> intervals = Lists.newArrayList();

		// iterate over markers, stopping before the last one
		PeekingIterator<Marker> iterator = Iterators.peekingIterator(markers.iterator());
		while (iterator.hasNext()) {
			Marker startMarker = iterator.next();
			try {
				Marker endMarker = iterator.peek();
				Interval interval = new Interval(startMarker.getTime(), endMarker.getTime(), endMarker.getLabel());
				intervals.add(interval);
			} catch (NoSuchElementException e) {
				// this is already the last Marker
				break;
			}
		}

		// construct new IntervalTier
		IntervalTier tier = new IntervalTier(name, intervals);
		return tier;
	}

	/**
	 * Construct a PointTier from this Layer. The first and last Marker times will determine the time domain of the PointTier. <br>
	 * Note that the first and last Markers' labels will be discarded, since their function will be reduced to providing the start
	 * and end times of the PointTier.
	 * 
	 * @return the PointTier
	 */
	public PointTier toPointTier() {
		// temporarily store intervals in List
		List<Point> points = Lists.newArrayList();

		// iterate over markers, ignoring the first and last ones
		for (Marker marker : markers) {
			if (marker.equals(markers.first())) {
				continue;
			}
			if (marker.equals(markers.last())) {
				break;
			}
			Point point = new Point(marker.getTime(), marker.getLabel());
			points.add(point);
		}

		// construct new PointTier
		double startTime = markers.first().getTime();
		double endTime = markers.last().getTime();
		PointTier tier = new PointTier(name, startTime, endTime, points);
		return tier;
	}

	/**
	 * Convenience method to construct a TextTier from this Layer.
	 * 
	 * @return the TextTier
	 * @see Layer#toPointTier()
	 */
	public TextTier toTextTier() {
		TextTier tier = (TextTier) toPointTier();
		return tier;
	}

	/**
	 * Check whether this Layer starts and ends with a boundary marker, and otherwise contains only point Markers.
	 * 
	 * @return true if this is the case, false otherwise
	 */
	public boolean containsOnlyPoints() {
		for (Marker marker : markers) {
			if (marker.equals(markers.first()) || marker.equals(markers.last()) && !marker.getType().equals(Anchor.BOUNDARY)) {
				return false;
			} else if (!marker.getType().equals(Anchor.POINT)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String id = Objects.toStringHelper(this).add("name", name).add("markers", markers).toString();
		return id;
	}

}
