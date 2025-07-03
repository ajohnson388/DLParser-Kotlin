package com.ajohnson.dlparserkotlin.categories

import com.ajohnson.dlparserkotlin.utils.RawStringEnum

/**
 * The AAMVA eye color types.
 */
enum class EyeColor(override val rawValue: String): RawStringEnum {
    BLACK("BLK"),
    BLUE("BLU"),
    BROWN("BRO"),
    GRAY("GRY"),
    GREEN("GRN"),
    HAZEL("HAZ"),
    MAROON("MAR"),
    PINK("PNK"),
    DICHROMATIC("DIC");
}