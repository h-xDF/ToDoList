package ru.raralux.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task constructor(@ColumnInfo(name = "name") val name: String,
                            @ColumnInfo(name = "description") val description: String,
                            @ColumnInfo(name = "visible") val visible: Boolean) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var taskId: Int? = null
}