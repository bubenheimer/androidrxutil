/*
 * Copyright (c) 2015-2019 Uli Bubenheimer. All rights reserved.
 */

@file:JvmName("OptRxUtils")

package org.bubenheimer.android.rx

import com.google.common.base.Optional
import io.reactivex.Observable

fun Optional<*>.isAbsent() = !isPresent

fun <T> Optional<T>.toObservable(): Observable<T> =
    if (isPresent) Observable.just(get()) else Observable.empty<T>()
