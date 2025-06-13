package com.example.geteasy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.geteasy.databinding.ItemTaskBinding
import com.example.geteasy.data.local.entities.DailyTask
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DailyTaskAdapter : ListAdapter<DailyTask, DailyTaskAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)
    private var onEditClick: ((DailyTask) -> Unit)? = null

    fun setOnEditClickListener(listener: (DailyTask) -> Unit) {
        onEditClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)

        with(holder.binding){
            title.text = task.title
            DateAndTime.text = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                .format(Date(task.reminderTime))
            editButton.setOnClickListener {
                onEditClick?.invoke(task)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DailyTask>() {
        override fun areItemsTheSame(oldItem: DailyTask, newItem: DailyTask) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DailyTask, newItem: DailyTask) =
            oldItem == newItem
    }


}