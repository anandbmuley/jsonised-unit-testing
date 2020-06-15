package abm.jsonizedut;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static abm.jsonizedut.FileType.*;

public class TestDataLoader {

    private ObjectMapper objectMapper;
    private final FileLoader fileLoader;
    private static TestDataLoader INSTANCE;

    private TestDataLoader() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        fileLoader = new FileLoader();
    }

    public static TestDataLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestDataLoader();
        }
        return INSTANCE;
    }

    private <T> T readContent(String jsonContent, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonContent, clazz);
    }

    public <T> T readResponse(String filename, Class<T> clazz) throws JsonProcessingException {
        return readContent(fileLoader.load(filename, RESPONSE), clazz);
    }

    public <T> T readRequest(String filename, Class<T> clazz) throws JsonProcessingException {
        return readContent(fileLoader.load(filename, REQUEST), clazz);
    }

    public <T> T readErrorResponse(String filename, Class<T> clazz) throws JsonProcessingException {
        return readContent(fileLoader.load(filename, ERROR_RESPONSE), clazz);
    }

    public String readContent(String filename, FileType fileType) {
        return fileLoader.load(filename, fileType);
    }


}
