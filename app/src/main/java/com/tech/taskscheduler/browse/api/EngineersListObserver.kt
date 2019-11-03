package com.tech.taskscheduler.browse.api

import com.tech.core.models.Engineer
import com.tech.taskscheduler.browse.BrowseMvp
import com.tech.taskscheduler.browse.BrowsePresenter
import io.reactivex.observers.DisposableSingleObserver

class EngineersListObserver(private val mvpView: BrowseMvp.View?, private val mvpPresenter: BrowsePresenter)
    : DisposableSingleObserver<List<Engineer>>() {
    override fun onSuccess(list: List<Engineer>) {
        mvpView?.hideLoading()
        list.forEach{
            it.init()
        }
        mvpPresenter.onEngineersListFetched(list)
    }

    override fun onError(e: Throwable) {
        mvpView?.apply {
            hideLoading()
            showError(e.message ?: "Generic Error")
        }
    }

    override fun onStart() {
        mvpView?.showLoading()
    }
}