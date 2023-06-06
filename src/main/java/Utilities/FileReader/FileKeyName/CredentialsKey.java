package Utilities.FileReader.FileKeyName;

public enum CredentialsKey {

    API_URL("/apiUrl"),
    USERNAME("/username"),
    PASSWORD("/password"),
    COOKIE_NAME("/cookieName");

    public final String key;

    CredentialsKey(String key){
        this.key = key;
    }
}
