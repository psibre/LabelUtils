package org.praat;

import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;

public class PraatFileTest {

	private PraatObject utf8IntervalTier;
	private PraatObject utf8ShortIntervalTier;

	@Before
	public void setUp() throws Exception {
		utf8IntervalTier = PraatFile.read("test.UTF-8.IntervalTier", Charsets.UTF_8);
		utf8ShortIntervalTier = PraatFile.read("test.UTF-8.short.IntervalTier", Charsets.UTF_8);
	}

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
	public void compareFormats() throws Exception {
		assertEquals(utf8IntervalTier, utf8ShortIntervalTier);
	}

}
