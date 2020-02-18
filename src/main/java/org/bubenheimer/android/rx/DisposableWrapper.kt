/*
 * Copyright (c) 2015-2019 Uli Bubenheimer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.bubenheimer.android.rx

import io.reactivex.rxjava3.disposables.Disposable

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
