package Utilities.FileReader.FileKeyName;

public enum CredentialsKey {

    USERNAME("/username"),
    PASSWORD("/password"),
    COOKIE_NAME("/cookieName");

    public final String key;

    CredentialsKey(String key){
        this.key = key;
    }
}
