package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 07-2010.
 */
internal class VersionFiveParser(data: String) : DLParser(data) {

    init {
        fields.remove(FieldKey.IS_ORGAN_DONOR)
        fields.remove(FieldKey.IS_VETERAN)
        fields.remove(FieldKey.FEDERAL_VEHICLE_CODE)
        fields.remove(FieldKey.DRIVER_LICENSE_NAME)
        fields.remove(FieldKey.GIVEN_NAME)
    }
}