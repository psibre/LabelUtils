/*
 * #%L
 * LabelUtils
 * %%
 * Copyright (C) 2012 INRIA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.praat;

import java.io.IOException;
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

	@Override
	public PraatObject read(PraatFile file) throws Exception {
		int numItems = file.readInteger();
		for (int i = 0; i < numItems; i++) {
			PraatObject item = file.readPayLoad();
			items.add(item);
		}
		return this;
	}

	@Override
	public void write(PraatFile file) throws IOException {
		// TODO Auto-generated method stub
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
