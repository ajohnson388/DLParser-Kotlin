package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.utils.RawStringEnum
import com.ajohnson.dlparserkotlin.utils.Utils
import com.ajohnson.dlparserkotlin.categories.*
import com.ajohnson.dlparserkotlin.parsers.*
import com.ajohnson.dlparserkotlin.utils.from
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
        FieldKey.HEIGHT_CENTIMETERS to "DAV",
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
        FieldKey.SUFFIX_ALIAS to "DBS",
        FieldKey.SUFFIX to "DCU",
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
        FieldKey.GIVEN_NAME to "DCT",
    )

    /**
     * The version number detected in the driver license data or nil
     * if the data is not AAMVA compliant.
     */
//    val versionNumber get() = Utils.firstRegexMatch("\\d{6}(\\d{2})\\w+", data)?.toInt()
    val versionNumber: Int?
        get() {
            val matches = Regex("""\\d{6}(\\d{2})\\w+""").find(data)
            val value = matches?.value
            return value?.substring(7)?.toIntOrNull()
        }

    /**
    The number of subfiles found in the driver license data.
     */
    val subfileCount get() = Regex("""\\d{8}(\\d{2})\\w+""").find(data)?.value?.toInt()

    protected open val unitedStatesDateFormat get() = "MMddyyyy"
    protected open val canadaDateFormat get() = "yyyyMMdd"

    private val dateFormat
        get() = when (parseEnum<IssuingCountry>(FieldKey.COUNTRY)) {
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
            10 -> VersionTenParser(data)
            11 -> VersionElevenParser(data)
            else -> DLParser(data)
        }

    fun parse(): License {
        val version = versionNumber
        val parser = versionParser
        return License(
            firstName = parser.parsedFirstName,
            middleNames = parser.parsedMiddleNames,
            lastName = parser.parsedLastName,
            givenNameAlias = parser.parseString(FieldKey.GIVEN_NAME_ALIAS),
            lastNameAlias = parser.parseString(FieldKey.LAST_NAME_ALIAS),
            suffixAlias = parser.parseString(FieldKey.SUFFIX_ALIAS),
            suffix = parsedNameSuffix,
            firstNameTruncation = parseEnum(FieldKey.FIRST_NAME_TRUNCATION),
            middleNameTruncation = parseEnum(FieldKey.MIDDLE_NAME_TRUNCATION),
            lastNameTruncation = parseEnum(FieldKey.LAST_NAME_TRUNCATION),
            expirationDate = parser.parseDate(FieldKey.EXPIRATION_DATE),
            issueDate = parser.parseDate(FieldKey.ISSUE_DATE),
            birthdate = parser.parseDate(FieldKey.BIRTH_DATE),
            hazmatExpirationDate = parser.parseDate(FieldKey.HAZMAT_EXPIRATION_DATE),
            revisionDate = parser.parseDate(FieldKey.REVISION_DATE),
            underEighteenUntilDate = parser.parseDate(FieldKey.UNDER_EIGHTEEN_UNTIL_DATE),
            underNineteenUntilDate = parser.parseDate(FieldKey.UNDER_NINETEEN_UNTIL_DATE),
            underTwentyOneUntilDate = parser.parseDate(FieldKey.UNDER_TWENTY_ONE_UNTIL_DATE),
            race = parseEnum(FieldKey.RACE),
            gender = parseEnum(FieldKey.GENDER),
            eyeColor = parseEnum(FieldKey.EYE_COLOR),
            height = parser.parsedHeight,
            weight = parser.parsedWeight,
            hairColor = parseEnum(FieldKey.HAIR_COLOR),
            placeOfBirth = parser.parseString(FieldKey.PLACE_OF_BIRTH),
            streetAddress = parser.parseString(FieldKey.STREET_ADDRESS),
            streetAddressTwo = parser.parseString(FieldKey.STREET_ADDRESS_TWO),
            city = parser.parseString(FieldKey.CITY),
            state = parser.parseString(FieldKey.STATE),
            postalCode = parser.parsedPostalCode,
            country = parseEnum(FieldKey.COUNTRY),
            licenseNumber = parser.parseString(FieldKey.DRIVER_LICENSE_NUMBER),
            documentId = parser.parseString(FieldKey.UNIQUE_DOCUMENT_ID),
            auditInformation = parser.parseString(FieldKey.AUDIT_INFORMATION),
            inventoryControlNumber = parser.parseString(FieldKey.INVENTORY_CONTROL_NUMBER),
            complianceType = parseEnum(FieldKey.COMPLIANCE_TYPE),
            isOrganDonor = parser.parseBoolean(FieldKey.IS_ORGAN_DONOR),
            isVeteran = parser.parseBoolean(FieldKey.IS_VETERAN),
            isTemporaryDocument = parser.parseBoolean(FieldKey.IS_TEMPORARY_DOCUMENT),
            isCommercial = parser.parseBoolean(FieldKey.IS_COMMERCIAL),
            isNonDomiciled = parser.parseBoolean(FieldKey.IS_NON_DOMICILE),
            isEnhancedCredential = parser.parseBoolean(FieldKey.IS_ENHANCED_CREDENTIAL),
            isPermit = parser.parseBoolean(FieldKey.IS_PERMIT),
            federalVehicleCode = parser.parseString(FieldKey.FEDERAL_VEHICLE_CODE),
            standardVehicleCode = parser.parseString(FieldKey.STANDARD_VEHICLE_CODE),
            standardRestrictionCode = parser.parseString(FieldKey.STANDARD_RESTRICTION_CODE),
            standardEndorsementCode = parser.parseString(FieldKey.STANDARD_ENDORSEMENT_CODE),
            jurisdictionVehicleCode = parser.parseString(FieldKey.JURISDICTION_VEHICLE_CLASS),
            jurisdictionRestrictionCode = parser.parseString(FieldKey.JURISDICTION_RESTRICTION_CODE),
            jurisdictionEndorsementCode = parser.parseString(FieldKey.JURISDICTION_ENDORSEMENT_CODE),
            jurisdictionVehicleDescription = parser.parseString(FieldKey.JURISDICTION_VEHICLE_CLASS_DESCRIPTION),
            jurisdictionRestrictionDescription = parser.parseString(FieldKey.JURISDICTION_RESTRICTION_CODE_DESCRIPTION),
            jurisdictionEndorsementDescription = parser.parseString(FieldKey.JURISDICTION_ENDORSEMENT_CODE_DESCRIPTION),
            version = version,
            pdf417Data = data
        )
    }


    // Generic parsers

    private fun firstMatch(pattern: String, data: String): String? {
        val regex = Regex(pattern, setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
        val matchResult = regex.find(data) ?: return null
        val matchedGroup = matchResult.groups[1]?.value ?: return null
        val matchedString = matchedGroup.trim()
        return matchedString.ifEmpty { null }
    }

    internal open fun parseString(key: FieldKey): String? {
        val rawKey = fields[key] ?: return null
        return firstMatch("""\n$rawKey(.*?)\n""", data)
            ?: firstMatch("""DL$rawKey(.*?)\n""", data)
            ?: firstMatch("""ID$rawKey(.*?)\n""", data)
//        val regex = Regex("$rawKey(.+)\\b", RegexOption.IGNORE_CASE)
//        return regex.find(data)?.value?.removePrefix(rawKey)
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

    internal inline fun <reified T> parseEnum(key: FieldKey): T? where T : Enum<T>, T : RawStringEnum
            = from(parseString(key))


    // Case-specific parsers

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
        get () =NameSuffix.of(parseString(FieldKey.SUFFIX))

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
    protected open val parsedWeight: Weight
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
            return Weight()
        }

    /**
     * Returns the postal code in 12345-6789 format or 12345 if the last 4 digits are 0.
     */
    protected open val parsedPostalCode: String?
        get() {
            val rawCode = parseString(FieldKey.POSTAL_CODE)
            val firstPart: String? = rawCode?.substring(0, 5) ?: return null
            val secondPart: String? = rawCode.substring(5)
            if (secondPart == null || secondPart == "0000") return firstPart
            return firstPart.plus("-").plus(secondPart)
        }
}