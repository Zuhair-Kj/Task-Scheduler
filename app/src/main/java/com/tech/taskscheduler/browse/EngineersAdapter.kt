package com.tech.taskscheduler.browse

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tech.core.models.Engineer
import com.tech.taskscheduler.R

class EngineersAdapter(
    private val items: MutableList<Engineer>,
    private val context: Context
): RecyclerView.Adapter<EngineerViewHolder>() {
    val lazyInflator: Lazy<LayoutInflater> = lazy { LayoutInflater.from(context) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        return EngineerViewHolder(
            DataBindingUtil.inflate(
                lazyInflator.value,
                R.layout.list_item_engineer,
                null,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun addItems(list: List<Engineer>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

}