package com.tech.core

import com.tech.core.models.Engineer
import com.tech.core.models.NONE

class RoundRobinScheduler(numberOfDays: Int, shiftsPerDay: Int): Scheduler(numberOfDays, shiftsPerDay) {
    override fun scheduleWork(engineers: List<Engineer>): List<List<Engineer>> {
        val result = mutableListOf<List<Engineer>>()

        for (i in 0 until numberOfDays) {
            result.add(engineers
                .filter { canAssignWork(it, i) }
                .sortedWith(engineersComparator)
                .take(shiftsPerDay)
                .map {
                    it.assignWork(i)
                    it
                }
            )
        }
        return result
    }

    private fun canAssignWork(engineer: Engineer, dayIndex: Int): Boolean {
         engineer.apply {
             return lastShiftDone == NONE || lastShiftDone + coolOffDays < dayIndex
        }
    }

    private val engineersComparator = object: Comparator<Engineer> {
        override fun compare(e1: Engineer, e2: Engineer): Int {
            return e1.shiftsDone - e2.shiftsDone
        }
    }

}