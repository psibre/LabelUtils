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

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

abstract public class Tier implements PraatObject {

	protected double xmin;
	protected double xmax;
	protected String name;
	protected List<Point> items = Lists.newArrayList();

	protected Tier() {
		// empty constructor
	}

	public Tier(String name, double startTime, double endTime) {
		this.name = name;
		xmin = startTime;
		xmax = endTime;
	}

	public double getStartTime() {
		return xmin;
	}

	public double getEndTime() {
		return xmax;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tier) {
			final Tier other = (Tier) obj;
			return Objects.equal(this.xmin, other.xmin) && Objects.equal(this.xmax, other.xmax)
					&& Objects.equal(this.name, other.name) && Objects.equal(this.items, other.items);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(xmin, xmax, name, items);
	}

}
