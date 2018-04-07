package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 2000.
 */
class VersionOneParser: DLParser {

    constructor(data: String): super(data) {
        fields.remove(FieldKey.jVehicleClass)
        fields.remove(FieldKey.jRestrictionCode)
        fields.remove(FieldKey.jEndorsementCode)
        fields[FieldKey.lastName] = "DAB"
        fields[FieldKey.uniqueDocumentId] = "DBJ"
        fields.remove(FieldKey.country) // TODO: No documentation?
        fields.remove(FieldKey.lastNameTruncation)
        fields.remove(FieldKey.firstNameTruncation)
        fields.remove(FieldKey.middleNameTruncation)
        fields.remove(FieldKey.placeOfBirth)
        fields.remove(FieldKey.auditInformation)
        fields.remove(FieldKey.inventoryControlNumber)
        fields[FieldKey.lastNameAlias] = "DBO"
        fields[FieldKey.givenNameAlias] = "DBP"
        fields[FieldKey.suffixAlias] = "DBR"
        fields[FieldKey.suffix] = "DAE"
        fields.remove(FieldKey.weightRange)
        fields.remove(FieldKey.race)
        fields[FieldKey.sVehicleCode] = "PAA"
        fields[FieldKey.sEndorsementCode] = "PAF"
        fields[FieldKey.sRestrictionCode] = "PAE"
        fields.remove(FieldKey.jVehicleClassDescription)
        fields.remove(FieldKey.jEndorsementCodeDescription)
        fields.remove(FieldKey.jRestrictionCodeDescription)
        fields.remove(FieldKey.complianceType)
        fields.remove(FieldKey.revisionDate)
        fields.remove(FieldKey.hazmatExpirationDate)
        fields.remove(FieldKey.isTemporaryDocument)
        fields[FieldKey.isOrganDonor] = "DBH"
        fields.remove(FieldKey.isVeteran)
    }

    override val unitedStatesDateFormat = "yyyyMMdd"
}