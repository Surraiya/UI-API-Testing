package API.ApiUtil;

import java.util.HashMap;
import java.util.Map;

import static API.ApiUtil.ApiParamName.*;

public class QueryParamsMapUtil {

    public static Map<String, Object> createTestParams(String sessionId, String projectName, String testName, String methodName, String env) {
        Map<String, Object> params = new HashMap<>();
        params.put(SID.parameter, sessionId);
        params.put(PROJECT_NAME.parameter, projectName);
        params.put(TEST_NAME.parameter, testName);
        params.put(METHOD_NAME.parameter, methodName);
        params.put(ENV.parameter, env);
        return params;
    }

    public static Map<String, Object> sendTestLogParams(String testId, String content){
        Map<String, Object> params = new HashMap<>();
        params.put(TEST_ID.parameter, testId);
        params.put(CONTENT.parameter, content);
        return params;
    }
    
    public static Map<String, Object> sendTestAttachmentParams(String testId, String content, String contentType){
        Map<String, Object> params = new HashMap<>();
        params.put(TEST_ID.parameter, testId);
        params.put(CONTENT.parameter, content);
        params.put(CONTENT_TYPE.parameter, contentType);
        return params;
    }
}
