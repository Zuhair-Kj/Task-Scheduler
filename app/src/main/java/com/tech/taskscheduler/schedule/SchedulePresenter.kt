package com.tech.taskscheduler.schedule

import android.os.Bundle
import com.tech.core.RoundRobinScheduler
import com.tech.core.Settings
import com.tech.core.models.Engineer
import com.tech.core.models.WorkDay
import com.tech.core.mvp.BaseMvpPresenter
import org.parceler.Parcels
import javax.inject.Inject

const val PARCEL_KEY = "workDays"
class SchedulePresenter @Inject constructor()
    : ScheduleMvp.Presenter, BaseMvpPresenter<ScheduleMvp.View>() {
    private val scheduler = RoundRobinScheduler(Settings.NUMBER_OF_DAYS, Settings.SHIFTS_PER_DAY)
    private val workDays = mutableListOf<WorkDay>()
    override fun getSchedule(engineersList: List<Engineer>) {
        if (workDays.isEmpty()) {
            scheduler.scheduleWork(engineersList)
                .forEachIndexed { index, list ->
                    workDays.add(WorkDay("${index + 1}", list))
                }
        }
        mvpView?.showSchedule(workDays)
    }

    override fun saveState(outState: Bundle) {
        outState.putParcelable(PARCEL_KEY, Parcels.wrap(workDays))
    }

    override fun restoreState(savedState: Bundle) {
        workDays.addAll(
            Parcels.unwrap<List<WorkDay>>(savedState.getParcelable(PARCEL_KEY))
        )
    }
}