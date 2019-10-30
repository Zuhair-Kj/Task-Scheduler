package com.tech.taskscheduler.engineersList

import android.util.Log
import android.widget.Toast
import com.tech.core.ApiManager
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpPresenter
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EngineersListPresenter @Inject constructor(private val apiManager: ApiManager) :
    BaseMvpPresenter<EngineersListMvp.View>(), EngineersListMvp.Presenter {
    override fun fetchEngineersList() {
        apiManager.getEngineers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Engineer>>() {
                override fun onError(e: Throwable) {
                    mvpView?.apply {
                        hideLoading()
                        showError(e.message ?: "error")
                    }
                }

                override fun onSuccess(list: List<Engineer>) {
                    mvpView?.apply {
                        hideLoading()
                        populateResults(list)
                    }
                }

                override fun onStart() {
                    mvpView?.showLoading()
                }
            })
    }
}