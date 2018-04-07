package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 07-2009.
 */
class VersionFourParser: DLParser {
    
    constructor(data: String): super(data) {
        fields.remove(FieldKey.isOrganDonor)
        fields.remove(FieldKey.isVeteran)
        fields.remove(FieldKey.fVehicleCode)
        fields.remove(FieldKey.driverLicenseName)
        fields.remove(FieldKey.givenName)
    }
}