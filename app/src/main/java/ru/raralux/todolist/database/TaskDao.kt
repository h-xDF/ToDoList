package ru.raralux.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): LiveData<MutableList<Task>>

    @Query("SELECT * FROM Task WHERE task_id = :id")
    fun getTaskById(id: Int): Task?

    @Insert
    fun addTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task?)
}