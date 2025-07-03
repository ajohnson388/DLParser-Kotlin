package com.ajohnson.dlparserkotlin.categories

import com.ajohnson.dlparserkotlin.utils.RawStringEnum

/**
 * The AAMVA truncation types.
 */
enum class Truncation(override val rawValue: String): RawStringEnum {
    TRUNCATED("T"),
    NONE("N");
}