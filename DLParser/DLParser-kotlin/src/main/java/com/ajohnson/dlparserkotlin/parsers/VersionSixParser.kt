package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 07-2011.
 */
class VersionSixParser: DLParser {

    constructor(data: String): super(data) {
        fields.remove(FieldKey.isVeteran)
        fields.remove(FieldKey.fVehicleCode)
        fields.remove(FieldKey.driverLicenseName)
        fields.remove(FieldKey.givenName)
    }
}