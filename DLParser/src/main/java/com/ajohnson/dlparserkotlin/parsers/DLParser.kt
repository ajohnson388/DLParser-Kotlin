package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.Utils
import com.ajohnson.dlparserkotlin.categories.*
import com.ajohnson.dlparserkotlin.models.FieldKey
import com.ajohnson.dlparserkotlin.models.License
import java.text.SimpleDateFormat
import java.util.*

/**
 * The primary class for parsing driver license data. This class automatically parses based on the
 * version number
 */
open class DLParser(val data: String) {

    /**
     * The base fields common to all or most version standards. This
     * field should be modified in subclasses for version-specific
     * field changes.
     * */
    internal val fields: MutableMap<FieldKey, String> = mutableMapOf(
            FieldKey.JURISDICTION_VEHICLE_CLASS to "DCA",
            FieldKey.JURISDICTION_RESTRICTION_CODE to "DCB",
            FieldKey.JURISDICTION_ENDORSEMENT_CODE to "DCD",
            FieldKey.EXPIRATION_DATE to "DBA",
            FieldKey.ISSUE_DATE to "DBD",
            FieldKey.FIRST_NAME to "DAC",
            FieldKey.MIDDLE_NAME to "DAD",
            FieldKey.LAST_NAME to "DCS",
            FieldKey.BIRTH_DATE to "DBB",
            FieldKey.GENDER to "DBC",
            FieldKey.EYE_COLOR to "DAY",
            FieldKey.HEIGHT_INCHES to "DAU",
            FieldKey.STREET_ADDRESS to "DAG",
            FieldKey.CITY to "DAI",
            FieldKey.STATE to "DAJ",
            FieldKey.POSTAL_CODE to "DAK",
            FieldKey.DRIVER_LICENSE_NUMBER to "DAQ",
            FieldKey.UNIQUE_DOCUMENT_ID to "DCF",
            FieldKey.COUNTRY to "DCG",
            FieldKey.LAST_NAME_TRUNCATION to "DDE",
            FieldKey.FIRST_NAME_TRUNCATION to "DDF",
            FieldKey.MIDDLE_NAME_TRUNCATION to "DDG",
            FieldKey.STREET_ADDRESS_TWO to "DAH",
            FieldKey.HAIR_COLOR to "DAZ",
            FieldKey.PLACE_OF_BIRTH to "DCI",
            FieldKey.AUDIT_INFORMATION to "DCJ",
            FieldKey.INVENTORY_CONTROL_NUMBER to "DCK",
            FieldKey.LAST_NAME_ALIAS to "DBN",
            FieldKey.GIVEN_NAME_ALIAS to "DBG",
            FieldKey.SUFFIX to "DBS", //.name toDO toOr DCU
            FieldKey.WEIGHT_RANGE to "DCE",
            FieldKey.RACE to "DCL",
            FieldKey.STANDARD_VEHICLE_CODE to "DCM",
            FieldKey.STANDARD_ENDORSEMENT_CODE to "DCN",
            FieldKey.STANDARD_RESTRICTION_CODE to "DCO",
            FieldKey.JURISDICTION_VEHICLE_CLASS_DESCRIPTION to "DCP",
            FieldKey.JURISDICTION_ENDORSEMENT_CODE_DESCRIPTION to "DCQ",
            FieldKey.JURISDICTION_RESTRICTION_CODE_DESCRIPTION to "DCR",
            FieldKey.COMPLIANCE_TYPE to "DDA",
            FieldKey.REVISION_DATE to "DDB",
            FieldKey.HAZMAT_EXPIRATION_DATE to "DDC",
            FieldKey.WEIGHT_POUNDS to "DAW",
            FieldKey.WEIGHT_KILOGRAMS to "DAX",
            FieldKey.IS_TEMPORARY_DOCUMENT to "DDD",
            FieldKey.IS_ORGAN_DONOR to "DDK",
            FieldKey.IS_VETERAN to "DDL",
            FieldKey.FEDERAL_VEHICLE_CODE to "DCH",
            FieldKey.DRIVER_LICENSE_NAME to "DAA",
            FieldKey.GIVEN_NAME to "DCT")

    /**
     * The version number detected in the driver license data or nil
     * if the data is not AAMVA compliant.
     */
//    val versionNumber get() = Utils.firstRegexMatch("\\d{6}(\\d{2})\\w+", data)?.toInt()
    val versionNumber: Int?
        get() {
            val matches = Regex("\\D(\\d{6}(\\d{2}))").find(data)
            val value = matches?.value
            return value?.substring(7)?.toIntOrNull()
        }

    /**
    The number of subfiles found in the driver license data.
     */
    private val subfileCount get() = Regex("\\d{8}(\\d{2})\\w+").find(data)?.value?.toInt()

    protected open val unitedStatesDateFormat get() = "MMddyyyy"
    protected open val canadaDateFormat get() = "yyyyMMdd"

    private val dateFormat
        get() = when (parsedCountry) {
            IssuingCountry.UNITED_STATES -> unitedStatesDateFormat
            IssuingCountry.CANADA -> canadaDateFormat
            else -> unitedStatesDateFormat
        }

    private val versionParser
        get() = when (versionNumber) {
            1 -> VersionOneParser(data)
            2 -> VersionTwoParser(data)
            3 -> VersionThreeParser(data)
            4 -> VersionFourParser(data)
            5 -> VersionFiveParser(data)
            6 -> VersionSixParser(data)
            7 -> VersionSevenParser(data)
            8 -> VersionEightParser(data)
            9 -> VersionNineParser(data)
            else -> DLParser(data)
        }

    fun parse(): License {
        val version = versionNumber
        val parser = versionParser
        return License(
                parser.parsedFirstName,
                parser.parsedMiddleNames,
                parser.parsedLastName,
                parser.parseString(FieldKey.FIRST_NAME_ALIAS),
                parser.parseString(FieldKey.GIVEN_NAME_ALIAS),
                parser.parseString(FieldKey.LAST_NAME_ALIAS),
                parser.parseString(FieldKey.SUFFIX_ALIAS),
                parser.parsedNameSuffix,
                parser.parseTruncation(FieldKey.FIRST_NAME_TRUNCATION),
                parser.parseTruncation(FieldKey.MIDDLE_NAME_TRUNCATION),
                parser.parseTruncation(FieldKey.LAST_NAME_TRUNCATION),
                parser.parseDate(FieldKey.EXPIRATION_DATE),
                parser.parseDate(FieldKey.ISSUE_DATE),
                parser.parseDate(FieldKey.BIRTH_DATE),
                parser.parseDate(FieldKey.HAZMAT_EXPIRATION_DATE),
                parser.parseDate(FieldKey.REVISION_DATE),
                parser.parseString(FieldKey.RACE),
                parser.parsedGender,
                parser.parsedEyeColor,
                parser.parsedHeight,
                parser.parsedWeight,
                parser.parsedHairColor,
                parser.parseString(FieldKey.PLACE_OF_BIRTH),
                parser.parseString(FieldKey.STREET_ADDRESS),
                parser.parseString(FieldKey.STREET_ADDRESS_TWO),
                parser.parseString(FieldKey.CITY),
                parser.parseString(FieldKey.STATE),
                parser.parsedPostalCode,
                parser.parsedCountry,
                parser.parseString(FieldKey.DRIVER_LICENSE_NUMBER),
                parser.parseString(FieldKey.UNIQUE_DOCUMENT_ID),
                parser.parseString(FieldKey.AUDIT_INFORMATION),
                parser.parseString(FieldKey.INVENTORY_CONTROL_NUMBER),
                parser.parseString(FieldKey.COMPLIANCE_TYPE),
                parser.parseBoolean(FieldKey.IS_ORGAN_DONOR),
                parser.parseBoolean(FieldKey.IS_VETERAN),
                parser.parseBoolean(FieldKey.IS_TEMPORARY_DOCUMENT),
                parser.parseString(FieldKey.FEDERAL_VEHICLE_CODE),
                parser.parseString(FieldKey.STANDARD_VEHICLE_CODE),
                parser.parseString(FieldKey.STANDARD_RESTRICTION_CODE),
                parser.parseString(FieldKey.STANDARD_ENDORSEMENT_CODE),
                parser.parseString(FieldKey.JURISDICTION_VEHICLE_CLASS),
                parser.parseString(FieldKey.JURISDICTION_RESTRICTION_CODE),
                parser.parseString(FieldKey.JURISDICTION_ENDORSEMENT_CODE),
                parser.parseString(FieldKey.JURISDICTION_VEHICLE_CLASS_DESCRIPTION),
                parser.parseString(FieldKey.JURISDICTION_RESTRICTION_CODE_DESCRIPTION),
                parser.parseString(FieldKey.JURISDICTION_ENDORSEMENT_CODE_DESCRIPTION),
                version,
                data
        )
    }

    internal open fun parseString(key: FieldKey): String? {
        fields[key]?.let {
            return Regex("$it(.+)\\b").find(data)?.value?.removePrefix(it)
        } ?: return null
    }

    internal open fun parseDouble(key: FieldKey): Double? {
        fields[key]?.let {
            return Regex("$it(\\w+)\\b").find(data)?.value?.removePrefix(it)?.toDoubleOrNull()
        } ?: return null
    }

    internal open fun parseDate(key: FieldKey): Date? {
        val dateString = parseString(key)
        if (dateString.isNullOrEmpty()) return null
        return SimpleDateFormat(dateFormat, Locale.US).parse(dateString)
    }

    internal open fun parseBoolean(key: FieldKey): Boolean? {
        val rawValue = parseString(key) ?: return null
        return rawValue == "1"
    }

    protected open val parsedFirstName
        get() =
            parseString(FieldKey.FIRST_NAME)
                    ?: parseString(FieldKey.GIVEN_NAME)?.split(",")?.lastOrNull()?.trim()
                    ?: parseString(FieldKey.DRIVER_LICENSE_NAME)?.split(",")?.lastOrNull()?.trim()

    protected open val parsedMiddleNames: List<String>
        get() {
            parseString(FieldKey.MIDDLE_NAME)?.let {
                return listOf(it)
            }

            parseString(FieldKey.GIVEN_NAME)?.let {
                val parts = it.split(",")
                return parts.drop(0).map { it.trim() }
            }

            parseString(FieldKey.DRIVER_LICENSE_NAME)?.let {
                val parts = it.split(",")
                return parts.drop(0).dropLast(0).map { it.trim() }
            }
            return listOf()
        }

    protected open val parsedLastName
        get() = parseString(FieldKey.LAST_NAME)
                ?: parseString(FieldKey.DRIVER_LICENSE_NAME)?.split(",")?.lastOrNull()?.trim()

    protected open val parsedNameSuffix: NameSuffix?
        get() {
            return NameSuffix.of(parseString(FieldKey.SUFFIX) ?: return null)
        }

    internal open fun parseTruncation(key: FieldKey): Truncation? {
        parseString(key)?.let {
            return Truncation.of(it)
        } ?: return null
    }

    protected open val parsedCountry: IssuingCountry?
        get() {
            return IssuingCountry.of(parseString(FieldKey.COUNTRY) ?: return null)
        }

    protected open val parsedGender: Gender?
        get() {
            return Gender.of(parseString(FieldKey.GENDER) ?: return null)
        }

    protected open val parsedEyeColor: EyeColor?
        get() {
            return EyeColor.of(parseString(FieldKey.EYE_COLOR) ?: return null)
        }

    protected open val parsedHairColor: HairColor?
        get() {
            return HairColor.of(parseString(FieldKey.HAIR_COLOR) ?: return null)
        }

    /**
     * Returns the height in inches.
     */
    protected open val parsedHeight: Double?
        get() {
            val heightString = parseString(FieldKey.HEIGHT_INCHES) ?: return null
            val height = heightString.split(" ")
                    .firstOrNull()?.toDoubleOrNull() ?: return null
            return if (heightString.contains("cm"))
                Utils.inchesFromCentimeters(height) else height
        }

    /**
     * Returns the weight in pounds.
     */
    protected open val parsedWeight: Weight?
        get() {
            parseString(FieldKey.WEIGHT_POUNDS)?.toDoubleOrNull()?.let {
                return Weight(pounds = it)
            }
            parseString(FieldKey.WEIGHT_KILOGRAMS)?.toDoubleOrNull()?.let {
                return Weight(pounds = Utils.poundsFromKilograms(it))
            }
            parseString(FieldKey.WEIGHT_RANGE)?.toIntOrNull()?.let {
                return Weight(WeightRange(it))
            }
            return null
        }

    /**
     * Returns the postal code in 12345-6789 format or 12345 if the last 4 digits are 0.
     */
    protected open val parsedPostalCode: String?
        get() {
            val rawCode = parseString(FieldKey.POSTAL_CODE)
            val firstPart: String? = rawCode?.substring(0, 5) ?: return null
            val secondPart: String? = rawCode.substring(5)

            secondPart?.takeIf { it != "0000" }?.let { return firstPart.plus("-").plus(it) }
                    ?: return firstPart
        }
}