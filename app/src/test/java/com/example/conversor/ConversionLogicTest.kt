package com.example.conversor

import org.junit.Assert.assertEquals
import org.junit.Test

class ConversionLogicTest {

    @Test
    fun testDistanceConversion() {
        // 1 kilometre to metres should be 1000.0
        val result = ConversionLogic.convert("Distance", 1.0, "Kilometres", "Metres")
        assertEquals(1000.0, result, 0.001)
    }

    @Test
    fun testTemperatureConversion() {
        // 0 Celsius to Fahrenheit should be 32.0
        val fahrenheit = ConversionLogic.convert("Temperature", 0.0, "Celsius", "Fahrenheit")
        assertEquals(32.0, fahrenheit, 0.001)

        // 100 Celsius to Kelvin should be 373.15
        val kelvin = ConversionLogic.convert("Temperature", 100.0, "Celsius", "Kelvin")
        assertEquals(373.15, kelvin, 0.001)
    }

    @Test
    fun testMassConversion() {
        // 1 Kilogram to Grams should be 1000.0
        val grams = ConversionLogic.convert("Mass and Weight", 1.0, "Kilograms", "Grams")
        assertEquals(1000.0, grams, 0.001)
    }
}
