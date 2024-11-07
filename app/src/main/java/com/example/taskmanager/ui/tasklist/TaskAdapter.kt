package com.example.taskmanager.ui.tasklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.TaskStatus
import com.example.taskmanager.model.Priority
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var tasks = emptyList<Task>()
    private var onItemClick: ((Task) -> Unit)? = null

    fun setOnItemClickListener(listener: (Task) -> Unit) {
        onItemClick = listener
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                taskTitle.text = task.title
                taskDescription.text = task.description

                // Set priority text
                taskPriority.text = when (task.priority) {
                    Priority.LOW -> root.context.getString(R.string.priority_low)
                    Priority.MEDIUM -> root.context.getString(R.string.priority_medium)
                    Priority.HIGH -> root.context.getString(R.string.priority_high)
                }

                // Set status background
                val backgroundColor = when (task.status) {
                    TaskStatus.PENDING -> R.color.pending_task
                    TaskStatus.COMPLETED -> R.color.completed_task
                }
                root.setBackgroundColor(root.context.getColor(backgroundColor))

                // Set click listener
                root.setOnClickListener {
                    onItemClick?.invoke(task)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size

    fun submitList(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}