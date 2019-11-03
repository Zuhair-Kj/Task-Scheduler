package com.tech.taskscheduler.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tech.core.models.WorkDay
import com.tech.taskscheduler.R

class WorkDayAdapter(private val workDaysList: MutableList<WorkDay>, val context: Context): RecyclerView.Adapter<WorkDayViewHolder>() {

    val lazyInflator: Lazy<LayoutInflater> = lazy { LayoutInflater.from(context) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkDayViewHolder {
        return WorkDayViewHolder(DataBindingUtil.inflate(lazyInflator.value, R.layout.list_item_work_day, null, false))
    }

    override fun onBindViewHolder(holder: WorkDayViewHolder, position: Int) {
        holder.bind(workDaysList[position])
    }

    override fun getItemCount() = workDaysList.size

    fun addItems(list: List<WorkDay>) {
        workDaysList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        workDaysList.clear()
        notifyDataSetChanged()
    }
}