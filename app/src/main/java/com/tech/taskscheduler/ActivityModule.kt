package com.tech.taskscheduler

import com.tech.taskscheduler.browse.BrowseActivity
import com.tech.taskscheduler.schedule.ScheduleActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributesEngineersListActivityInjector(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesScheduleInjector(): ScheduleActivity
}