/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import org.bubenheimer.android.log.Log;

import rx.Subscriber;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DummySubscriber<T> extends Subscriber<T> {
    private static final String TAG = DummySubscriber.class.getSimpleName();

    @Override
    public void onCompleted() {
        Log.d(TAG, "onCompleted()");
    }

    @Override
    public void onError(final Throwable e) {
        Log.d(e, TAG, "onError(", e, ")");
    }

    @Override
    public void onNext(final Object o) {
        Log.v(TAG, "onNext(", o, ")");
    }
}
