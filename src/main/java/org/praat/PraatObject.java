package org.praat;

public interface PraatObject {
	public PraatObject read(PraatFile file) throws Exception;

	public String getName();

	public void setName(String name);
}
