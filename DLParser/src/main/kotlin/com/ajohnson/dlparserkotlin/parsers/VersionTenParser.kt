package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 2020.
 */
internal class VersionTenParser(data: String) : DLParser(data) {

    init {
        fields.remove(FieldKey.FEDERAL_VEHICLE_CODE)
        fields.remove(FieldKey.DRIVER_LICENSE_NAME)
        fields.remove(FieldKey.GIVEN_NAME)
    }
}