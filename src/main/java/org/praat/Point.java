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

public class Point {

	protected double time;
	protected String text;

	public Point(double time, String text) {
		this.time = time;
		this.text = text;
	}

	public double getTime() {
		return time;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("time", time).add("text", text).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			final Point other = (Point) obj;
			return Objects.equal(this.time, other.time) && Objects.equal(this.text, other.text);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(time, text);
	}

}
