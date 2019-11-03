package com.tech.taskscheduler.schedule

import com.tech.core.models.Engineer
import com.tech.core.models.WorkDay
import com.tech.core.mvp.MvpPresenter
import com.tech.core.mvp.MvpView

interface ScheduleMvp {
    interface Presenter: MvpPresenter<View> {
        fun getSchedule(engineersList: List<Engineer>)
    }
    interface View: MvpView {
        fun showSchedule(schedule: List<WorkDay>)
        fun showError(message: String)
    }
}