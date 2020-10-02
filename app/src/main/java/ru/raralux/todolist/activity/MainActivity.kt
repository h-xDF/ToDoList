package ru.raralux.todolist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.raralux.todolist.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}