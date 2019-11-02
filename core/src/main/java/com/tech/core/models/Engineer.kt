package com.tech.core.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

const val NONE = -1

@Parcel(Parcel.Serialization.BEAN)
data class Engineer @ParcelConstructor constructor(
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