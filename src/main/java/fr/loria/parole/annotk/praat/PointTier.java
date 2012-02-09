package fr.loria.parole.annotk.praat;

import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.Lists;

import fr.loria.parole.annotk.Layer;

public class PointTier extends Tier implements Iterable<Point> {

	private List<Point> points = Lists.newArrayList();

	public PointTier(String name, double startTime, double endTime) {
		super(name, startTime, endTime);
	}

	@Override
	public PraatObject read(PraatFile file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Layer asLayer() {
		Layer layer = new Layer();
		for (Point point : points) {
			layer.addMarker(point.asMarker());
		}
		return layer;
	}

	@Override
	public ListIterator<Point> iterator() {
		ListIterator<Point> iterator = points.listIterator();
		return iterator;
	}
}
