package com.tech.taskscheduler

import com.tech.core.injection.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ActivityModule::class])
interface ApplicationComponent: AndroidInjector<MainApplication>{
}