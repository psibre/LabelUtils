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

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;

public class TextGrid extends Collection implements PraatObject {

	private double xmin;
	private double xmax;
	private String name;

	public TextGrid(String name, List<Tier> tiers) {
		Iterables.addAll(items, tiers);
		setTimeDomain();
	}

	/**
	 * TextGrid constructor mimicking Praat's <b>Create TextGrid...</b> command
	 * 
	 * @param startTime
	 *            Start time (s)
	 * @param endTime
	 *            End time (s)
	 * @param tierNames
	 *            All tier names
	 * @param pointTiers
	 *            Which of these are point tiers?
	 */
	public TextGrid(double startTime, double endTime, List<String> tierNames, List<String> pointTiers) {
		xmin = startTime;
		xmax = endTime;
		for (String tierName : tierNames) {
			Tier tier;
			if (pointTiers.contains(tierName)) {
				tier = new PointTier(tierName, startTime, endTime);
			} else {
				tier = new IntervalTier(tierName, startTime, endTime);
			}
			items.add(tier);
		}
	}

	public TextGrid(PraatFile file) throws Exception {
		read(file);
	}

	@Override
	public PraatObject read(PraatFile file) throws Exception {
		xmin = file.readDouble();
		xmax = file.readDouble();
		return super.read(file);
	}

	@Override
	public void write(PraatFile file) throws IOException {
		file.writeDouble("xmin =", xmin);
		file.writeDouble("xmax =", xmax);
		if (file instanceof PraatShortTextFile) {
			((PraatShortTextFile) file).writeBareString("<exists>");
		} else {
			file.writeLine("tiers? <exists> ");
		}
		file.writeInteger("size =", items.size());

		// iterate over items
		int i = 1;
		file.writeLine("item []: ");
		for (PraatObject item : items) {
			file.increaseIndent();
			file.writeLine("item [%d]:", i++);
			file.increaseIndent();
			file.writeString("class =", item.getClass().getSimpleName());
			file.writeString("name =", item.getName());
			item.write(file);
			file.decreaseIndent();
			file.decreaseIndent();
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Ensure that the time domain of the TextGrid encloses the time domains of all Tiers.<br>
	 * Note that if the time domains of individual Tiers differ, these deviations are <i>not</i> corrected.
	 */
	private void setTimeDomain() {
		for (PraatObject item : items) {
			Tier tier = (Tier) item;
			if (tier.getStartTime() < xmin) {
				xmin = tier.getStartTime();
			}
			if (tier.getEndTime() > xmax) {
				xmax = tier.getEndTime();
			}
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("name", name).add("xmin", xmin).add("xmax", xmax).add("tiers", items).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TextGrid) {
			final TextGrid other = (TextGrid) obj;
			return Objects.equal(this.name, other.name) && Objects.equal(this.xmin, other.xmin)
					&& Objects.equal(this.xmax, other.xmax) && Objects.equal(this.items, other.items);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, xmin, xmax, items);
	}
}
