package com.tech.taskscheduler.browse

import android.os.Bundle
import android.os.Parcelable
import com.tech.core.ApiManager
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpPresenter
import com.tech.taskscheduler.browse.api.EngineersListApiCall
import com.tech.taskscheduler.browse.api.EngineersListObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.parceler.Parcels
import javax.inject.Inject

const val PARCEL_KEY = "EngineersList"
class BrowsePresenter @Inject constructor(private val engineersListApiCall: EngineersListApiCall) :
    BaseMvpPresenter<BrowseMvp.View>(),
    BrowseMvp.Presenter {
    var engineersList = mutableListOf<Engineer>()
    override fun fetchEngineersList() {
        if (engineersList.isNotEmpty())
            mvpView?.populateResults(engineersList)
        else {
            engineersListApiCall.execute(EngineersListObserver(mvpView, this))
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