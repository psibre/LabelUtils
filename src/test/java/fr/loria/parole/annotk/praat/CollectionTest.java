package fr.loria.parole.annotk.praat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CollectionTest {

	private PraatObject utf8Collection;
	private PraatObject utf8ShortCollection;

	@Before
	public void setUp() throws Exception {
		utf8Collection = PraatFile.read("test.UTF-8.Collection");
		utf8ShortCollection = PraatFile.read("test.UTF-8.short.Collection");
	}

	@Test
	public void compareFormats() {
		assertEquals(utf8Collection, utf8ShortCollection);
	}

}
