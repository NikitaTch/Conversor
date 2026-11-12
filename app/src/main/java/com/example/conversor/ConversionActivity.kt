package com.example.conversor

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class ConversionActivity : AppCompatActivity() {

    private lateinit var tvCategoryTitle: TextView
    private lateinit var etInputValue: EditText
    private lateinit var spinnerSourceUnit: Spinner
    private lateinit var spinnerTargetUnit: Spinner
    private lateinit var btnConvert: Button
    private lateinit var tvResult: TextView
    private lateinit var btnBack: Button
    private lateinit var coordinatorConversion: CoordinatorLayout

    private var category: String = "Distance"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)

        category = intent.getStringExtra("EXTRA_CATEGORY") ?: "Distance"

        tvCategoryTitle = findViewById(R.id.tvCategoryTitle)
        etInputValue = findViewById(R.id.etInputValue)
        spinnerSourceUnit = findViewById(R.id.spinnerSourceUnit)
        spinnerTargetUnit = findViewById(R.id.spinnerTargetUnit)
        btnConvert = findViewById(R.id.btnConvert)
        tvResult = findViewById(R.id.tvResult)
        btnBack = findViewById(R.id.btnBack)
        coordinatorConversion = findViewById(R.id.coordinatorConversion)

        tvCategoryTitle.text = "Category: $category"

        setupSpinners()

        btnConvert.setOnClickListener {
            performConversion()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupSpinners() {
        val units = ConversionLogic.getUnitsForCategory(category)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerSourceUnit.adapter = adapter
        spinnerTargetUnit.adapter = adapter

        if (units.size > 1) {
            spinnerTargetUnit.setSelection(1)
        }
    }

    private fun performConversion() {
        val inputStr = etInputValue.text.toString().trim()
        if (inputStr.isEmpty()) {
            Snackbar.make(coordinatorConversion, "Please enter a value to convert", Snackbar.LENGTH_SHORT).show()
            return
        }

        val inputValue = inputStr.toDoubleOrNull()
        if (inputValue == null) {
            Snackbar.make(coordinatorConversion, "Invalid input value entered", Snackbar.LENGTH_SHORT).show()
            return
        }

        val fromUnit = spinnerSourceUnit.selectedItem?.toString() ?: ""
        val toUnit = spinnerTargetUnit.selectedItem?.toString() ?: ""

        if (fromUnit.isEmpty() || toUnit.isEmpty()) {
            Snackbar.make(coordinatorConversion, "Please select both source and target units", Snackbar.LENGTH_SHORT).show()
            return
        }

        val result = ConversionLogic.convert(category, inputValue, fromUnit, toUnit)
        tvResult.text = String.format(Locale.getDefault(), "Result: %.4f %s", result, toUnit)

        Snackbar.make(coordinatorConversion, "Conversion completed successfully", Snackbar.LENGTH_SHORT).show()
    }
}
