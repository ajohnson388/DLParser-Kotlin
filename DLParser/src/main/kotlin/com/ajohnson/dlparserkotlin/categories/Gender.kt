package com.ajohnson.dlparserkotlin.categories

import com.ajohnson.dlparserkotlin.utils.RawStringEnum

/**
 * The AAMVA gender types.
 */
enum class Gender(override val rawValue: String): RawStringEnum {
    MALE("1"),
    FEMALE("2");
}