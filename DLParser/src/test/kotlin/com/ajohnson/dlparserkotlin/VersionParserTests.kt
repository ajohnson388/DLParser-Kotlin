package com.ajohnson.dlparserkotlin

import com.ajohnson.dlparserkotlin.models.License
import com.ajohnson.dlparserkotlin.parsers.DLParser
import org.junit.Assert.assertEquals
import org.junit.Test
import tests.MockLicenseFactory

class VersionParserTests {

    private val mockFactory = MockLicenseFactory()

    private fun testVersionParser(mockLicense: License, licenseDataString: String) {
        val actualLicense = DLParser(licenseDataString).parse()
        actualLicense.pdf417Data = null  // Remove PDF417 data since we won't have it in the mock
        assertEquals(mockLicense.toString(), actualLicense.toString())
    }

    @Test
    fun testVersionOneParser() {
        testVersionParser(mockFactory.versionOneLicense, "@\n\u001E\rANSI 123456010201DL00000355DLPAA22\nPAE12\nPAF11\nDBH1\nDBJ12345678912345678912\nDBOlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBPgivenNameAlias\nDBRsuffixAlias\nDADmiddleName, middleName\nDAE5TH\nDAGstreetAddress\nDAAfirstName\nDABlastName\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\n\r")
    }

    @Test
    fun testVersionTwoParser() {
        testVersionParser(mockFactory.versionTwoLicense, "@\n\u001E\rANSI 123456020201DL00000356DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCH32\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCTfirstName\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDAGstreetAddress\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAQ12345678\nDAYBRO\nDAZBLK\n\r")
    }

    @Test
    fun testVersionThreeParser() {
        testVersionParser(mockFactory.versionThreeLicense, "@\n\u001E\rANSI 123456030201DL00000409DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCH32\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCTfirstName\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDAGstreetAddress\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAQ12345678\nDAYBRO\nDAZBLK\n\r")
    }

    @Test
    fun testVersionFourParser() {
        testVersionParser(mockFactory.versionFourLicense, "@\n\u001E\rANSI 123456040201DL00000499DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }

    @Test
    fun testVersionFiveParser() {
        testVersionParser(mockFactory.versionFiveLicense, "@\n\u001E\rANSI 123456050201DL00000499DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }

    @Test
    fun testVersionSixParser() {
        testVersionParser(mockFactory.versionSixLicense, "@\n\u001E\rANSI 123456060201DL00000505DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDK1\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }

    @Test
    fun testVersionSevenParser() {
        testVersionParser(mockFactory.versionSevenLicense, "@\n\u001E\rANSI 123456070201DL00000511DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDK1\nDDL0\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }

    @Test
    fun testVersionEightParser() {
        testVersionParser(mockFactory.versionEightLicense, "@\n\u001E\rANSI 123456080201DL00000511DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDK1\nDDL0\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }

    @Test
    fun testVersionNineParser() {
        testVersionParser(mockFactory.versionNineLicense, "@\n\u001E\rANSI 123456090201DL00000511DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDK1\nDDL0\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }

    @Test
    fun testVersionTenParser() {
        testVersionParser(mockFactory.versionTenLicense, "@\n\u001E\rANSI 123456100201DL00000511DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDK1\nDDL0\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }

    @Test
    fun testVersionElevenParser() {
        testVersionParser(mockFactory.versionElevenLicense, "@\n\u001E\rANSI 123456110201DL00000511DLDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDK1\nDDL0 \nDDM1\nDDN1\nDDO1\nDDP1\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }

    @Test
    fun testParserWithIdSubfile() {
        testVersionParser(mockFactory.versionTenLicense, "@\n\u001E\rANSI 123456100201ID00000511IDDCN11\nDCO12\nDCLW\nDCM22\nDCJauditInformation\nDCK123456\nDCIplaceOfBirth\nDCF12345678912345678912\nDCGUSA\nDCD8\nDCE2\nDCB9\nDCA10\nDCU5TH\nDCRJRD\nDCSlastName\nDCPJVD\nDCQJED\nDBNlastNameAlias\nDBA11111112\nDBC1\nDBB11111114\nDBD11111113\nDBGgivenNameAlias\nDBSsuffixAlias\nDADmiddleName, middleName\nDAGstreetAddress\nDACfirstName\nDAHstreetAddressTwo\nDAIcity\nDAJstate\nDAK12345\nDAU052\nDAW44\nDAQ12345678\nDAX44\nDAYBRO\nDAZBLK\nDDK1\nDDL0\nDDC11111115\nDDB11111116\nDDAM\nDDGN\nDDFT\nDDEN\nDDD0\nDDH11111117\nDDI11111118\nDDJ11111119\n\r")
    }
}