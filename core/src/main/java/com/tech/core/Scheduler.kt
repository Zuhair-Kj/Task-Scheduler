package com.tech.core

import com.tech.core.models.Engineer
import com.tech.core.models.NONE

// The number of shits an engineer can do is not here as each engineer may have his own number (in the future).
class Scheduler(private val numberOfDays: Int, private val shiftsPerDay: Int, private val minShiftsQota: Int) {
    fun scheduleWork(engineers: List<Engineer>): List<List<Engineer>> {
        val result = mutableListOf<List<Engineer>>()

        for (i in 0 until numberOfDays) {
            result.add(engineers
                .filter { canAssignWork(it, i) }
                .sortedWith(engineersComparator)
                .take(shiftsPerDay)
                .map {
                    it.assignwork(i)
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

//    val engineersComparator: Comparator<Engineer> = object : Comparator<Engineer> {
//        override fun compare(p0: Engineer?, p1: Engineer?): Int {
//            return e1.shiftsDone - e2.shiftsDone
//        }
//    }

    val engineersComparator = object: Comparator<Engineer> {
        override fun compare(e1: Engineer, e2: Engineer): Int {
            return e1.shiftsDone - e2.shiftsDone
        }
    }

}