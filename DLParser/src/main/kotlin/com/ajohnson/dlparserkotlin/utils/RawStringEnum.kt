package com.ajohnson.dlparserkotlin.utils

interface RawStringEnum {
    val rawValue: String
}

inline fun <reified T> from(value: String?): T?
        where T : Enum<T>, T : RawStringEnum {
    return enumValues<T>().firstOrNull { it.rawValue == value }
}
