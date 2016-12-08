package com.example.afaucogney.firebasepaging.common;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by afaucogney on 05/12/2016.
 */

public class DefaultSubscriber<T> extends Subscriber<T> {

    String TAG;

    public DefaultSubscriber(String TAG) {
        this.TAG = TAG;
    }

    @Override
    public void onCompleted() {
        Log.e(TAG,"RxComleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG,"RxError");
    }

    @Override
    public void onNext(T t) {

    }
}
