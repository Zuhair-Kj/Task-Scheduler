package com.tech.taskscheduler.schedule

import com.tech.core.Scheduler
import com.tech.core.models.Engineer
import com.tech.core.mvp.BaseMvpPresenter
import javax.inject.Inject

class SchedulePresenter @Inject constructor()
    : ScheduleMvp.Presenter, BaseMvpPresenter<ScheduleMvp.View>() {
    val scheduler = Scheduler(10, 2)
    override fun getSchedule(engineersList: List<Engineer>) {

    }
}