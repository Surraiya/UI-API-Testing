package Utilities.FileReader.FileKeyName;

public enum EndpointsKey {
    GET_TOKEN("/get_token"),
    GET_TEST_LIST("/get_tests_list"),
    PUT_TEST("/put_test"),
    PUT_TEST_LOG("/put_test_log"),
    PUT_TEST_ATTACHMENT("/put_test_attachment");

    public final String key;

    EndpointsKey(String key){
        this.key = key;
    }
}
