package com.tech.taskscheduler.engineersList.api

import com.tech.core.models.Engineer
import com.tech.taskscheduler.engineersList.EngineersListMvp
import io.reactivex.observers.DisposableSingleObserver

class EngineersListObserver(private val mvpView: EngineersListMvp.View?) : DisposableSingleObserver<List<Engineer>>() {
    override fun onSuccess(t: List<Engineer>) {
        mvpView?.apply {
            hideLoading()
            populateResults(t)
        }
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