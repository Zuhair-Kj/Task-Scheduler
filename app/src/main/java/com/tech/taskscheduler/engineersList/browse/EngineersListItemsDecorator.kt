package com.tech.taskscheduler.engineersList.browse

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EngineersListItemsDecorator(private val cellSpace: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) < 2) {
                top = cellSpace * 2
            }
            left =  cellSpace
            right = cellSpace
            bottom = cellSpace
        }
    }
}