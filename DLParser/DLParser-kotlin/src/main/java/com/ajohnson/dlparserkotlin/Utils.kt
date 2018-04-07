package com.ajohnson.dlparserkotlin

/**
 * Utility functions used through out the framework.
 */
object Utils {

    fun firstRegexMatch(pattern: String, data: String): String? {
        val regex = Regex(pattern, RegexOption.IGNORE_CASE)
        val match = regex.matchEntire(data)?.value
        return match?.trim()
    }
}