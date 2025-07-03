package com.ajohnson.dlparserkotlin.categories

/**
 * The AAMVA parsed weight ranges
 */
data class WeightRange(var rank: Int) {

    val kilograms: ClosedRange<Int> = when (rank) {
        1 -> 32..45
        2 -> 46..59
        3 -> 60..70
        4 -> 71..86
        5 -> 87..100
        6 -> 101..113
        7 -> 114..127
        8 -> 128..145
        9 -> 146..Int.MAX_VALUE
        else -> 0..31
    }

    var pounds: ClosedRange<Int> = when (rank) {
        1 -> 71..100
        2 -> 101..130
        3 -> 131..160
        4 -> 161..190
        5 -> 191..220
        6 -> 221..250
        7 -> 251..280
        8 -> 281..320
        9 -> 321..Int.MAX_VALUE
        else -> 0..70
    }
}