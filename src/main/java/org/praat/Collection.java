package org.praat;

import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Collection implements PraatObject, Iterable<PraatObject> {

	List<PraatObject> items = Lists.newArrayList();

	protected Collection() {
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public ListIterator<PraatObject> iterator() {
		ListIterator<PraatObject> iterator = items.listIterator();
		return iterator;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("items", items).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Collection) {
			final Collection other = (Collection) obj;
			return Objects.equal(this.items, other.items);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(items);
	}
}
