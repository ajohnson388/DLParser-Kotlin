package com.ajohnson.dlparserkotlin.categories

/**
 * The AAMVA gender types.
 */
enum class Gender(val rawValue: String) {

    MALE("1"),
    FEMALE("2");

    companion object {
        fun of(rawValue: String): Gender? =
            Gender.values().firstOrNull { it.rawValue == rawValue }
    }
}