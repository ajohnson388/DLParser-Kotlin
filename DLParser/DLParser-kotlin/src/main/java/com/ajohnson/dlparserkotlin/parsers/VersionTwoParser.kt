package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 09-2003.
 */
class VersionTwoParser: DLParser {
    
    constructor(data: String): super(data) {
        fields.remove(FieldKey.firstName)
        fields.remove(FieldKey.middleName)
        fields.remove(FieldKey.lastNameTruncation)
        fields.remove(FieldKey.firstNameTruncation)
        fields.remove(FieldKey.middleNameTruncation)
        fields.remove(FieldKey.lastNameAlias)
        fields.remove(FieldKey.givenNameAlias)
        fields.remove(FieldKey.suffixAlias)
        fields.remove(FieldKey.complianceType)
        fields.remove(FieldKey.revisionDate)
        fields.remove(FieldKey.hazmatExpirationDate)
        fields.remove(FieldKey.weightPounds)
        fields.remove(FieldKey.weightKilograms)
        fields.remove(FieldKey.isTemporaryDocument)
        fields.remove(FieldKey.isOrganDonor)
        fields.remove(FieldKey.isVeteran)
        fields.remove(FieldKey.driverLicenseName)
    }

    override val canadaDateFormat = "MMddyyyy"
}