package com.ajohnson.dlparserkotlin.categories

/**
 * The AAMVA issuing country types.
 */
enum class IssuingCountry(val rawValue: String) {

    UNITED_STATES("USA"),
    CANADA("CAN");

    companion object {
        fun of(rawValue: String): IssuingCountry? =
            IssuingCountry.values().firstOrNull { it.rawValue == rawValue }
    }
}