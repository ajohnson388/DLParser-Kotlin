package com.ajohnson.dlparserkotlin

import kotlin.math.round

/**
 * Utility functions used through out the framework.
 */
internal object Utils {

    fun firstRegexMatch(pattern: String, data: String): String? {
        val regex = Regex(pattern, RegexOption.IGNORE_CASE)
        val match = regex.matchEntire(data)?.value
        return match?.trim()
    }
    
    fun inchesFromCentimeters(centimeters: Double): Double = round(0.393701 * centimeters)

    fun poundsFromKilograms(kilograms: Double): Double = round(2.20462 * kilograms)
}