package org.bubenheimer.android.rx;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.plugins.RxJavaPlugins;

public interface DefaultsObserver<T> extends Observer<T> {
    @Override
    default void onSubscribe(
            final @NonNull Disposable d
    ) {};

    @Override
    default void onNext(
            final @NonNull T t
    ) {};

    @Override
    default void onError(
            final @NonNull Throwable e
    ) {
        RxJavaPlugins.onError(new OnErrorNotImplementedException(e));
    };

    @Override
    default void onComplete(
    ) {};
}
