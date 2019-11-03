package com.tech.taskscheduler.schedule

import androidx.recyclerview.widget.RecyclerView
import com.tech.core.models.WorkDay
import com.tech.taskscheduler.databinding.ListItemWorkDayBinding

class WorkDayViewHolder(private val binding: ListItemWorkDayBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(workDay: WorkDay) {
        binding.date.text = "Day ${workDay.date}"
        binding.names.text = workDay.engineers.fold(StringBuilder()) {
                builder, engineer -> builder.append("${engineer.name} ")
        }.toString()
    }
}