package abm.jsonizedut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.logging.Level.FINER;

final class FileLoader {

    private static final Logger LOGGER = Logger.getLogger(FileLoader.class.getSimpleName());

    public synchronized String load(String filename, FileType fileType) {
        String fileContent = null;
        try {
            File schemaFile = Optional.ofNullable(getClass().getClassLoader()
                    .getResource(fileType.getFolderName() + File.separator + filename))
                    .map($ -> new File($.getFile()))
                    .orElseThrow(FileNotFoundException::new);
            fileContent = String.join("", Files.readAllLines(Paths.get(schemaFile.toURI())));
        } catch (IOException e) {
            LOGGER.log(FINER, "Unable to load file %s", filename);
        }
        return fileContent;
    }

}
