package com.ajohnson.dlparserkotlin.categories

import com.ajohnson.dlparserkotlin.utils.RawStringEnum

/**
 * The AAMVA race types.
 */
enum class Race(override val rawValue: String): RawStringEnum {
    AMERICAN_INDIAN("AL"),
    ASIAN("AP"),
    BLACK("BK"),
    HISPANIC("H"),
    NON_HISPANIC("O"),
    UNKNOWN("U"),
    WHITE("W");
}