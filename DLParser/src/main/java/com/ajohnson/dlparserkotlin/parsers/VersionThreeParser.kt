package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 03-2005.
 */
internal class VersionThreeParser(data: String): DLParser(data) {

    init {
        fields.remove(FieldKey.FIRST_NAME)
        fields.remove(FieldKey.MIDDLE_NAME)
        fields.remove(FieldKey.LAST_NAME_TRUNCATION)
        fields.remove(FieldKey.FIRST_NAME_TRUNCATION)
        fields.remove(FieldKey.MIDDLE_NAME_TRUNCATION)
        fields.remove(FieldKey.COMPLIANCE_TYPE)
        fields.remove(FieldKey.REVISION_DATE)
        fields.remove(FieldKey.HAZMAT_EXPIRATION_DATE)
        fields.remove(FieldKey.WEIGHT_POUNDS)
        fields.remove(FieldKey.WEIGHT_KILOGRAMS)
        fields.remove(FieldKey.IS_TEMPORARY_DOCUMENT)
        fields.remove(FieldKey.IS_ORGAN_DONOR)
        fields.remove(FieldKey.IS_VETERAN)
        fields.remove(FieldKey.DRIVER_LICENSE_NAME)
    }
}