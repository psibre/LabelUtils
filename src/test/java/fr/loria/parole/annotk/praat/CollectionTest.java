package fr.loria.parole.annotk.praat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CollectionTest {

	private PraatObject collection;
	private PraatObject shortCollection;

	@Before
	public void setUp() throws Exception {
		collection = PraatFile.read("test.UTF-8.Collection");
		shortCollection = PraatFile.read("test.UTF-8.short.Collection");
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
	}

}
