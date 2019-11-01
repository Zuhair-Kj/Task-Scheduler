package com.tech.core.models

const val NONE = -1

data class Engineer constructor(
    val id: Int,
    val name: String?,
    var coolOffDays: Int,
    var shiftsDone: Int,
    var lastShiftDone: Int
){
    fun init() {
        coolOffDays = 1
        lastShiftDone = NONE
    }

    fun assignwork(dayIndex: Int) {
        this.shiftsDone++
        this.lastShiftDone = dayIndex
    }
}