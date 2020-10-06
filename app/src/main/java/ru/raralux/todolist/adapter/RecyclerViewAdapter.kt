package ru.raralux.todolist.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_task_layout.view.*
import ru.raralux.todolist.R
import ru.raralux.todolist.database.AppDatabase
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
            itemView.txtNo.text = task?.taskId.toString()
            itemView.txtDesc.text = task?.description
            itemView.checkbox.isChecked = task?.visible?:false

            itemView.setOnLongClickListener {
                Log.d("test", "delete task: ${task.toString()}")
                //AppDatabase.deleteTask(AppDatabase.invoke(itemView.context), task)

                Completable
                    .fromAction{ AppDatabase.invoke(itemView.context).taskDao().deleteTask(task) }
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {Toast.makeText(itemView.context,
                            "Successfully! deleted task: id=${task?.taskId}", Toast.LENGTH_SHORT).show()},
                        {Toast.makeText(itemView.context,
                            "error: delete task id=${task?.taskId}", Toast.LENGTH_SHORT).show()}
                    )
                true
            }

            itemView.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                task?.visible = isChecked
                Log.d("test", "update task: ${task.toString()}")
                //AppDatabase.updateTask(AppDatabase.invoke(itemView.context), task!!)

                Completable
                    .fromAction{ AppDatabase.invoke(itemView.context).taskDao().updateTask(task!!)}
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {Toast.makeText(itemView.context,
                            "Successfully! updated task: id=${task!!.taskId}", Toast.LENGTH_SHORT).show()},
                        {Toast.makeText(itemView.context,
                            "error: update task id=${task?.taskId}", Toast.LENGTH_SHORT).show()}
                    )
            }
        }
    }
}