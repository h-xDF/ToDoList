package ru.raralux.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task_layout.view.*
import ru.raralux.todolist.R
import ru.raralux.todolist.database.Task

class RecyclerViewAdapter(val context: Context, var data: MutableList<Task>?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.item_task_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindItem(data?.get(position))
    }

    fun addItems(t: MutableList<Task>?) {
        data = t
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        fun bindItem(task: Task?) {
            itemView.txtName.text = task?.name
            itemView.txtNo.setText(Integer.valueOf(task?.taskId!!))
            itemView.checkbox.isChecked = task?.visible?:false
        }
    }
}