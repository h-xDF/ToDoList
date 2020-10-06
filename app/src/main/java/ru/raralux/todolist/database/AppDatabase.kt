package ru.raralux.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        //private val LOCK = Any()

        private fun getInstance(context: Context): AppDatabase {

            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "Task.db"
                    )
                    .build()
            }
            return INSTANCE as AppDatabase
        }

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: getInstance(context).also { INSTANCE = it }
        }

//        @SuppressLint("StaticFieldLeak")
//        fun insertData(mydata: AppDatabase, task: Task) {
//            object : AsyncTask<Void, Void, Void>() {
//                override fun doInBackground(vararg voids: Void): Void? {
//                    mydata.taskDao().addTask(task)
//                    return null
//                }
//            }.execute()
//        }
//
//        @SuppressLint("StaticFieldLeak")
//        fun getAllTask(mydata: AppDatabase): LiveData<MutableList<Task>> {
//            lateinit var lists: LiveData<MutableList<Task>>
//
//            return object : AsyncTask<Void, Void, LiveData<MutableList<Task>>>() {
//                override fun doInBackground(vararg voids: Void): LiveData<MutableList<Task>>? {
//                    lists = mydata.taskDao().getAllTasks()
//                    return lists
//                }
//            }.execute().get()
//        }
//
//        @SuppressLint("StaticFieldLeak")
//        fun deleteTask(mydata: AppDatabase, task: Task?) {
//            object : AsyncTask<Void, Void, Void>() {
//                override fun doInBackground(vararg voids: Void): Void? {
//                    mydata.taskDao().deleteTask(task)
//                    return null
//                }
//            }.execute()
//        }
//
//        @SuppressLint("StaticFieldLeak")
//        fun updateTask(mydata: AppDatabase, task: Task) {
//            object : AsyncTask<Void, Void, Void>() {
//                override fun doInBackground(vararg voids: Void): Void? {
//                    mydata.taskDao().updateTask(task)
//                    return null
//                }
//            }.execute()
//        }
    }
}