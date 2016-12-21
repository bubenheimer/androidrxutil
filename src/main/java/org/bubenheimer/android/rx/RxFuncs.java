/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func5;

@SuppressWarnings("unused")
public final class RxFuncs {
    public static final Func1<Object, Boolean> nullFilter = new Func1<Object, Boolean>() {
        @Override
        public Boolean call(final Object o) {
            return o != null;
        }
    };

    public static final Func2<Object, Object, Void> nullFunc2 =
            new Func2<Object, Object, Void>() {
                @Override
                public Void call(final Object o1, final Object o2) {
                    return null;
                }
            };

    public static final Func5<Object, Object, Object, Object, Object, Void> nullFunc5 =
            new Func5<Object, Object, Object, Object, Object, Void>() {
                @Override
                public Void call(final Object o1, final Object o2, final Object o3, final Object o4,
                                 final Object o5) {
                    return null;
                }
    };

    private RxFuncs() {
        throw new UnsupportedOperationException();
    }
}
