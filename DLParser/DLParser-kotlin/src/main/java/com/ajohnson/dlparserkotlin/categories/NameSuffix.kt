package com.ajohnson.dlparserkotlin.categories

/**
 * The AAMVA hair color types.
 */
enum class NameSuffix(val rawValue: String, val alternateValue: String? = null) {

    JUNIOR("JR"),
    SENIOR("SR"),
    FIRST("1ST", "I"),
    SECOND("2ND", "II"),
    THIRD("3RD", "III"),
    FOURTH("4TH", "IV"),
    FIFTH("5TH", "V"),
    SIXTH("6TH", "VI"),
    SEVENTH("7TH", "VII"),
    EIGHTH("8TH", "VIII"),
    NINTH("9TH", "IX");

    companion object {
        fun of(rawValue: String): NameSuffix? = NameSuffix.values().firstOrNull {
            it.rawValue == rawValue || it.alternateValue == rawValue
        }
    }
}