package API.ApiUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static Utilities.FileReader.FileKeyName.CredentialsKey.API_URL;
import static Utilities.FileReader.FileName.CREDENTIALS;
import static Utilities.FileReader.JsonFileReader.getStringValue;
import static io.restassured.RestAssured.given;

public class RequestUtil {

    private static final RequestSpecification requestSpecification = given()
            .baseUri(getStringValue(CREDENTIALS.fileName, API_URL.key));

    public static Response sendTokenGenerationRequest(String endpoint, String variantKey, String variant) {
        return requestSpecification
                .log().all()
                .param(variantKey, variant)
                .post(endpoint);
    }

    public static Response sendGetTestListInJsonRequest(String endpoint, String projectIdKey, String projectId){
        return requestSpecification
                .log().all()
                .accept(ContentType.JSON)
                .param(projectIdKey, projectId)
                .post(endpoint);
    }

    public static Response createTestRecordRequest(String endpoint, Map<String, Object> createTestParams){
        return requestSpecification
                .log().all()
                .params(createTestParams)
                .post(endpoint);
    }

    public static Response sendLogFileRequest(String endpoint, Map<String, Object> sendLogFileParams){
        return requestSpecification
                .log().all()
                .params(sendLogFileParams)
                .post(endpoint);
    }

    public static Response sendTestAttachmentRequest(String endpoint, Map<String, Object> sendAttachmentParams){
        return requestSpecification
                .log().all()
                .params(sendAttachmentParams)
                .post(endpoint);
    }
}
