package com.ajohnson.dlparserkotlin.categories


/**
 * The AAMVA truncation types.
 */
enum class Truncation(val rawValue: String) {

    TRUNCATED("T"),
    NONE("N");

    companion object {
        fun of(rawValue: String): Truncation? =
                Truncation.values().firstOrNull { it.rawValue == rawValue }
    }
}