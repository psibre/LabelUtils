package fr.loria.parole.annotk.praat;

import java.io.IOException;

public interface PraatObject {
	public PraatObject read(PraatFile file) throws IOException;
}
