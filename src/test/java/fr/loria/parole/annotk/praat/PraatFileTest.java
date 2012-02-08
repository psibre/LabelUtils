package fr.loria.parole.annotk.praat;

import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.Test;

import com.google.common.base.Charsets;

public class PraatFileTest {

	@Test(expected = IllegalArgumentException.class)
	public void handleMissingResource() throws Exception {
		PraatFile.read("--INVALID_RESOURCE_NAME--");
	}

	@Test(expected = IOException.class)
	public void handleUnreadbleResource() throws Exception {
		// PraatFile.read("unreadableFile");
		// TODO make maven copy this test resource and then set permissions to 000
		throw new IOException();
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleEmptyResource() throws Exception {
		PraatFile.read("emptyFile");
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleNonPraatFile() throws Exception {
		PraatFile.read("randomFile.txt");
	}

	@Test
	public void loadTextFile() throws Exception {
		PraatObject object = PraatFile.read("test.UTF-8.IntervalTier", Charsets.UTF_8);
		assertNotNull(object);
	}

	@Test
	public void loadShortTextFile() throws Exception {
		PraatObject object = PraatFile.read("test.UTF-8.short.IntervalTier", Charsets.UTF_8);
		assertNotNull(object);
	}

	@Test
	public void compareDefaultAndShortFormats() throws Exception {
		PraatObject object = PraatFile.read("test.UTF-8.IntervalTier", Charsets.UTF_8);
		PraatObject shortObject = PraatFile.read("test.UTF-8.short.IntervalTier", Charsets.UTF_8);
		assertEquals(object, shortObject);
	}

}
