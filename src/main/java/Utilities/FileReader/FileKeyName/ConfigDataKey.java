package Utilities.FileReader.FileKeyName;

public enum ConfigDataKey {

    AUTH_URL("/authUrl"),
    VARIANT_NUMBER("/variantNumber");

    public final String key;

    ConfigDataKey(String key){
        this.key = key;
    }
}
