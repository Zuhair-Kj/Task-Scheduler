package com.tech.taskscheduler.engineersList

import com.tech.core.ApiManager
import com.tech.core.mvp.BaseMvpPresenter
import com.tech.taskscheduler.engineersList.api.EngineersListObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EngineersListPresenter @Inject constructor(private val apiManager: ApiManager) :
    BaseMvpPresenter<EngineersListMvp.View>(), EngineersListMvp.Presenter {
    override fun fetchEngineersList() {
        apiManager.getEngineers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(EngineersListObserver(mvpView))
    }
}