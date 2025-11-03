package com.example.tasktracker

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val tvGreeting: TextView = findViewById(R.id.tvGreeting)
        val etName: EditText = findViewById(R.id.etName)
        val spinnerOptions: Spinner = findViewById(R.id.spinnerOptions)
        val switchNotify: Switch = findViewById(R.id.switchNotify)
        val seekBarVolume: SeekBar = findViewById(R.id.seekBarVolume)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        val options = resources.getStringArray(R.array.sample_options)
        val adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            options
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    view.setTextColor(resources.getColor(android.R.color.darker_gray))
                } else {
                    view.setTextColor(resources.getColor(android.R.color.black))
                }
                return view
            }
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOptions.adapter = adapter

        tvGreeting.text = viewModel.greeting

        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val notify = switchNotify.isChecked
            val volume = seekBarVolume.progress
            
            var option = ""
            if (spinnerOptions.selectedItemPosition > 0) {
                option = spinnerOptions.selectedItem.toString()
            }

            viewModel.updateData(name, notify, volume, option)
            tvGreeting.text = viewModel.greeting
        }
    }
}
