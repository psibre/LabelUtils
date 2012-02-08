package fr.loria.parole.annotk.praat;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;
import org.junit.Test;

public class PraatFileTest {

	@Test(expected = IllegalArgumentException.class)
	public void handleMissingResource() throws IOException, ClassNotFoundException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		PraatFile.read("--INVALID_RESOURCE_NAME--");
	}

	@Test(expected = IOException.class)
	public void handleUnreadbleResource() throws IOException, ClassNotFoundException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		// PraatFile.read("unreadableFile");
		// TODO make maven copy this test resource and then set permissions to 000
		throw new IOException();
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleEmptyResource() throws IOException, ClassNotFoundException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		PraatFile.read("emptyFile");
	}

	@Test(expected = IllegalArgumentException.class)
	public void handleNonPraatFile() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		PraatFile.read("randomFile.txt");
	}

	@Test
	public void loadShortTextFile() throws IllegalArgumentException, IOException, ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		PraatObject object = PraatFile.read("test.UTF-8.short.IntervalTier");
		assertNotNull(object);
	}

}
