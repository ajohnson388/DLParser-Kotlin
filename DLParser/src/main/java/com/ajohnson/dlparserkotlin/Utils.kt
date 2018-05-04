package com.ajohnson.dlparserkotlin

import kotlin.math.round

/**
 * Utility functions used through out the framework.
 */
internal object Utils {

    fun inchesFromCentimeters(centimeters: Double): Double = round(0.393701 * centimeters)

    fun poundsFromKilograms(kilograms: Double): Double = round(2.20462 * kilograms)
}