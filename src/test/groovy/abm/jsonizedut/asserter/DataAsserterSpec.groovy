package abm.jsonizedut.asserter

import abm.jsonizedut.FileType
import abm.jsonizedut.TestDataLoader
import abm.jsonizedut.exceptions.DataAssertionFailed
import spock.lang.Specification

class DataAsserterSpec extends Specification {

    DataAsserter dataAsserter

    TestDataLoader testDataLoader

    void setup() {
        dataAsserter = new DataAsserter()
        testDataLoader = TestDataLoader.getInstance()
    }

    def "assertData - should pass successfully"() {
        given:
        String expected = testDataLoader.loadContent("customer.json", FileType.REQUEST)
        String actual = expected

        when:
        dataAsserter.assertData(expected, actual)

        then:
        noExceptionThrown()
    }

    def "assertData - should fail if mismatched response"() {
        given:
        String expected = testDataLoader.loadContent("customer-expected.json", FileType.REQUEST)
        String actual = testDataLoader.loadContent("customer.json", FileType.REQUEST)

        when:
        dataAsserter.assertData(expected, actual)

        then:
        def exceptionThrown = thrown(DataAssertionFailed)
        exceptionThrown.message == "Data Mismatched !\nUnExpected Fields\n\t\tField Path : \n\t\t[ Expected - null, Actual - contact]"

    }

}
