package Utilities;


import API.Endpoints.Test;
import Exceptions.ApiRequestException;
import Models.TestModel;
import aquality.selenium.core.logging.Logger;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;

import java.util.List;

import static API.ApiUtil.ResponseUtil.jsonValidator;
import static API.Endpoints.Test.*;
import static API.Endpoints.Token.getToken;
import static API.Endpoints.Token.tokenGenerator;
import static Utilities.RandomUtil.generateAlphaNumericString;
import static aquality.selenium.browser.AqualityServices.getBrowser;

public class WebApiUtil {

    private static final Logger logger = Logger.getInstance();

    public static String getTokenUsingApi(String variantNumber){
        Response response = tokenGenerator(variantNumber);
        String token = getToken(response);
        logger.info(String.format("Generated token is: %s", token));
        return token;
    }

    public static void authorizeWebsite(String authUrl, String username, String password){
        logger.info(String.format("Authorize %s with username: %s and password: %s", authUrl, username, password));
        getBrowser().goTo(String.format(authUrl, username, password));
    }

    public static void addCookie(String name, String value){
        logger.info(String.format("Add a new Cookie name: %s and value: %s", name, value));
        Cookie authCookie = new Cookie(name, value);
        getBrowser().getDriver().manage().addCookie(authCookie);
    }

    public static void refreshPage(){
        getBrowser().refresh();
    }

    public static Response getTestListInJsonUsingApi(String projectId){
        return getTestListInJson(projectId);
    }

    public static boolean isTestListResponseInJsonUsingApi(Response response){
        if(jsonValidator(response))
            return true;
        else throw new ApiRequestException(String.format(
                "The response is not in Json Format. Response format is: %s", response.getContentType())
        );
    }

    public static List<TestModel> getTestListAsTestModel(Response response){
        List<TestModel> tests = Test.getTestsAsTestModel(response);
        logger.info(String.format("Api request response of getting Test List as Test Model: %s", tests));
        return tests;
    }

    public static boolean hasCorrespondingElement(List<TestModel> nexageFirstPageTests,
                                                  List<TestModel> apiResponseTests) {
        return nexageFirstPageTests.stream()
                .anyMatch(testModel -> apiResponseTests.stream().anyMatch(testModel::equals));
    }

    public static void switchToNewTab(){
        getBrowser().tabs().switchToLastTab();
    }

    public static void waitForPageToLoad() {
        getBrowser().waitForPageToLoad();
    }

    public static void closeAddProjectForm(){
        String jsCode = "function closePopUp() { window.close(); } closePopUp();";
        getBrowser().getDriver().executeAsyncScript(jsCode,"");
    }

    public static void goBackToPreviousPage(){
        getBrowser().goBack();
        switchToMainPage();
    }
    public static void switchToMainPage(){
        getBrowser().tabs().switchToTab(0);
    }

    public static String getSessionIdForTest(String length){
        return generateAlphaNumericString(Integer.parseInt(length));
    }

    public static String createTestRecordAndGetTestIdUsingApi(String sessionId,
                                                      String projectName,
                                                      String testName,
                                                      String methodName,
                                                      String env){
        Response response = Test.createTestRecord(sessionId, projectName, testName, methodName, env);
        String testId = getTestIdUsingApi(response);
        logger.info(String.format(
                "Retrieved Test Id is %s after creating a new test record where sessionId = %s, projectName = %s, TestName = %s, MethodName = %s, env= %s",
                testId, sessionId, projectName, testName, methodName, env));
        return testId;
    }

    public static void sendLogFilesUsingApi(String testId, String content){
        sendLogFiles(testId, content);
        logger.info(String.format("Log file of test id: %s is sent successfully", testId));
    }

    public static String getLogContent(){
        return RandomUtil.generateSentence();
    }

    public static String takeScreenshotAsByteArray(){
        return getBrowser().getDriver().getScreenshotAs(OutputType.BASE64);
    }

    public static void sendScreenShotOfCurrentPageUsingApi(String testId,
                                                           String content,
                                                           String contentType){
        sendTestAttachment(testId, content, contentType);
        logger.info(String.format("A screenshot of the current page is sent to test id: %s", testId));
    }
}
