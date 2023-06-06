package Utilities.FileReader;

public enum FileName {
    CONFIG_DATA("configData"),
    CREDENTIALS("credentials"),
    ENDPOINTS("endPoints"),
    PROJECT_DATA("projectData");

    public final String fileName;

    FileName(String fileName){
        this.fileName = fileName;
    }
}
