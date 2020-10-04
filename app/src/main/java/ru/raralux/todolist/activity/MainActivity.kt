package ru.raralux.todolist.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.raralux.todolist.R
import ru.raralux.todolist.adapter.RecyclerViewAdapter
import ru.raralux.todolist.database.AppDatabase
import ru.raralux.todolist.database.Task
import ru.raralux.todolist.viewModel.TaskViewModel

class MainActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerViewAdapter
    lateinit var viewModel: TaskViewModel
    private lateinit var recyclerView: RecyclerView

    private lateinit var add: FloatingActionButton

    private var list: MutableList<Task>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add = findViewById(R.id.fab_added_task)

        recyclerView = findViewById(R.id.rv_task)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(this, list)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.fetchAllTask().observe(this,
            Observer<MutableList<Task>> { t ->
                Log.v("OnChanged","OnChanged!!")
                adapter.addItems(t)
            })

        add.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog)
        val lp: WindowManager.LayoutParams = WindowManager.LayoutParams().apply {
            copyFrom(dialog.window?.attributes)
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
        }

        val submit = dialog.findViewById<Button>(R.id.submit)
        val title = dialog.findViewById<EditText>(R.id.title)
        val description = dialog.findViewById<EditText>(R.id.description)

        submit.setOnClickListener {
            when {
                title.length() == 0 || description.length() == 0 ->
                    Toast.makeText(this@MainActivity, "Please fill all the fields"
                        , Toast.LENGTH_SHORT).show()

                else -> {
                    val task = Task(title.text.toString(), title.text.toString(), false)
                    AppDatabase.insertData(AppDatabase.getInstance(this), task)
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
        dialog.window?.attributes = lp
    }
}