package com.ajohnson.dlparserkotlin.parsers

import com.ajohnson.dlparserkotlin.Utils
import com.ajohnson.dlparserkotlin.categories.*
import com.ajohnson.dlparserkotlin.models.FieldKey
import com.ajohnson.dlparserkotlin.models.License
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ajohnson on 4/7/18.
 */
open class DLParser(val data: String) {

    /**
     * The base fields common to all or most version standards. This
     * field should be modified in subclasses for version-specific
     * field changes.
     * */
    val fields: MutableMap<FieldKey, String> = mutableMapOf(
        FieldKey.jVehicleClass to                "DCA",
        FieldKey.jRestrictionCode to             "DCB",
        FieldKey.jEndorsementCode to             "DCD",
        FieldKey.expirationDate to               "DBA",
        FieldKey.issueDate to                    "DBD",
        FieldKey.firstName to                    "DAC",
        FieldKey.middleName to                   "DAD",
        FieldKey.lastName to                     "DCS",
        FieldKey.birthDate to                    "DBB",
        FieldKey.gender to                       "DBC",
        FieldKey.eyeColor to                     "DAY",
        FieldKey.heightInches to                 "DAU",
        FieldKey.streetAddress to                "DAG",
        FieldKey.city to                         "DAI",
        FieldKey.state to                        "DAJ",
        FieldKey.postalCode to                   "DAK",
        FieldKey.driverLicenseNumber to          "DAQ",
        FieldKey.uniqueDocumentId to             "DCF",
        FieldKey.country to                      "DCG",
        FieldKey.lastNameTruncation to           "DDE",
        FieldKey.firstNameTruncation to          "DDF",
        FieldKey.middleNameTruncation to         "DDG",
        FieldKey.streetAddressTwo to             "DAH",
        FieldKey.hairColor to                    "DAZ",
        FieldKey.placeOfBirth to                 "DCI",
        FieldKey.auditInformation to             "DCJ",
        FieldKey.inventoryControlNumber to       "DCK",
        FieldKey.lastNameAlias to                "DBN",
        FieldKey.givenNameAlias to               "DBG",
        FieldKey.suffix to                       "DBS", //.name toDO toOr DCU
        FieldKey.weightRange to                  "DCE",
        FieldKey.race to                         "DCL",
        FieldKey.sVehicleCode to                 "DCM",
        FieldKey.sEndorsementCode to             "DCN",
        FieldKey.sRestrictionCode to             "DCO",
        FieldKey.jVehicleClassDescription to     "DCP",
        FieldKey.jEndorsementCodeDescription to  "DCQ",
        FieldKey.jRestrictionCodeDescription to  "DCR",
        FieldKey.complianceType to               "DDA",
        FieldKey.revisionDate to                 "DDB",
        FieldKey.hazmatExpirationDate to         "DDC",
        FieldKey.weightPounds to                 "DAW",
        FieldKey.weightKilograms to              "DAX",
        FieldKey.isTemporaryDocument to          "DDD",
        FieldKey.isOrganDonor to                 "DDK",
        FieldKey.isVeteran to                    "DDL",
        FieldKey.fVehicleCode to                 "DCH",
        FieldKey.driverLicenseName to            "DAA",
        FieldKey.givenName to                    "DCT")

    /**
     * The version number detected in the driver license data or nil
     * if the data is not AAMVA compliant.
     */
    val versionNumber get() = Utils.firstRegexMatch("\\d{6}(\\d{2})\\w+", data)?.toInt()

    /**
    The number of subfiles found in the driver license data.
     */
    val subfileCount get() = Utils.firstRegexMatch("\\d{8}(\\d{2})\\w+", data)?.toInt()

    open val unitedStatesDateFormat get() = "MMddyyyy"
    open val canadaDateFormat get() = "yyyyMMdd"

    val dateFormat get() = when (parsedCountry) {
        IssuingCountry.UNITED_STATES -> unitedStatesDateFormat
        IssuingCountry.CANADA -> canadaDateFormat
        else -> unitedStatesDateFormat
    }

    private val versionParser get() = when (versionNumber) {
        1 ->  VersionOneParser(data)
        2 ->  VersionTwoParser(data)
        3 ->  VersionThreeParser(data)
        4 ->  VersionFourParser(data)
        5 ->  VersionFiveParser(data)
        8 ->  VersionEightParser(data)
        9 ->  VersionNineParser(data)
        else -> DLParser(data)
    }

    fun parse(): License {
        val version = versionNumber
        val parser = versionParser
        return License(
                parser.parsedFirstName,
                parser.parsedMiddleNames,
                parser.parsedLastName,
                parser.parseString(FieldKey.firstNameAlias),
                parser.parseString(FieldKey.givenNameAlias),
                parser.parseString(FieldKey.lastNameAlias),
                parser.parseString(FieldKey.suffixAlias),
                parser.parsedNameSuffix,
                parser.parseTruncation(FieldKey.firstNameTruncation),
                parser.parseTruncation(FieldKey.middleNameTruncation),
                parser.parseTruncation(FieldKey.lastNameTruncation),
                parser.parseDate(FieldKey.expirationDate),
                parser.parseDate(FieldKey.issueDate),
                parser.parseDate(FieldKey.birthDate),
                parser.parseDate(FieldKey.hazmatExpirationDate),
                parser.parseDate(FieldKey.revisionDate),
                parser.parseString(FieldKey.race),
                parser.parsedGender,
                parser.parsedEyeColor,
                parser.parsedHeight,
                parser.parsedWeight,
                parser.parsedHairColor,
                parser.parseString(FieldKey.placeOfBirth),
                parser.parseString(FieldKey.streetAddress),
                parser.parseString(FieldKey.streetAddressTwo),
                parser.parseString(FieldKey.city),
                parser.parseString(FieldKey.state),
                parser.parseString(FieldKey.postalCode),
                parser.parsedCountry,
                parser.parseString(FieldKey.driverLicenseNumber),
                parser.parseString(FieldKey.uniqueDocumentId),
                parser.parseString(FieldKey.auditInformation),
                parser.parseString(FieldKey.inventoryControlNumber),
                parser.parseString(FieldKey.complianceType),
                parser.parseBoolean(FieldKey.isOrganDonor),
                parser.parseBoolean(FieldKey.isVeteran),
                parser.parseBoolean(FieldKey.isTemporaryDocument),
                parser.parseString(FieldKey.fVehicleCode),
                parser.parseString(FieldKey.sVehicleCode),
                parser.parseString(FieldKey.sRestrictionCode),
                parser.parseString(FieldKey.sEndorsementCode),
                parser.parseString(FieldKey.jVehicleClass),
                parser.parseString(FieldKey.jRestrictionCode),
                parser.parseString(FieldKey.jEndorsementCode),
                parser.parseString(FieldKey.jVehicleClassDescription),
                parser.parseString(FieldKey.jRestrictionCodeDescription),
                parser.parseString(FieldKey.jEndorsementCodeDescription),
                version,
                data
        )
    }


    open fun parseString(key: FieldKey): String? {
        fields[key]?.let {
            return Utils.firstRegexMatch("$it(.+)\\b", data)
        } ?: return null
    }

    open fun parseDouble(key: FieldKey): Double? {
        fields[key]?.let {
            return Utils.firstRegexMatch("$it(\\w+)\\b", data)?.toDoubleOrNull()
        } ?: return null
    }

    open fun parseDate(key: FieldKey): Date? {
        val dateString = parseString(key)
        if (dateString.isNullOrEmpty()) return null
        return SimpleDateFormat(dateFormat, Locale.US).parse(dateString)
    }

    open fun parseBoolean(key: FieldKey): Boolean? {
        val rawValue = parseString(key) ?: return null
        return rawValue == "1"
    }

    open val parsedFirstName get() =
        parseString(FieldKey.firstName)
        ?: parseString(FieldKey.givenName)?.split(",")?.lastOrNull()?.trim()
        ?: parseString(FieldKey.driverLicenseName)?.split(",")?.lastOrNull()?.trim()

    open val parsedMiddleNames: List<String> get() {
        parseString(FieldKey.middleName)?.let {
            return listOf(it)
        }

        parseString(FieldKey.givenName)?.let {
            val parts = it.split(",")
            return parts.drop(0).map { it.trim() }
        }

        parseString(FieldKey.driverLicenseName)?.let {
            val parts = it.split(",")
            return parts.drop(0).dropLast(0).map { it.trim() }
        }
        return listOf()
    }

    open val parsedLastName get() = parseString(FieldKey.lastName)
        ?: parseString(FieldKey.driverLicenseName)?.split(",")?.lastOrNull()?.trim()

    open val parsedNameSuffix: NameSuffix? get() {
        return NameSuffix.of(parseString(FieldKey.suffix) ?: return null)
    }

    open fun parseTruncation(key: FieldKey): Truncation? {
        parseString(key)?.let {
            return Truncation.of(it)
        } ?: return null
    }

    open val parsedCountry: IssuingCountry? get() {
        return IssuingCountry.of(parseString(FieldKey.country) ?: return null)
    }

    open val parsedGender: Gender? get() {
        return Gender.of(parseString(FieldKey.gender) ?: return null)
    }

    open val parsedEyeColor: EyeColor? get() {
        return EyeColor.of(parseString(FieldKey.eyeColor) ?: return null)
    }

    open val parsedHairColor: HairColor? get() {
        return HairColor.of(parseString(FieldKey.hairColor) ?: return null)
    }

    /**
     * Returns the height in inches.
     */
    open val parsedHeight: Double? get() {
        val heightString = parseString(FieldKey.heightInches) ?: return null
        val height = heightString.split(" ")
                .firstOrNull()?.toDoubleOrNull() ?: return null
        return if (heightString.contains("cm"))
            Utils.inchesFromCentimeters(height) else height
    }

    /**
     * Returns the weight in pounds.
     * */
    open val parsedWeight: Weight? get() {
        parseString(FieldKey.weightPounds)?.toDoubleOrNull()?.let {
            return Weight(pounds=it)
        }
        parseString(FieldKey.weightKilograms)?.toDoubleOrNull()?.let {
            return Weight(pounds=Utils.poundsFromKilograms(it))
        }
        parseString(FieldKey.weightRange)?.toIntOrNull()?.let {
            return Weight(WeightRange(it))
        }
        return null
    }
}