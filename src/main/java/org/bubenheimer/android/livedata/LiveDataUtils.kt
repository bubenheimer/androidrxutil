/*
 * Copyright (c) 2015-2019 Uli Bubenheimer. All rights reserved.
 */

@file:JvmName("LiveDataUtils")

package org.bubenheimer.android.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.google.common.base.Optional
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import org.bubenheimer.android.threading.onMainThread

fun <T> Observable<Optional<T>>.toNullLiveData(): LiveData<T> =
        Transformations.map(toLiveData(), Optional<T>::orNull)

fun <T> Observable<T>.toLiveData(): LiveData<T> =
        LiveDataReactiveStreams.fromPublisher(toFlowable(BackpressureStrategy.LATEST))

@MainThread
fun <T> LiveData<T>.filter(predicate: (T?) -> Boolean): LiveData<T> {
    check(onMainThread())

    val result = MediatorLiveData<T>()
    result.addSource(this) {
        if (predicate(it)) {
            result.value = it
        }
    }
    return result
}

@MainThread
fun <T> LiveData<T>.nonNull(): LiveData<T> {
    check(onMainThread())

    return filter { it != null }
}

fun <T> LiveData<T>.withDefault(defaultValue: T) = object : MediatorLiveData<T>() {
    init {
        addSource(this@withDefault, this::setValue)
    }

    override fun getValue() = super.getValue() ?: defaultValue
}
