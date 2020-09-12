package abm.jsonizedut


import spock.lang.Specification

import static abm.jsonizedut.shared.AssertionsCatalogue.assertData

class TestDataLoaderSpec extends Specification {

    TestDataLoader testDataLoader

    void setup() {
        testDataLoader = TestDataLoader.getInstance()
    }

    def "should create single instance"() {
        given:
        def previousHashcode = TestDataLoader.getInstance().hashCode()

        when:
        def newHashCode = TestDataLoader.getInstance().hashCode()

        then:
        previousHashcode == newHashCode
    }

    def "readRequest - should read request data"() {
        when:
        Customer actual = testDataLoader.loadRequest("customer.json", Customer)

        then:
        assertData actual
    }


    def "readResponse - should read response data"() {
        when:
        Customer actual = testDataLoader.loadResponse("customer.json", Customer)

        then:
        assertData actual
    }

    def "readErrorResponse - should read error response data"() {
        when:
        Customer actual = testDataLoader.loadErrorResponse("customer.json", Customer)

        then:
        assertData actual
    }

    def "readContent - should read the content of the file"() {
        expect:
        testDataLoader.loadContent("customer.json", FileType.REQUEST) != null
    }

}
