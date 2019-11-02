package com.tech.taskscheduler

import com.tech.taskscheduler.engineersList.browse.EngineersListActivity
import com.tech.taskscheduler.schedule.ScheduleActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributesEngineersListActivityInjector(): EngineersListActivity

    @ContributesAndroidInjector
    abstract fun contributesScheduleInjector(): ScheduleActivity
}