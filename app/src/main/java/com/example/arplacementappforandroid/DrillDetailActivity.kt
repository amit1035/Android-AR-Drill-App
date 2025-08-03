package com.example.arplacementappforandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DrillDetailActivity : AppCompatActivity() {

    private lateinit var drillNameText: TextView
    private lateinit var drillImage: ImageView
    private lateinit var drillDescription: TextView
    private lateinit var drillTips: TextView
    private lateinit var startARButton: Button

    private var drillName: String = ""
    private var drillIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drill_detail)

        // Setup Toolbar as ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Drill Details"

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Get intent data
        drillName = intent.getStringExtra("DRILL_NAME") ?: "Unknown Drill"
        drillIndex = intent.getIntExtra("DRILL_INDEX", 0)

        initializeViews()

        // Set drill data based on index
        setupDrillData()

        setupClickListeners()
    }

    private fun initializeViews() {
        drillNameText = findViewById(R.id.drillNameText)
        drillImage = findViewById(R.id.drillImage)
        drillDescription = findViewById(R.id.drillDescription)
        drillTips = findViewById(R.id.drillTips)
        startARButton = findViewById(R.id.startARButton)
    }

    @SuppressLint("SetTextI18n")
    private fun setupDrillData() {
        drillNameText.text = drillName

        when (drillIndex) {
            0 -> {
                drillImage.setImageResource(R.drawable.home)
                drillDescription.text = """
                    Basic Drill - Perfect for beginners
                    
                    This drill focuses on fundamental movements and positioning. It's designed to help you build confidence and master the basic techniques before moving to advanced levels.
                    
                    Duration: 15-20 minutes
                    Difficulty: Beginner
                    Equipment: Basic setup required
                """.trimIndent()

                drillTips.text = """
                    💡 Tips:
                    • Start slowly and focus on form
                    • Maintain proper posture throughout
                    • Take breaks when needed
                    • Practice in a clear, open space
                    • Keep your movements controlled
                """.trimIndent()
            }
            1 -> {
                drillImage.setImageResource(R.drawable.home)
                drillDescription.text = """
                    Advanced Drill - For experienced practitioners
                    
                    This intermediate-level drill incorporates complex movements and requires good coordination. It builds upon basic skills and introduces new challenging elements.
                    
                    Duration: 25-30 minutes
                    Difficulty: Intermediate
                    Equipment: Advanced setup recommended
                """.trimIndent()

                drillTips.text = """
                    💡 Tips:
                    • Warm up thoroughly before starting
                    • Focus on smooth transitions
                    • Maintain consistent rhythm
                    • Use proper breathing techniques
                    • Challenge yourself but stay safe
                """.trimIndent()
            }
            2 -> {
                drillImage.setImageResource(R.drawable.home)
                drillDescription.text = """
                    Expert Drill - Master level challenge
                    
                    The most challenging drill designed for experts. This requires excellent technique, stamina, and mental focus. Only attempt after mastering previous levels.
                    
                    Duration: 35-45 minutes
                    Difficulty: Expert
                    Equipment: Professional setup required
                """.trimIndent()

                drillTips.text = """
                    💡 Tips:
                    • Ensure you're properly warmed up
                    • Have adequate space and safety measures
                    • Focus intensely on precision
                    • Listen to your body
                    • Consider having a spotter
                """.trimIndent()
            }

         }
    }

    private fun setupClickListeners() {
        startARButton.setOnClickListener {
            val intent = Intent(this, ARActivity::class.java)
            intent.putExtra("DRILL_NAME", drillName)
            intent.putExtra("DRILL_INDEX", drillIndex)
            startActivity(intent)
        }
    }
}
