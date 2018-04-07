package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Created by ajohnson on 4/7/18.
 */
class VersionNineParser: DLParser {

    constructor(data: String): super(data) {
        fields.remove(FieldKey.fVehicleCode)
        fields.remove(FieldKey.driverLicenseName)
        fields.remove(FieldKey.givenName)
    }
}