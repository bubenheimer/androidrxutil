/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import androidx.core.util.Pair;

import rx.functions.Func2;

@SuppressWarnings("unused")
public final class PairFunc<T, U> implements Func2<T, U, Pair<T, U>> {
    @Override
    public Pair<T, U> call(final T t, final U u) {
        return Pair.create(t, u);
    }
}
