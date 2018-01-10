/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func5;

@SuppressWarnings("unused")
public final class RxFuncs {
    @SuppressWarnings("Convert2MethodRef")
    public static final Func1<Object, Boolean> nullFilter = o -> o == null;

    @SuppressWarnings("Convert2MethodRef")
    public static final Func1<Object, Boolean> notNullFilter = o -> o != null;

    public static final Func2<Object, Object, Void> nullFunc2 =
            (o1, o2) -> null;

    public static final Func5<Object, Object, Object, Object, Object, Void> nullFunc5 =
            (o1, o2, o3, o4, o5) -> null;

    private RxFuncs() {
        throw new UnsupportedOperationException();
    }
}
