/*
 * Copyright (c) 2015-2019 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx

import io.reactivex.disposables.Disposable

/** Cheaper replacement for `SerialDisposable`. Methods are not thread-safe.  */
class DisposableWrapper {
    private var disposable: Disposable? = null

    fun get() = disposable

    fun set(disposable: Disposable) {
        check(this.disposable == null)
        this.disposable = disposable
    }

    fun clear() {
        disposable?.let {
            disposable!!.dispose()
            disposable = null
        }
    }
}
