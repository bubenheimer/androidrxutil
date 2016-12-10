/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.android.rx;

import com.jakewharton.rxrelay.BehaviorRelay;
import com.jakewharton.rxrelay.ReplayRelay;

import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;

public final class SkipCurrentTest {
    @Test
    public void skipNothing() {
        final TestSubscriber<Object> subscriber = new TestSubscriber<>();
        final Observable<Object> observable =
                Observable.empty().compose(new SkipCurrent<>());
        observable.subscribe(subscriber);
        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }

    @Test
    public void skipOne() {
        final TestSubscriber<Object> subscriber = new TestSubscriber<>();
        final Observable<Object> observable =
                Observable.just(123).compose(new SkipCurrent<>());
        observable.subscribe(subscriber);
        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }

    @Test
    public void skipTwo() {
        final TestSubscriber<Object> subscriber = new TestSubscriber<>();
        final Observable<Object> observable =
                Observable.just(123, 456).compose(new SkipCurrent<>());
        observable.subscribe(subscriber);
        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }

    @Test
    public void skipThree() {
        final TestSubscriber<Object> subscriber = new TestSubscriber<>();
        final Observable<Object> observable =
                Observable.just(123, 456, 789).compose(new SkipCurrent<>());
        observable.subscribe(subscriber);
        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }

    @Test
    public void behaviorSubjectSkipNothing() {
        final TestSubscriber<Object> subscriber = new TestSubscriber<>();
        final BehaviorRelay<Object> relay = BehaviorRelay.create();
        final Observable<Object> observable = relay.compose(new SkipCurrent<>());
        observable.subscribe(subscriber);

        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertNotCompleted();
    }

    @Test
    public void behaviorSubjectSkipDefault() {
        final TestSubscriber<Integer> subscriber = new TestSubscriber<>();
        final BehaviorRelay<Integer> relay = BehaviorRelay.create(123);
        final Observable<Integer> observable =
                relay.compose(new SkipCurrent<Integer>());
        observable.subscribe(subscriber);

        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertNotCompleted();
    }

    @Test
    public void behaviorSubjectSkipDefaultPlusOne() {
        final TestSubscriber<Integer> subscriber = new TestSubscriber<>();
        final BehaviorRelay<Integer> relay = BehaviorRelay.create(123);
        relay.call(456);
        final Observable<Integer> observable =
                relay.compose(new SkipCurrent<Integer>());
        observable.subscribe(subscriber);

        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertNotCompleted();
    }

    @Test
    public void behaviorSubjectSkipThenTakeOne() {
        final TestSubscriber<Integer> subscriber = new TestSubscriber<>();
        final BehaviorRelay<Integer> relay = BehaviorRelay.create(123);
        final Observable<Integer> observable =
                relay.compose(new SkipCurrent<Integer>());
        observable.subscribe(subscriber);

        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertNotCompleted();

        relay.call(456);
        subscriber.assertValue(456);
   }

    @Test
    public void behaviorSubjectSkipThenTakeTwo() {
        final TestSubscriber<Integer> subscriber = new TestSubscriber<>();
        final BehaviorRelay<Integer> relay = BehaviorRelay.create(123);
        final Observable<Integer> observable =
                relay.compose(new SkipCurrent<Integer>());
        observable.subscribe(subscriber);

        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertNotCompleted();

        relay.call(456);
        relay.call(789);
        subscriber.assertValues(456, 789);
    }

    @Test
    public void behaviorSubjectSkipDefaultPlusOneThenTakeTwo() {
        final TestSubscriber<Integer> subscriber = new TestSubscriber<>();
        final BehaviorRelay<Integer> relay = BehaviorRelay.create(123);
        relay.call(456);
        final Observable<Integer> observable =
                relay.compose(new SkipCurrent<Integer>());
        observable.subscribe(subscriber);

        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertNotCompleted();

        relay.call(789);
        relay.call(321);
        subscriber.assertValues(789, 321);
    }

    @Test
    public void replaySubjectSkipTwoThenTakeTwo() {
        final TestSubscriber<Integer> subscriber = new TestSubscriber<>();
        final ReplayRelay<Integer> relay = ReplayRelay.create();
        relay.call(123);
        relay.call(456);
        final Observable<Integer> observable =
                relay.compose(new SkipCurrent<Integer>());
        observable.subscribe(subscriber);

        subscriber.assertNoValues();
        subscriber.assertNoErrors();
        subscriber.assertNotCompleted();

        relay.call(789);
        relay.call(321);
        subscriber.assertValues(789, 321);
    }
}
