package org.praat;

import java.io.IOException;

public interface PraatObject {

	public PraatObject read(PraatFile file) throws Exception;

	public void write(PraatFile file) throws IOException;

	public String getName();

	public void setName(String name);

}
