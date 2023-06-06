package API.ApiUtil;

public enum ApiParamName {
    VARIANT("variant"),
    PROJECT_ID("projectId"),
    SID("SID"),
    PROJECT_NAME("projectName"),
    TEST_NAME("testName"),
    METHOD_NAME("methodName"),
    ENV("env"),
    TEST_ID("testId"),
    CONTENT("content"),
    CONTENT_TYPE("contentType");

    public final String parameter;

    ApiParamName(String parameter){
        this.parameter = parameter;
    }
}
