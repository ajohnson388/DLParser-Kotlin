package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.utils.Utils
import com.ajohnson.dlparserkotlin.categories.NameSuffix
import com.ajohnson.dlparserkotlin.models.FieldKey

/**
 * Published 2000.
 */
internal class VersionOneParser(data: String) : DLParser(data) {

    init {
        fields.remove(FieldKey.JURISDICTION_VEHICLE_CLASS)
        fields.remove(FieldKey.JURISDICTION_RESTRICTION_CODE)
        fields.remove(FieldKey.JURISDICTION_ENDORSEMENT_CODE)
        fields[FieldKey.LAST_NAME] = "DAB"
        fields[FieldKey.UNIQUE_DOCUMENT_ID] = "DBJ"
        fields.remove(FieldKey.COUNTRY)
        fields.remove(FieldKey.LAST_NAME_TRUNCATION)
        fields.remove(FieldKey.FIRST_NAME_TRUNCATION)
        fields.remove(FieldKey.MIDDLE_NAME_TRUNCATION)
        fields.remove(FieldKey.PLACE_OF_BIRTH)
        fields.remove(FieldKey.AUDIT_INFORMATION)
        fields.remove(FieldKey.INVENTORY_CONTROL_NUMBER)
        fields[FieldKey.LAST_NAME_ALIAS] = "DBO"
        fields[FieldKey.GIVEN_NAME_ALIAS] = "DBP"
        fields[FieldKey.SUFFIX_ALIAS] = "DBR"
        fields[FieldKey.SUFFIX] = "DAE"
        fields[FieldKey.HEIGHT_CENTIMETERS] = "DAV"
        fields.remove(FieldKey.WEIGHT_RANGE)
        fields.remove(FieldKey.RACE)
        fields[FieldKey.STANDARD_VEHICLE_CODE] = "PAA"
        fields[FieldKey.STANDARD_ENDORSEMENT_CODE] = "PAF"
        fields[FieldKey.STANDARD_RESTRICTION_CODE] = "PAE"
        fields.remove(FieldKey.JURISDICTION_VEHICLE_CLASS_DESCRIPTION)
        fields.remove(FieldKey.JURISDICTION_ENDORSEMENT_CODE_DESCRIPTION)
        fields.remove(FieldKey.JURISDICTION_RESTRICTION_CODE_DESCRIPTION)
        fields.remove(FieldKey.COMPLIANCE_TYPE)
        fields.remove(FieldKey.REVISION_DATE)
        fields.remove(FieldKey.HAZMAT_EXPIRATION_DATE)
        fields.remove(FieldKey.IS_TEMPORARY_DOCUMENT)
        fields[FieldKey.IS_ORGAN_DONOR] = "DBH"
        fields.remove(FieldKey.IS_VETERAN)
        fields.remove(FieldKey.UNDER_EIGHTEEN_UNTIL_DATE)
        fields.remove(FieldKey.UNDER_NINETEEN_UNTIL_DATE)
        fields.remove(FieldKey.UNDER_TWENTY_ONE_UNTIL_DATE)
    }

    override val unitedStatesDateFormat = "yyyyMMdd"

    override val parsedHeight: Double?
        get() {
            // Check for cm
            parseString(FieldKey.HEIGHT_CENTIMETERS)?.toDoubleOrNull()?.let {
                return Utils.inchesFromCentimeters(it)
            }

            // Check for ft/in
            val rawHeight = parseString(FieldKey.HEIGHT_INCHES)?.toIntOrNull() ?: return null
            val feet = rawHeight / 100
            val inches = rawHeight - (feet * 100)
            val totalInches = (feet * 12) + inches
            return totalInches.toDouble()
        }

    override val parsedNameSuffix: NameSuffix?
        get() {
            var suffix = parseString(FieldKey.SUFFIX)
            if (suffix == null) {
                val driverLicenseName = parseString(FieldKey.DRIVER_LICENSE_NAME) ?: return null
                val nameComponents = driverLicenseName.split(",")
                suffix = if (nameComponents.size >= 4) nameComponents[3] else return null
            }
            return NameSuffix.of(suffix)
        }
}