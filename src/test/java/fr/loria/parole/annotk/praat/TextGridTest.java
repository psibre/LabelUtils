package fr.loria.parole.annotk.praat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextGridTest {

	private PraatObject utf8TextGrid;
	private PraatObject utf8ShortTextGrid;

	@Before
	public void setUp() throws Exception {
		utf8TextGrid = PraatFile.read("test.UTF-8.TextGrid");
		utf8ShortTextGrid = PraatFile.read("test.UTF-8.short.TextGrid");
	}

	@Test
	public void compareFormats() {
		assertEquals(utf8TextGrid, utf8ShortTextGrid);
	}

}
