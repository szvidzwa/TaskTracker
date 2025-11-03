package com.example.tasktracker

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var name: String = ""
    private var notificationsEnabled: Boolean = false
    private var volume: Int = 50
    private var chosenOption: String = ""

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
}
