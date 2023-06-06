package testScenario;

import Models.TestModel;
import PageObjects.Pages.AddProjectPage;
import PageObjects.Pages.CreatedProjectPage;
import PageObjects.Pages.MainPage;
import PageObjects.Pages.NexageProjectPage;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.List;

import static Utilities.FileReader.FileKeyName.ConfigDataKey.AUTH_URL;
import static Utilities.FileReader.FileKeyName.ConfigDataKey.VARIANT_NUMBER;
import static Utilities.FileReader.FileKeyName.CredentialsKey.*;
import static Utilities.FileReader.FileKeyName.ProjectDataKey.*;
import static Utilities.FileReader.FileName.*;
import static Utilities.FileReader.JsonFileReader.getStringValue;
import static Utilities.WebApiUtil.*;
import static org.testng.Assert.*;

public class webApiTest extends BaseTest{

    @Test()
    public void testAuthorizeProjectAndCreateTestViaApi(ITestContext context){

        logger.info("1. Get a token according to the variant number using API request");
        String variantNumber = getStringValue(CONFIG_DATA.fileName, VARIANT_NUMBER.key);
        String token = getTokenUsingApi(variantNumber);
        assertNotEquals(token, null, "Token should be generated");


        logger.info("2. Go to the website. Pass the authorization. Pass the token generated in step 1 using cookie (token parameter). Refresh the page.");
        authorizeWebsite(getStringValue(CONFIG_DATA.fileName, AUTH_URL.key),
                getStringValue(CREDENTIALS.fileName, USERNAME.key),
                getStringValue(CREDENTIALS.fileName, PASSWORD.key));
        addCookie(getStringValue(CREDENTIALS.fileName, COOKIE_NAME.key), token);
        refreshPage();

        MainPage mainPage = new MainPage();
        assertTrue(mainPage.state().isDisplayed(), "MainPage should be open");
        assertTrue(mainPage.getVersionOptionNumber().contains(String.valueOf(variantNumber)), "The correct variant number should be in the footer");


        logger.info("3. Go to the Nexage project page. API request to get a list of tests in JSON format.");
        String projectId = mainPage.getNexageProjectId();
        Response response = getTestListInJsonUsingApi(projectId);
        assertTrue(isTestListResponseInJsonUsingApi(response), "List of tests should be in json format.");
        List<TestModel> apiResponsetests = getTestListAsTestModel(response);

        mainPage.clickNexageProject();
        NexageProjectPage nexageProjectPage = new NexageProjectPage();
        nexageProjectPage.state().waitForDisplayed();
        assertTrue(nexageProjectPage.state().isDisplayed(), "Nexage Page should be open");

        List<TestModel> nexageFirstPageTests = nexageProjectPage.getFirstPageTests();
        assertTrue(nexageProjectPage.isFirstPageTestsSortedByDate(nexageFirstPageTests), "The tests on the first page should be sorted by data in descending order");
        assertTrue(hasCorrespondingElement(nexageFirstPageTests, apiResponsetests), "Nexage First page tests should have at least one common element with the tests obtained by the API request");


        logger.info("4. Return to the previous page in the browser (project page). Click on +Add. Enter a project name and save. To close the window for adding a project, call the js-method closePopUp(). Refresh the page");
        goBackToPreviousPage();
        waitForPageToLoad();
        assertTrue(mainPage.state().isDisplayed(), "Previous page which is main page should be open");

        mainPage.clickAddProjectButton();
        AddProjectPage addProjectPage = new AddProjectPage();
        switchToNewTab();
        waitForPageToLoad();
        assertTrue(addProjectPage.state().isDisplayed(), "Add Project Form should be open");
        String projectName = getStringValue(PROJECT_DATA.fileName, PROJECT_NAME.key);
        addProjectPage.enterProjectName(projectName);
        addProjectPage.saveProjectName();
        waitForPageToLoad();

        String actualMessage = addProjectPage.getMessageAfterSavingProject();
        String expectedMessage = getStringValue(PROJECT_DATA.fileName, SUCCESS_MESSAGE.key);
        assertEquals(actualMessage, expectedMessage, String.format("Message should be %s, instead of %s", expectedMessage, actualMessage));
        closeAddProjectForm();
        assertFalse(addProjectPage.state().isDisplayed(), "Add Project Form should not be open");
        switchToMainPage();
        refreshPage();
        assertTrue(mainPage.isProjectSavedSuccessfully(projectName), String.format("Project Name: %s should appear in the List of projects",projectName));


        logger.info("5. Go to the page of the created project. Add a test via API (along with a log and a screenshot of the current page)");
        mainPage.goToCreatedProjectPage(projectName);
        CreatedProjectPage createdProjectPage = new CreatedProjectPage();
        waitForPageToLoad();

        String testName = context.getName();
        String testId = createTestRecordAndGetTestIdUsingApi(
                getSessionIdForTest(getStringValue(PROJECT_DATA.fileName, SESSION_ID_LENGTH.key)),
                projectName,
                testName,
                context.getAllTestMethods()[0].getMethodName(),
                getStringValue(PROJECT_DATA.fileName, ENV.key)
        );
        sendLogFilesUsingApi(testId, getLogContent());
        sendScreenShotOfCurrentPageUsingApi(
                testId,
                takeScreenshotAsByteArray(),
                getStringValue(PROJECT_DATA.fileName, CONTENT_TYPE.key)
        );

        assertTrue(createdProjectPage.isTestNameInTestListOfCreatedProject(testName), "Test is already in the Created Project Page without refreshing.");
    }
}
