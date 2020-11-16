package abm.jsonizedut;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static abm.jsonizedut.FileType.*;

public final class TestDataLoader {

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

    private <T> T loadContent(String jsonContent, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonContent, clazz);
    }

    public <T> T loadResponse(String filename, Class<T> clazz) throws JsonProcessingException {
        return loadContent(fileLoader.load(filename, RESPONSE), clazz);
    }

    public <T> T loadRequest(String filename, Class<T> clazz) throws JsonProcessingException {
        return loadContent(fileLoader.load(filename, REQUEST), clazz);
    }

    public <T> T loadErrorResponse(String filename, Class<T> clazz) throws JsonProcessingException {
        return loadContent(fileLoader.load(filename, ERROR_RESPONSE), clazz);
    }

    public <T> String toJSON(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }

    public String loadContent(String filename, FileType fileType) {
        return fileLoader.load(filename, fileType);
    }


}
