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

import com.google.common.base.Objects;

public class Interval extends Point {

	private double endTime;

	public Interval(double start, double end, String label) {
		super(start, label);
		endTime = end;
	}

	public double getStartTime() {
		return time;
	}

	public double getEndTime() {
		return endTime;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("xmin", time).add("xmax", endTime).add("text", text).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Interval) {
			final Interval other = (Interval) obj;
			return Objects.equal(this.time, other.time) && Objects.equal(this.endTime, other.endTime)
					&& Objects.equal(this.text, other.text);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(time, endTime, text);
	}

}
