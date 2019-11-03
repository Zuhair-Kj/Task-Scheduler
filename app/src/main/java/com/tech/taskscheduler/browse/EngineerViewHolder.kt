package com.tech.taskscheduler.browse

import androidx.recyclerview.widget.RecyclerView
import com.tech.core.models.Engineer
import com.tech.taskscheduler.databinding.ListItemEngineerBinding

class EngineerViewHolder(private val binding: ListItemEngineerBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(engineer: Engineer) {
        binding.id.text = "#${engineer.id}"
        binding.name.text = engineer.name
    }
}