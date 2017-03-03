/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import rx.functions.FuncN;

public final class ArrayFuncN implements FuncN<Object[]> {
    @Override
    public Object[] call(final Object... args) {
        return args;
    }
}
