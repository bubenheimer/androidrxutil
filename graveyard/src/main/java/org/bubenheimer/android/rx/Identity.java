/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import rx.functions.Func1;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class Identity<T> implements Func1<T,T> {
    public static final Identity<?> INSTANCE = new Identity<>();

    @Override
    public T call(final T obj) {
        return obj;
    }

    public Identity() {
    }
}
