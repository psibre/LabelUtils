package fr.loria.parole.annotk.praat;

import java.util.List;

import com.google.common.collect.Lists;

public class Collection implements PraatObject {

	List<PraatObject> items = Lists.newArrayList();

	public Collection(PraatFile file) throws Exception {
		read(file);
	}

	public PraatObject read(PraatFile file) throws Exception {
		int numItems = file.readInteger();
		for (int i = 0; i < numItems; i++) {
			PraatObject item = file.readPayLoad();
			items.add(item);
		}
		return this;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}
}
