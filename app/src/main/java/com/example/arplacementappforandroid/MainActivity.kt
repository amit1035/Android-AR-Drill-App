package com.example.arplacementappforandroid

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var drillSpinner: Spinner
    private lateinit var startButton: Button

    // Mock drill data
    private val drillList = arrayOf("Drill 1 - Basic", "Drill 2 - Advanced", "Drill 3 - Expert")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupSpinner()
        setupClickListeners()
    }

    private fun initializeViews() {
        drillSpinner = findViewById(R.id.drillSpinner)
        startButton = findViewById(R.id.startDrillBtn)
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, drillList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        drillSpinner.adapter = adapter
    }

    private fun setupClickListeners() {
        startButton.setOnClickListener {
            val selectedDrill = drillSpinner.selectedItem.toString()
            val selectedIndex = drillSpinner.selectedItemPosition

            // Go to drill detail page first
            val intent = Intent(this, DrillDetailActivity::class.java)
            intent.putExtra("DRILL_NAME", selectedDrill)
            intent.putExtra("DRILL_INDEX", selectedIndex)
            startActivity(intent)
        }
    }
}