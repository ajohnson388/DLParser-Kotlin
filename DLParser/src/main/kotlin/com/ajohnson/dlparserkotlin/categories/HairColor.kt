package com.ajohnson.dlparserkotlin.categories

import com.ajohnson.dlparserkotlin.utils.RawStringEnum

/**
 * The AAMVA hair color types.
 */
enum class HairColor(override val rawValue: String): RawStringEnum {
    BALD("BAL"),
    BLACK("BLK"),
    BLOND("BLN"),
    BROWN("BRO"),
    GREY("GRY"),
    RED("RED"),
    SANDY("SDY"),
    WHITE("WHI");
}