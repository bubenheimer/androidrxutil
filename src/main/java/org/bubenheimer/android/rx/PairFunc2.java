/*
 * Copyright (c) 2015-2018 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import android.support.v4.util.Pair;

import io.reactivex.functions.BiFunction;

public final class PairFunc2<T, U> implements BiFunction<T, U, Pair<T, U>> {
    @Override
    public Pair<T, U> apply(final T t, final U u) {
        return Pair.create(t, u);
    }
}
