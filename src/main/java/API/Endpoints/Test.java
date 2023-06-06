package API.Endpoints;

import Exceptions.ApiRequestException;
import Models.TestModel;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;

import java.util.List;

import static API.ApiUtil.ApiParamName.PROJECT_ID;
import static API.ApiUtil.QueryParamsMapUtil.*;
import static API.ApiUtil.RequestUtil.*;
import static API.ApiUtil.ResponseUtil.*;
import static Utilities.FileReader.FileKeyName.EndpointsKey.*;
import static Utilities.FileReader.FileName.ENDPOINTS;
import static Utilities.FileReader.JsonFileReader.getStringValue;

public class Test {

    public static Response getTestListInJson(String projectId){
        Response response = sendGetTestListInJsonRequest(
                getStringValue(ENDPOINTS.fileName, GET_TEST_LIST.key),
                PROJECT_ID.parameter,
                projectId
        );
        if(isResponseSuccessful(response))
            return response;
        else throw new ApiRequestException(String.format(
                "Failed to get Test List in json. Status code: %s", response.getStatusCode())
        );
    }

    public static List<TestModel> getTestsAsTestModel(Response response){
        return readJsonResponse(response, new TypeReference<>() {});
    }

    public static Response createTestRecord(String sessionId, String projectName, String testName, String methodName, String env){
        Response response = createTestRecordRequest(
                getStringValue(ENDPOINTS.fileName, PUT_TEST.key),
                createTestParams(sessionId, projectName, testName, methodName, env)
        );
        if(isResponseSuccessful(response))
            return response;
        else throw new ApiRequestException(String.format(
                "Failed to create a Test record. Status code: %s", response.getStatusCode())
        );
    }

    public static String getTestIdUsingApi(Response response){
        return getResponseBody(response);
    }

    public static void sendLogFiles(String testId, String content){
        Response response = sendLogFileRequest(
                getStringValue(ENDPOINTS.fileName, PUT_TEST_LOG.key),
                sendTestLogParams(testId, content)
        );
        if(!isResponseSuccessful(response))
            throw new ApiRequestException(String.format(
                "Failed to send log Files. Status code: %s", response.getStatusCode())
        );
    }

    public static void sendTestAttachment(String testId, String content, String contentType){
        Response response = sendTestAttachmentRequest(
                getStringValue(ENDPOINTS.fileName, PUT_TEST_ATTACHMENT.key),
                sendTestAttachmentParams(testId, content, contentType)
        );
        if(!isResponseSuccessful(response))
            throw new ApiRequestException(String.format(
                    "Failed to send test Attachment. Status code: %s", response.getStatusCode())
            );
    }
}
