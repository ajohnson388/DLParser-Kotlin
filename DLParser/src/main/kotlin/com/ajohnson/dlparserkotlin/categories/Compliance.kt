package com.ajohnson.dlparserkotlin.categories

import com.ajohnson.dlparserkotlin.utils.RawStringEnum

enum class Compliance(override val rawValue: String): RawStringEnum {
    FULLY_COMPLIANT("F"),
    MATERIALLY_COMPLIANT("M"),
    NON_COMPLIANT("N");
}