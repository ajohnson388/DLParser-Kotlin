package com.ajohnson.dlparserkotlin.categories

/**
 * The AAMVA eye color types.
 */
enum class EyeColor(val rawValue: String) {

    BLACK("BLK"),
    BLUE("BLU"),
    BROWN("BRO"),
    GRAY("GRY"),
    GREEN("GRN"),
    HAZEL("HAZ"),
    MAROON("MAR"),
    PINK("PNK"),
    DICHROMATIC("DIC");

    companion object {
        fun of(rawValue: String): EyeColor? =
            EyeColor.values().firstOrNull { it.rawValue == rawValue }
    }
}