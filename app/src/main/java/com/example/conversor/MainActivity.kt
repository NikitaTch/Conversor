package com.example.conversor

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerCategory: Spinner
    private lateinit var imageViewCategory: ImageView
    private lateinit var btnNext: Button
    private lateinit var coordinatorMain: CoordinatorLayout

    private var selectedCategory: String = "Distance"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCategory = findViewById(R.id.spinnerCategory)
        imageViewCategory = findViewById(R.id.imageViewCategory)
        btnNext = findViewById(R.id.btnNext)
        coordinatorMain = findViewById(R.id.coordinatorMain)

        setupCategorySpinner()

        btnNext.setOnClickListener {
            if (selectedCategory.isBlank()) {
                Snackbar.make(coordinatorMain, "Please select a conversion category", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Snackbar.make(coordinatorMain, "Selected: $selectedCategory", Snackbar.LENGTH_SHORT).show()

            val intent = Intent(this, ConversionActivity::class.java).apply {
                putExtra("EXTRA_CATEGORY", selectedCategory)
            }
            startActivity(intent)
        }
    }

    private fun setupCategorySpinner() {
        val categories = ConversionLogic.categories
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCategory = categories[position]
                updateCategoryBitmap(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun updateCategoryBitmap(category: String) {
        val drawableRes = when (category) {
            "Distance" -> R.drawable.cat_distance
            "Temperature" -> R.drawable.cat_temperature
            "Volume" -> R.drawable.cat_volume
            "Mass and Weight" -> R.drawable.cat_mass
            "Time" -> R.drawable.cat_time
            "Digital Information" -> R.drawable.cat_digital
            "Currency" -> R.drawable.cat_currency
            "Area" -> R.drawable.cat_area
            "Speed" -> R.drawable.cat_speed
            "Pressure" -> R.drawable.cat_pressure
            else -> R.drawable.cat_distance
        }

        // Using Bitmap class to load, manage, and display the image
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, drawableRes)
        imageViewCategory.setImageBitmap(bitmap)
    }
}
