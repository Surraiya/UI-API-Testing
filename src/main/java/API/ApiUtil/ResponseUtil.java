package API.ApiUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class ResponseUtil {

    public static boolean isResponseSuccessful(Response response){
        return response.getStatusCode() >= HttpStatus.SC_OK;
    }

    public static String getResponseBody(Response response){
        return response.getBody().asString();
    }

    public static boolean jsonValidator(Response response) {
        return response.jsonPath().toString() != null;
    }

    public static <T> T readJsonResponse(Response response, TypeReference<T> typeReference) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = getResponseBody(response);
            return objectMapper.readValue(responseBody, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error in deserializing JSON response", e);
        }
    }
}
