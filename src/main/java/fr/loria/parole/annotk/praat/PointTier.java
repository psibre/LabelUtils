package fr.loria.parole.annotk.praat;

import java.util.List;

import com.google.common.collect.Lists;

import fr.loria.parole.annotk.Layer;

public class PointTier extends Tier {

	private List<Point> points = Lists.newArrayList();

	public PointTier(String name, double startTime, double endTime) {
		super(name, startTime, endTime);
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
	public PraatObject read(PraatFile file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
