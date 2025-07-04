package com.ajohnson.dlparserkotlin.utils

import kotlin.math.round

/**
 * Utility functions used through out the framework.
 */
internal object Utils {

    fun inchesFromCentimeters(centimeters: Double): Double = round(0.393701 * centimeters)

    fun poundsFromKilograms(kilograms: Double): Double = round(2.20462 * kilograms)

    fun firstRegexMatch(pattern: String, data: String): String? {
        val regex = Regex(pattern, setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        val matchResult = regex.find(data) ?: return null
        val matchedGroup = matchResult.groups[1]?.value ?: return null
        val matchedString = matchedGroup.trim()
        return matchedString.ifEmpty { null }
    }
}