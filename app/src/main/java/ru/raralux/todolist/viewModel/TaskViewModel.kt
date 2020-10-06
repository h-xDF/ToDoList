package ru.raralux.todolist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.raralux.todolist.database.AppDatabase
import ru.raralux.todolist.database.Task

class TaskViewModel(application: Application): AndroidViewModel(application) {

    fun fetchAllTask(): LiveData<MutableList<Task>> {
        return AppDatabase.invoke(this.getApplication()).taskDao().getAllTasks()
    }


    fun addTask(task: Task) {
        AppDatabase.invoke(this.getApplication()).taskDao().addTask(task)
    }
}