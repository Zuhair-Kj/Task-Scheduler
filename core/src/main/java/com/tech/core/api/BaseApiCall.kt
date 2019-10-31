package com.tech.core.api

import com.tech.core.ApiManager
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseApiCall<T>(protected val apiManager: ApiManager) {
    private val compositeDisposable = CompositeDisposable()
    val subscribeOnScheduler = Schedulers.io()
    val observeOnScheduler = AndroidSchedulers.mainThread()
    fun <O> execute(disposableSingleObserver: O) where O: Disposable, O: SingleObserver<T> {
        compositeDisposable.add(
            callSingle()
                .compose(applySchedulers())
                .subscribeWith(disposableSingleObserver)
        )
    }

    protected fun applySchedulers(): SingleTransformer<T, T> {
        return SingleTransformer {source ->
            source.subscribeOn(subscribeOnScheduler)
                  .observeOn(observeOnScheduler)
        }
    }

    fun abort() {
        compositeDisposable.clear()
    }

    abstract fun callSingle(): Single<T>
}