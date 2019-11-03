package com.tech.taskscheduler.engineersList.browse

import android.os.Bundle
import android.os.Parcelable
import com.tech.core.ApiManager
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpPresenter
import com.tech.taskscheduler.engineersList.api.EngineersListObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.parceler.Parcels
import javax.inject.Inject

const val PARCEL_KEY = "EngineersList"
class EngineersListPresenter @Inject constructor(private val apiManager: ApiManager) :
    BaseMvpPresenter<EngineersListMvp.View>(),
    EngineersListMvp.Presenter {
    var engineersList = mutableListOf<Engineer>()
    override fun fetchEngineersList() {
        if (engineersList.isNotEmpty())
            mvpView?.populateResults(engineersList)
        else {
            apiManager.getEngineers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(EngineersListObserver(mvpView, this))
        }
    }

    fun onEngineersListFetched(list: List<Engineer>) {
        engineersList.apply {
            clear()
            addAll(list)
        }
        mvpView?.populateResults(engineersList)
    }

    override fun saveState(outState: Bundle) {
        outState.putParcelable(PARCEL_KEY, Parcels.wrap(engineersList))
    }

    override fun restoreState(savedState: Bundle) {
        engineersList.addAll(
            Parcels.unwrap<MutableList<Engineer>>(savedState.get(PARCEL_KEY) as Parcelable)
        )
        engineersList.forEach { it.init() }
    }
}