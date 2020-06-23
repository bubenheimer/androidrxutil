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
import java.util.*

@Suppress("unused")
fun <T> Observable<Optional<T>>.toNullLiveData(): LiveData<T> = toLiveData().map { it.orElse(null) }

fun <T> Observable<T>.toLiveData(): LiveData<T> =
        toFlowable(BackpressureStrategy.LATEST).toLiveData()

@MainThread
fun <T> LiveData<T>.filter(predicate: (T?) -> Boolean): LiveData<T> {
    Check.onMainThread()

    return MediatorLiveData<T>().apply {
        addSource(this@filter) { if (predicate(it)) value = it }
    }
}

@Suppress("unused")
@MainThread
fun <T> LiveData<T>.nonNull(): LiveData<T> {
    Check.onMainThread()

    return filter { it != null }
}

@Suppress("unused")
fun <T> LiveData<T>.withDefault(defaultValue: T) = object : MediatorLiveData<T>() {
    init {
        addSource(this@withDefault, ::setValue)
    }

    override fun getValue() = super.getValue() ?: defaultValue
}
