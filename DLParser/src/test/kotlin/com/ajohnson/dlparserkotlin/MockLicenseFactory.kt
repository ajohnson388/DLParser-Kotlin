package tests

import com.ajohnson.dlparserkotlin.categories.Compliance
import com.ajohnson.dlparserkotlin.categories.EyeColor
import com.ajohnson.dlparserkotlin.categories.Gender
import com.ajohnson.dlparserkotlin.categories.HairColor
import com.ajohnson.dlparserkotlin.categories.IssuingCountry
import com.ajohnson.dlparserkotlin.categories.NameSuffix
import com.ajohnson.dlparserkotlin.categories.Race
import com.ajohnson.dlparserkotlin.categories.Truncation
import com.ajohnson.dlparserkotlin.categories.Weight
import com.ajohnson.dlparserkotlin.categories.WeightRange
import com.ajohnson.dlparserkotlin.models.License
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MockLicenseFactory {

    private fun makeDate(dateString: String, format: String): Date {
        val formatter = SimpleDateFormat(format, Locale.US)
        return formatter.parse(dateString)!!
    }

    private fun makeBaseLicense(dateFormat: String = "MMddyyyy"): License {
        return License(
            firstName = "firstName",
            middleNames = listOf("middleName", "middleName"),
            lastName = "lastName",
            givenNameAlias = "givenNameAlias",
            lastNameAlias = "lastNameAlias",
            suffixAlias = "suffixAlias",
            suffix = NameSuffix.FIFTH,
            firstNameTruncation = Truncation.TRUNCATED,
            middleNameTruncation = Truncation.NONE,
            lastNameTruncation = Truncation.NONE,
            expirationDate = makeDate("11111112", dateFormat),
            issueDate = makeDate("11111113", dateFormat),
            birthdate = makeDate("11111114", dateFormat),
            hazmatExpirationDate = makeDate("11111115", dateFormat),
            revisionDate = makeDate("11111116", dateFormat),
            underEighteenUntilDate = makeDate("11111117", dateFormat),
            underNineteenUntilDate = makeDate("11111118", dateFormat),
            underTwentyOneUntilDate = makeDate("11111119", dateFormat),
            race = Race.WHITE,
            gender = Gender.MALE,
            eyeColor = EyeColor.BROWN,
            height = 52.0,
            weight = Weight(range = WeightRange(2), pounds = 44.0),
            hairColor = HairColor.BLACK,
            placeOfBirth = "placeOfBirth",
            streetAddress = "streetAddress",
            streetAddressTwo = "streetAddressTwo",
            city = "city",
            state = "state",
            postalCode = "12345",
            country = IssuingCountry.UNITED_STATES,
            licenseNumber = "12345678",
            documentId = "12345678912345678912",
            auditInformation = "auditInformation",
            inventoryControlNumber = "123456",
            complianceType = Compliance.MATERIALLY_COMPLIANT,
            isOrganDonor = true,
            isVeteran = false,
            isTemporaryDocument = false,
            isCommercial = null,
            isNonDomiciled = null,
            isEnhancedCredential = null,
            isPermit = null,
            federalVehicleCode = "32",
            standardVehicleCode = "22",
            standardRestrictionCode = "12",
            standardEndorsementCode = "11",
            jurisdictionVehicleCode = "10",
            jurisdictionRestrictionCode = "9",
            jurisdictionEndorsementCode = "8",
            jurisdictionVehicleDescription = "JVD",
            jurisdictionRestrictionDescription = "JRD",
            jurisdictionEndorsementDescription = "JED",
            version = null,
            pdf417Data = null
        )
    }

    val versionOneLicense: License
        get() {
            val license = makeBaseLicense("yyyyMMdd")
            license.version = 1
            license.federalVehicleCode = null
            license.jurisdictionVehicleCode = null
            license.jurisdictionRestrictionCode = null
            license.jurisdictionEndorsementCode = null
            license.country = null
            license.lastNameTruncation = null
            license.firstNameTruncation = null
            license.middleNameTruncation = null
            license.placeOfBirth = null
            license.auditInformation = null
            license.inventoryControlNumber = null
            license.weight?.range = null
            license.race = null
            license.jurisdictionVehicleDescription = null
            license.jurisdictionEndorsementDescription = null
            license.jurisdictionRestrictionDescription = null
            license.complianceType = null
            license.revisionDate = null
            license.hazmatExpirationDate = null
            license.isTemporaryDocument = null
            license.isVeteran = null
            license.underEighteenUntilDate = null
            license.underNineteenUntilDate = null
            license.underTwentyOneUntilDate = null
            return license
        }

    val versionTwoLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 2
            license.middleNames = listOf()
            license.lastNameTruncation = null
            license.firstNameTruncation = null
            license.middleNameTruncation = null
            license.lastNameAlias = null
            license.givenNameAlias = null
            license.suffixAlias = null
            license.complianceType = null
            license.revisionDate = null
            license.hazmatExpirationDate = null
            license.weight?.pounds = null
            license.isTemporaryDocument = null
            license.isOrganDonor = null
            license.isVeteran = null
            license.underEighteenUntilDate = null
            license.underNineteenUntilDate = null
            license.underTwentyOneUntilDate = null
            return license
        }

    val versionThreeLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 3
            license.middleNames = listOf()
            license.lastNameTruncation = null
            license.firstNameTruncation = null
            license.middleNameTruncation = null
            license.complianceType = null
            license.revisionDate = null
            license.hazmatExpirationDate = null
            license.weight?.pounds = null
            license.isTemporaryDocument = null
            license.isOrganDonor = null
            license.isVeteran = null
            license.underEighteenUntilDate = null
            license.underNineteenUntilDate = null
            license.underTwentyOneUntilDate = null
            return license
        }

    val versionFourLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 4
            license.isOrganDonor = null
            license.isVeteran = null
            license.federalVehicleCode = null
            license.underEighteenUntilDate = null
            license.underNineteenUntilDate = null
            license.underTwentyOneUntilDate = null
            return license
        }

    val versionFiveLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 5
            license.isOrganDonor = null
            license.isVeteran = null
            license.federalVehicleCode = null
            return license
        }

    val versionSixLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 6
            license.isVeteran = null
            license.federalVehicleCode = null
            return license
        }

    val versionSevenLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 7
            license.federalVehicleCode = null
            return license
        }

    val versionEightLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 8
            license.federalVehicleCode = null
            return license
        }

    val versionNineLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 9
            license.federalVehicleCode = null
            return license
        }

    val versionTenLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 10
            license.federalVehicleCode = null
            return license
        }

    val versionElevenLicense: License
        get() {
            val license = makeBaseLicense()
            license.version = 11
            license.federalVehicleCode = null
            license.givenNameAlias = null
            license.lastNameAlias = null
            license.suffixAlias = null
            license.hazmatExpirationDate = null
            license.race = null
            license.isCommercial = true
            license.isNonDomiciled = true
            license.isEnhancedCredential = true
            license.isPermit = true
            return license
        }
}