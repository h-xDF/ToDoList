package ru.raralux.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task constructor(@ColumnInfo(name = "name") var name: String,
                            @ColumnInfo(name = "description") var description: String,
                            @ColumnInfo(name = "visible") var visible: Boolean) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var taskId: Int? = null

    override fun toString(): String {
        return "id=$taskId, name=$name, description=$description, visible=$visible"
    }
}