package com.example.conversor

object ConversionLogic {

    val categories = listOf(
        "Distance",
        "Temperature",
        "Volume",
        "Mass and Weight",
        "Time",
        "Digital Information",
        "Currency",
        "Area",
        "Speed",
        "Pressure"
    )

    private val unitsMap = mapOf(
        "Distance" to listOf("Metres", "Kilometres", "Miles", "Feet", "Inches", "Centimetres", "Millimetres"),
        "Temperature" to listOf("Celsius", "Fahrenheit", "Kelvin"),
        "Volume" to listOf("Litres", "Millilitres", "Cubic Metres", "Gallons", "Pints"),
        "Mass and Weight" to listOf("Grams", "Kilograms", "Milligrams", "Pounds", "Ounces"),
        "Time" to listOf("Seconds", "Minutes", "Hours", "Days", "Milliseconds"),
        "Digital Information" to listOf("Bits", "Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes"),
        "Currency" to listOf("Euros", "US Dollars", "British Pounds", "Japanese Yen"),
        "Area" to listOf("Square Metres", "Square Kilometres", "Hectares", "Acres", "Square Feet"),
        "Speed" to listOf("Metres per Second", "Kilometres per Hour", "Miles per Hour", "Knots"),
        "Pressure" to listOf("Pascals", "Kilopascals", "Bars", "Atmospheres", "PSI")
    )

    // Conversion factors relative to base unit for each category
    private val factorsMap = mapOf(
        "Distance" to mapOf(
            "Metres" to 1.0,
            "Kilometres" to 1000.0,
            "Miles" to 1609.344,
            "Feet" to 0.3048,
            "Inches" to 0.0254,
            "Centimetres" to 0.01,
            "Millimetres" to 0.001
        ),
        "Volume" to mapOf(
            "Litres" to 1.0,
            "Millilitres" to 0.001,
            "Cubic Metres" to 1000.0,
            "Gallons" to 3.78541,
            "Pints" to 0.473176
        ),
        "Mass and Weight" to mapOf(
            "Grams" to 1.0,
            "Kilograms" to 1000.0,
            "Milligrams" to 0.001,
            "Pounds" to 453.592,
            "Ounces" to 28.3495
        ),
        "Time" to mapOf(
            "Seconds" to 1.0,
            "Minutes" to 60.0,
            "Hours" to 3600.0,
            "Days" to 86400.0,
            "Milliseconds" to 0.001
        ),
        "Digital Information" to mapOf(
            "Bits" to 1.0,
            "Bytes" to 8.0,
            "Kilobytes" to 8192.0,
            "Megabytes" to 8388608.0,
            "Gigabytes" to 8.589934592E9,
            "Terabytes" to 8.796093022208E12
        ),
        "Currency" to mapOf(
            "Euros" to 1.0,
            "US Dollars" to 0.92,
            "British Pounds" to 1.18,
            "Japanese Yen" to 0.0061
        ),
        "Area" to mapOf(
            "Square Metres" to 1.0,
            "Square Kilometres" to 1000000.0,
            "Hectares" to 10000.0,
            "Acres" to 4046.86,
            "Square Feet" to 0.092903
        ),
        "Speed" to mapOf(
            "Metres per Second" to 1.0,
            "Kilometres per Hour" to 0.277778,
            "Miles per Hour" to 0.44704,
            "Knots" to 0.514444
        ),
        "Pressure" to mapOf(
            "Pascals" to 1.0,
            "Kilopascals" to 1000.0,
            "Bars" to 100000.0,
            "Atmospheres" to 101325.0,
            "PSI" to 6894.76
        )
    )

    fun getUnitsForCategory(category: String): List<String> {
        return unitsMap[category] ?: emptyList()
    }

    fun convert(category: String, value: Double, fromUnit: String, toUnit: String): Double {
        if (category == "Temperature") {
            return convertTemperature(value, fromUnit, toUnit)
        }

        val categoryFactors = factorsMap[category] ?: return value
        val fromFactor = categoryFactors[fromUnit] ?: 1.0
        val toFactor = categoryFactors[toUnit] ?: 1.0

        val baseValue = value * fromFactor
        return baseValue / toFactor
    }

    private fun convertTemperature(value: Double, fromUnit: String, toUnit: String): Double {
        // Convert to Celsius first
        val celsius = when (fromUnit) {
            "Celsius" -> value
            "Fahrenheit" -> (value - 32.0) * 5.0 / 9.0
            "Kelvin" -> value - 273.15
            else -> value
        }

        // Convert from Celsius to target
        return when (toUnit) {
            "Celsius" -> celsius
            "Fahrenheit" -> (celsius * 9.0 / 5.0) + 32.0
            "Kelvin" -> celsius + 273.15
            else -> celsius
        }
    }
}
