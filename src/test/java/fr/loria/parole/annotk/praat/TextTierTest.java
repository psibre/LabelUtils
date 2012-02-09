package fr.loria.parole.annotk.praat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextTierTest {

	private PraatObject utf8TextTier;
	private PraatObject utf8ShortTextTier;

	@Before
	public void setUp() throws Exception {
		utf8TextTier = PraatFile.read("test.UTF-8.TextTier");
		utf8ShortTextTier = PraatFile.read("test.UTF-8.short.TextTier");
	}

	@Test
	public void compareFormats() {
		assertEquals(utf8TextTier, utf8ShortTextTier);
	}

}
