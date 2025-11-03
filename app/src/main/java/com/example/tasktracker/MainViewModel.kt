package com.example.tasktracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasktracker.data.Task
import com.example.tasktracker.data.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var name: String = ""
    private var notificationsEnabled: Boolean = false
    private var volume: Int = 50
    private var chosenOption: String = ""

    private val repository: TaskRepository = TaskRepository(application)
    val allTasks: LiveData<List<Task>> = repository.getAllTasks()

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    // --- MOVED CODE STARTS HERE ---

    val greeting: String
        get() = if (name.isEmpty()) {
            "Hello - ${chosenOption.ifEmpty { "visitor" }}"
        } else {
            "Hello, $name - ${chosenOption.ifEmpty { "visitor" }}"
        }

    fun updateData(name: String, notif: Boolean, vol: Int, option: String) {
        if (name.isNotEmpty()) this.name = name
        notificationsEnabled = notif
        volume = vol
        chosenOption = option
    }

    // --- MOVED CODE ENDS HERE ---
}
