package com.example.tasktracker

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tasktracker.data.Task

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var adapter: ArrayAdapter<String>? = null
    private val taskTitles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val listViewTasks = findViewById<ListView>(R.id.listViewTasks)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskTitles)
        listViewTasks.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.allTasks.observe(this) { tasks ->
            taskTitles.clear()
            val titles = tasks.map { it.title }
            taskTitles.addAll(titles)
            adapter?.notifyDataSetChanged()
        }

        buttonAdd.setOnClickListener {
            val title = editTextTask.text.toString().trim()
            if (title.isNotEmpty()) {
                viewModel.insert(Task(title))
                editTextTask.setText("")
            }
        }

        buttonClear.setOnClickListener {
            viewModel.deleteAll()
        }
    }
}
