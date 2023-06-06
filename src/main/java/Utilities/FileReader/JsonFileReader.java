package Utilities.FileReader;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JsonFileReader {
    private static final Map<String, ISettingsFile> fileMap = new HashMap<>();

    static {
        loadJsonFiles();
    }

    private static void loadJsonFiles() {
        try {
            Files.walk(Path.of(""))
                    .filter(Files::isRegularFile)
                    .filter(file -> file.getFileName().toString().endsWith(".json"))
                    .forEach(file -> {
                        String filename = file.getFileName().toString().replace(".json","");
                        ISettingsFile configFileReader = new JsonSettingsFile(file.getFileName().toString());
                        fileMap.put(filename, configFileReader);
                    });
        } catch (IOException e) {
            throw new RuntimeException("Failed to load all the json Files", e);
        }
    }

    public static String getStringValue(String filename, String key) {
        ISettingsFile fileReader = fileMap.get(filename);
        return (fileReader != null) ? fileReader.getValue(key).toString() : null;
    }
}
