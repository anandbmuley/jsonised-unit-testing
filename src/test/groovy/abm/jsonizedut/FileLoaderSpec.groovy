package abm.jsonizedut

import spock.lang.Specification

import static abm.jsonizedut.FileType.*

class FileLoaderSpec extends Specification {

    FileLoader fileLoader

    void setup() {
        fileLoader = new FileLoader()
    }

    def "load - should load a file successfully"() {
        given:
        def filename = "customer.json"

        when:
        String fileContent = fileLoader.load(filename, FILE_TYPE)

        then:
        fileContent != null

        where:
        FILE_TYPE      | _
        REQUEST        | _
        RESPONSE       | _
        ERROR_RESPONSE | _
    }

    def "load - should log an error"() {
        given:
        def filename = "sample-wrong.json"

        when:
        String fileContent = fileLoader.load(filename, FILE_TYPE)

        then:
        fileContent == null

        where:
        FILE_TYPE      | _
        REQUEST        | _
        RESPONSE       | _
        ERROR_RESPONSE | _
    }
}
