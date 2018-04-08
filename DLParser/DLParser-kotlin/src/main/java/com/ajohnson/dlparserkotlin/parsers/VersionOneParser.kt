package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.Utils
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
        fields[FieldKey.heightCentimeters] = "DAV"
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

    override val parsedHeight: Double? get() {
        // Check for cm
        parseString(FieldKey.heightCentimeters)?.toDoubleOrNull()?.let {
            return Utils.inchesFromCentimeters(it)
        }

        // Check for ft/in
        val rawHeight = parseString(FieldKey.heightInches)?.toIntOrNull() ?: return null
        val feet = rawHeight / 100
        val inches = rawHeight - (feet * 100)
        val totalInches = (feet * 12) + inches
        return totalInches.toDouble()
    }
}