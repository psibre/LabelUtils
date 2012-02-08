package fr.loria.parole.annotk.praat;

import java.io.IOException;

import org.junit.Test;

public class PraatFileTest {

	@Test(expected = IllegalArgumentException.class)
	public void handleMissingResource() throws IOException {
		new PraatFile("--INVALID_RESOURCE_NAME--");
	}

	@Test(expected = IOException.class)
	public void handleUnreadbleResource() throws IOException {
		// new PraatFile("unreadableFile");
		// TODO make maven copy this test resource and then set permissions to 000
		throw new IOException();
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleEmptyResource() throws IOException {
		new PraatFile("emptyFile");
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleNonPraatFile() throws IOException {
		new PraatFile("randomFile.txt");
	}

}
