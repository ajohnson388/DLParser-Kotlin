package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 2025.
 */
internal class VersionElevenParser(data: String) : DLParser(data) {

    init {
        fields.remove(FieldKey.FEDERAL_VEHICLE_CODE)
        fields.remove(FieldKey.DRIVER_LICENSE_NAME)
        fields.remove(FieldKey.GIVEN_NAME)
        fields.remove(FieldKey.LAST_NAME_ALIAS)
        fields.remove(FieldKey.GIVEN_NAME_ALIAS)
        fields.remove(FieldKey.SUFFIX_ALIAS)
        fields.remove(FieldKey.RACE)
        fields.remove(FieldKey.HAZMAT_EXPIRATION_DATE)
        fields[FieldKey.IS_COMMERCIAL] = "DDM"
        fields[FieldKey.IS_NON_DOMICILE] = "DDN"
        fields[FieldKey.IS_ENHANCED_CREDENTIAL] = "DDO"
        fields[FieldKey.IS_PERMIT] = "DDP"
    }
}