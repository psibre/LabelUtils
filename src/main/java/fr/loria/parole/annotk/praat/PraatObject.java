package fr.loria.parole.annotk.praat;

public interface PraatObject {
	public PraatObject read(PraatFile file) throws Exception;

	public String getName();

	public void setName(String name);
}
