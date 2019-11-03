package com.tech.taskscheduler.schedule

import android.os.Bundle
import com.tech.core.Scheduler
import com.tech.core.models.Engineer
import com.tech.core.models.WorkDay
import com.tech.core.mvp.BaseMvpPresenter
import org.parceler.Parcels
import javax.inject.Inject

class SchedulePresenter @Inject constructor()
    : ScheduleMvp.Presenter, BaseMvpPresenter<ScheduleMvp.View>() {
    val scheduler = Scheduler(10, 2)
    val workDays = mutableListOf<WorkDay>()
    override fun getSchedule(engineersList: List<Engineer>) {
        scheduler.scheduleWork(engineersList)
            .forEachIndexed { index, list ->
                workDays.add(WorkDay("${index + 1}", list))
            }
        mvpView?.showSchedule(workDays)
    }

    override fun saveState(outState: Bundle) {
        outState.putParcelable("workDays", Parcels.wrap(workDays))
    }

    override fun restoreState(savedState: Bundle) {
        workDays.addAll(
            Parcels.unwrap<List<WorkDay>>(savedState.getParcelable("workDays"))
        )
    }
}