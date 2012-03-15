package fr.loria.parole.labelutils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.praat.IntervalTier;
import org.praat.PraatFile;

public class LayerTest {

	private IntervalTier intervalTier;

	@Before
	public void setUp() throws Exception {
		intervalTier = (IntervalTier) PraatFile.read("test.UTF-8.IntervalTier");
	}

	@Test
	public void test() {
		Layer layer = new Layer(intervalTier);
		assertEquals(intervalTier, layer.toIntervalTier());
	}

}
