/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import rx.Observable;
import rx.functions.Func1;

/**
 * Skip all current items. If there is no current item, skip nothing.
 */
public final class SkipCurrent<T> implements Observable.Transformer<T,T> {
    private static final Object NONE = new Object(); // safe because of erasure

    @Override
    public Observable<T> call(final Observable<T> observable) {
        //noinspection unchecked
        final T semaphore = (T) NONE;
        return Observable.merge(observable, Observable.just(semaphore))
                .skipWhile(new Func1<T, Boolean>() {
                    @Override
                    public Boolean call(final T item) {
                        return item != semaphore;
                    }
                })
                .skip(1);
    }
}
