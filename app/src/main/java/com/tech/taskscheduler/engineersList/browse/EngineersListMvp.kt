package com.tech.taskscheduler.engineersList.browse

import com.tech.core.models.Engineer
import com.tech.core.mvp.MvpPresenter
import com.tech.core.mvp.MvpView

interface EngineersListMvp {
    interface Presenter: MvpPresenter<View> {
        fun fetchEngineersList()
    }

    interface View: MvpView {
        fun showLoading()
        fun populateResults(engineersList: List<Engineer>)
        fun showError(errorMessage: String)
        fun hideLoading()
    }
}