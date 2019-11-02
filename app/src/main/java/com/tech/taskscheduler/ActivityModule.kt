package com.tech.taskscheduler

import com.tech.taskscheduler.engineersList.browse.EngineersListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivityInjector(): EngineersListActivity
}