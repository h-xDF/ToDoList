package ru.raralux.todolist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.raralux.todolist.database.AppDatabase
import ru.raralux.todolist.database.Task

class TaskViewModel(application: Application): AndroidViewModel(application) {
    var list: LiveData<MutableList<Task>>

    init {
        list = AppDatabase.getAllTask(AppDatabase.invoke(this.getApplication()))
    }

    fun fetchAllTask(): LiveData<MutableList<Task>> = list
}