package com.rivuchk.rnd.notesapp.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rivu on 11-11-2017.
 */

public class Utils {


    public static class BackgroundLoader<T> implements ObservableTransformer<T,T> {

        @Override
        public ObservableSource<T> apply(Observable<T> upstream) {
            return upstream
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    public static class BackgroundLoaderSingle<T> implements SingleTransformer<T,T> {

        @Override
        public SingleSource<T> apply(Single<T> upstream) {
            return upstream
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
