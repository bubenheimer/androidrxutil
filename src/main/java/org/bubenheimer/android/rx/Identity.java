/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import rx.functions.Func1;

public final class Identity implements Func1 {
    public static final Identity INSTANCE = new Identity();

    @Override
    public Object call(final Object obj) {
        return obj;
    }

    private Identity() {
    }
}
