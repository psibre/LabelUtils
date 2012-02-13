package org.praat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import com.google.common.base.Objects;
import com.google.common.io.Files;

public class PointTier extends Tier implements Iterable<Point> {

	public PointTier(String name, double startTime, double endTime) {
		super(name, startTime, endTime);
	}

	public PointTier(String name, double startTime, double endTime, List<Point> points) {
		this(name, startTime, endTime);
		items = points;
	}

	public PointTier(PraatFile file) throws Exception {
		read(file);
	}

	@Override
	public PraatObject read(PraatFile file) throws Exception {
		xmin = file.readDouble();
		xmax = file.readDouble();
		int numItems = file.readInteger();

		// iterate over intervals
		for (int i = 0; i < numItems; i++) {
			double time = file.readDouble();
			String label = file.readString();
			Point point = new Point(time, label);
			items.add(point);
		}
		return this;
	}

	@Override
	public void writeText(File file) throws IOException {
		Charset charset = Charset.defaultCharset();
		BufferedWriter writer = Files.newWriter(file, charset);

		// header
		writer.write("File type = \"ooTextFile\"\n");
		writer.write(String.format("Object class = \"%s\"\n", getClass().getSimpleName()));
		writer.newLine();

		// payload
		NumberFormat number = NumberFormat.getNumberInstance(Locale.US);

		writer.write(String.format("xmin = %s \n", number.format(xmin)));
		writer.write(String.format("xmax = %s \n", number.format(xmax)));
		writer.write(String.format("points: size = %d \n", items.size()));

		// iterate over items
		int p = 1;
		for (Point point : items) {
			writer.write(String.format("points [%d]:\n", p));
			writer.write(String.format("    number = %s \n", number.format(point.getTime())));
			writer.write(String.format("    mark = \"%s\" \n", point.getText()));
			p++;
		}

		// flush and close
		writer.close();
	}

	@Override
	public ListIterator<Point> iterator() {
		ListIterator<Point> iterator = items.listIterator();
		return iterator;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("xmin", xmin).add("xmax", xmax).add("name", name).add("points", items).toString();
	}

}
