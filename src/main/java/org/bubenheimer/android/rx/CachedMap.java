/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import android.support.annotation.NonNull;

import org.bubenheimer.android.Equals;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class CachedMap<T, R> implements Observable.Operator<R, T> {
    private static final Object NONE = new Object();

    @SuppressWarnings("unchecked") // safe because of erasure
    private T lastIn = (T) NONE;
    @SuppressWarnings("unchecked") // safe because of erasure
    private R lastOut = (R) NONE;

    private final Func1<T, R> mapFunc;

    /**
     * @param mapFunc function whose most recent invocation is to be cached
     */
    @SuppressWarnings("unused")
    public CachedMap(final @NonNull Func1<T, R> mapFunc) {
        this.mapFunc = mapFunc;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override
            public void onError(final Throwable e) {
                subscriber.onError(e);
            }

            @Override
            public void onNext(final T in) {
                if (Equals.equals(in, lastIn)) {
                    subscriber.onNext(lastOut);
                } else {
                    lastOut = CachedMap.this.mapFunc.call(in);
                    lastIn = in;
                    subscriber.onNext(lastOut);
                }
            }
        };
    }
}
