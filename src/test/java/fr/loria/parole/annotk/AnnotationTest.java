package fr.loria.parole.annotk;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.loria.parole.annotk.praat.PraatFile;
import fr.loria.parole.annotk.praat.TextGrid;

public class AnnotationTest {

	private TextGrid textGrid;

	@Before
	public void setUp() throws Exception {
		textGrid = (TextGrid) PraatFile.read("test.UTF-8.TextGrid");
	}

	@Test
	public void createFromTextGrid() throws Exception {
		Annotation annotation = new Annotation(textGrid);
		assertNotNull(annotation);
	}

}
