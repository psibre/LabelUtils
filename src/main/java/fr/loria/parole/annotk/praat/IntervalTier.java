package fr.loria.parole.annotk.praat;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Objects;
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

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("xmin", xmin).add("xmax", xmax).add("name", name).add("intervals", intervals)
				.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntervalTier) {
			final IntervalTier other = (IntervalTier) obj;
			return Objects.equal(this.xmin, other.xmin) && Objects.equal(this.xmax, other.xmax)
					&& Objects.equal(this.name, other.name) && Objects.equal(this.intervals, other.intervals);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(xmin, xmax, name, intervals);
	}
}
