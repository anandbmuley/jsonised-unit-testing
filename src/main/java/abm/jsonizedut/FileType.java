package abm.jsonizedut;

public enum FileType {

    REQUEST("requests"),
    RESPONSE("responses"),
    ERROR_RESPONSE("error-responses");

    private final String folderName;

    FileType(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

}
