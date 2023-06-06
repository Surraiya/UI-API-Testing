package API.Endpoints;

import API.ApiUtil.RequestUtil;
import Exceptions.ApiRequestException;
import io.restassured.response.Response;

import static API.ApiUtil.ApiParamName.VARIANT;
import static API.ApiUtil.ResponseUtil.getResponseBody;
import static API.ApiUtil.ResponseUtil.isResponseSuccessful;
import static Utilities.FileReader.FileKeyName.EndpointsKey.GET_TOKEN;
import static Utilities.FileReader.FileName.ENDPOINTS;
import static Utilities.FileReader.JsonFileReader.getStringValue;

public class Token {

    public static Response tokenGenerator(String variantNumber) {
        Response response = RequestUtil.sendTokenGenerationRequest(getStringValue(ENDPOINTS.fileName, GET_TOKEN.key),
                VARIANT.parameter, variantNumber);
        if (isResponseSuccessful(response))
            return response;
        else
            throw new ApiRequestException("Token generation request failed. Status code: " + response.getStatusCode());
    }

    public static String getToken(Response response){
        return getResponseBody(response);
    }
}
