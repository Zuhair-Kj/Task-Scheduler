package com.tech.core

import com.tech.core.models.Engineer

abstract class Scheduler (protected val numberOfDays: Int, protected val shiftsPerDay: Int) {
    abstract fun scheduleWork(engineers: List<Engineer>): List<List<Engineer>>
}