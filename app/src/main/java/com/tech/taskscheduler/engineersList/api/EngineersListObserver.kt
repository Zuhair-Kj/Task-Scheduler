package com.tech.taskscheduler.engineersList.api

import com.tech.core.models.Engineer
import com.tech.taskscheduler.engineersList.browse.EngineersListMvp
import com.tech.taskscheduler.engineersList.browse.EngineersListPresenter
import io.reactivex.observers.DisposableSingleObserver

class EngineersListObserver(private val mvpView: EngineersListMvp.View?, private val mvpPresenter: EngineersListPresenter)
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