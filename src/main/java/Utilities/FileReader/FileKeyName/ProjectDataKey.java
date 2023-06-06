package Utilities.FileReader.FileKeyName;

public enum ProjectDataKey {
    PROJECT_NAME("/projectName"),
    SUCCESS_MESSAGE("/successMessage"),
    ENV("/env"),
    SESSION_ID_LENGTH("/sessionIdLength"),
    CONTENT_TYPE("/contentType");

    public final String key;

    ProjectDataKey(String key){
        this.key = key;
    }
}
