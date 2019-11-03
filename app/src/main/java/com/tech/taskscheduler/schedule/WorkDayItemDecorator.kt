package com.tech.taskscheduler.schedule

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class WorkDayItemDecorator(private val cellSpace: Int, private val orientation: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            bottom = cellSpace
            right = cellSpace
            if (orientation == RecyclerView.VERTICAL) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = cellSpace * 2
                }
                left =  cellSpace


            } else {
                if (parent.getChildAdapterPosition(view) == 0) {
                    left = cellSpace * 2
                }
                top =  cellSpace
            }

        }
    }
}