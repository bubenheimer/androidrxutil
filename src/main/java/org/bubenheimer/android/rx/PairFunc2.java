/*
 * Copyright (c) 2015-2019 Uli Bubenheimer
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
 *
 */

package org.bubenheimer.android.rx;

import androidx.core.util.Pair;

import io.reactivex.functions.BiFunction;

public final class PairFunc2<T, U> implements BiFunction<T, U, Pair<T, U>> {
    @Override
    public Pair<T, U> apply(final T t, final U u) {
        return Pair.create(t, u);
    }
}
