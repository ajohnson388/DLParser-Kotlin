package com.ajohnson.dlparserkotlin.categories

import com.ajohnson.dlparserkotlin.utils.RawStringEnum

/**
 * The AAMVA issuing country types.
 */
enum class IssuingCountry(override val rawValue: String): RawStringEnum {
    UNITED_STATES("USA"),
    CANADA("CAN");
}