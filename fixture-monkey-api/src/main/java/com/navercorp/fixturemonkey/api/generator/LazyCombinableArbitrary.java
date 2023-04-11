/*
 * Fixture Monkey
 *
 * Copyright (c) 2021-present NAVER Corp.
 *
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
 */

package com.navercorp.fixturemonkey.api.generator;

import java.util.function.Function;
import java.util.function.Predicate;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

import net.jqwik.api.Arbitrary;

import com.navercorp.fixturemonkey.api.lazy.LazyArbitrary;

@API(since = "0.5.0", status = Status.EXPERIMENTAL)
public final class LazyCombinableArbitrary implements CombinableArbitrary {
	private final LazyArbitrary<Arbitrary<Object>> introspected;

	public LazyCombinableArbitrary(LazyArbitrary<Arbitrary<Object>> introspected) {
		this.introspected = introspected;
	}

	@Override
	public Arbitrary<Object> combined() {
		return introspected.getValue();
	}

	@Override
	public Arbitrary<Object> rawValue() {
		return introspected.getValue();
	}

	@Override
	public CombinableArbitrary filter(Predicate<Object> predicate) {
		return new FilteredCombinableArbitrary(this, predicate);
	}

	@Override
	public CombinableArbitrary map(Function<Object, Object> mapper) {
		return new MappedCombinableArbitrary(this, mapper);
	}

	@Override
	public CombinableArbitrary injectNull(double nullProbability) {
		return new NullInjectCombinableArbitrary(this, nullProbability);
	}
}