package com.ajohnson.dlparserkotlin.models

/**
 * Keys used to map license data to the license model.
 */
internal enum class FieldKey {
    firstName,
    lastName,
    middleName,

    driverLicenseName,
    givenName,

    lastNameAlias,
    firstNameAlias,
    givenNameAlias,
    suffixAlias,
    suffix,

    middleNameTruncation,
    firstNameTruncation,
    lastNameTruncation,

    expirationDate,
    issueDate,
    birthDate,
    hazmatExpirationDate,
    revisionDate,

    race,
    gender,
    eyeColor,
    heightInches,
    heightCentimeters,
    hairColor,
    weightRange,
    weightPounds,
    weightKilograms,

    placeOfBirth,
    streetAddress,
    streetAddressTwo,
    city,
    state,
    postalCode,
    country,

    driverLicenseNumber,
    uniqueDocumentId,
    auditInformation,
    inventoryControlNumber,
    complianceType,

    isOrganDonor,
    isVeteran,
    isTemporaryDocument,

    fVehicleCode,  // f ==> Federal

    sVehicleCode,  // s ==> Standard
    sRestrictionCode,
    sEndorsementCode,

    jVehicleClass,  // j ==> Jurisdiction
    jRestrictionCode,
    jEndorsementCode,

    jVehicleClassDescription,
    jRestrictionCodeDescription,
    jEndorsementCodeDescription,

    federalCommercialVehicleCode;
}