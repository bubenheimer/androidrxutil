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

@file:JvmName("LiveDataUtils")

package org.bubenheimer.android.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import androidx.lifecycle.toLiveData
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import org.bubenheimer.android.Check
import org.bubenheimer.util.Optional
import org.bubenheimer.util.orElse

@Suppress("unused")
public fun <T : Any> Observable<Optional<T>>.toNullLiveData(): LiveData<T?> {
    return toLiveData().map { it.orElse(null) }
}

public fun <T : Any> Observable<T>.toLiveData(): LiveData<T> =
    toFlowable(BackpressureStrategy.LATEST).toLiveData()

@MainThread
public fun <T : Any> LiveData<out T>.filter(predicate: (T?) -> Boolean): LiveData<T> {
    Check.onMainThread()

    return MediatorLiveData<T>().apply {
        addSource(this@filter) { if (predicate(it)) value = it }
    }
}

@Suppress("unused")
@MainThread
public fun <T : Any> LiveData<out T>.nonNull(): LiveData<T> {
    Check.onMainThread()

    return filter { it != null }
}

@Suppress("unused")
public fun <T : Any> LiveData<out T>.withDefault(defaultValue: T): LiveData<T> =
    object : MediatorLiveData<T>() {
        init {
            addSource(this@withDefault, ::setValue)
        }

        override fun getValue() = super.getValue() ?: defaultValue
    }
