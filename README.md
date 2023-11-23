# Automated Testing with RestAssured and Selenium

## Overview 
This project demonstrates an automated testing framework for a web application using RestAssured for API testing and Selenium for UI testing. The tests are written in Java and use TestNG as the testing framework. The main test scenario, found in the `webApiTest` class, focuses on authorizing a project, creating tests via API, and interacting with the web application.

Main aim of the project was to create set of auto tests for custom Web Api. It focuses mostly on proper usage of web elements, creation of rest api requests and maintaining correct project structure. Task require setting & running app in Docker. 

Project was part of A1QA automation testing internship and some of the resources were shared by the company (Dockerfile).

## Project Structure

- **testScenario Package:** Contains the main test scenarios for the project.
  - **BaseTest:** Base test class with common setup and teardown methods.
  - **webApiTest:** Test class containing the main test scenario for authorizing a project and creating tests via API.

- **Models Package:** Contains data models used in the project.
  - **TestModel:** Represents a test model for storing test-related information.

- **PageObjects Package:** Contains page object classes representing different pages in the web application.
  - **AddProjectPage:** Page object for the "Add Project" form.
  - **CreatedProjectPage:** Page object for the "Created Project" page.
  - **MainPage:** Page object for the main page of the web application.
  - **NexageProjectPage:** Page object for the Nexage project page.

- **Utilities Package:** Contains utility classes for various purposes.
  - **FileReader:** Utility class for reading configuration files.
  - **WebApiUtil:** Utility class for making web API requests.
  - **WebDriverUtil:** Utility class for interacting with the Selenium WebDriver.

## Dependencies

- **RestAssured:** Used for making API requests and validating API responses.
- **Selenium WebDriver:** Used for interacting with the web application.
- **TestNG:** Testing framework for running tests and managing test suites.

## Setup Instructions

1. Clone the repository: `git clone https://github.com/yourusername/your-project.git`
2. Open the project in your preferred Java IDE.
3. Resolve dependencies using a build tool like Maven or Gradle.
4. Update configuration files in the `resources` folder with appropriate values.
5. Run the tests using TestNG or your IDE's test runner.

## Test Execution

Execute the `webApiTest` class to run the main test scenario. Test results and logs will be generated during the test execution.

## Test Steps (webApiTest)
The required autotests are given:
[RestAPI_AutoTests_TestCase.pdf](RestAPI_AutoTests_TestCase.pdf)

1. **Get Token:**
   - Retrieve a token according to the variant number using an API request.

2. **Authorize Website:**
   - Navigate to the website.
   - Perform authorization using credentials.
   - Pass the generated token using a cookie parameter.
   - Refresh the page.

3. **Verify Main Page:**
   - Ensure the main page is open.
   - Check that the correct variant number is displayed in the footer.

4. **Nexage Project Page:**
   - Go to the Nexage project page.
   - Make an API request to get a list of tests in JSON format.
   - Verify that the list of tests is in JSON format.
   - Compare the API response with the tests on the Nexage project page.

5. **Add Project:**
   - Return to the main page (project page).
   - Click on the '+Add' button.
   - Enter a project name and save.
   - Close the project adding window.
   - Refresh the page and verify that the project is saved successfully.

6. **Add Test via API:**
   - Go to the page of the created project.
   - Add a test via API along with a log and a screenshot of the current page.
   - Verify that the test is listed on the Created Project Page without refreshing.

## API
The API specification is given:
[Reporting-Portal-Api-Spec.pdf](https://github.com/Surraiya/UI-API-Testing/blob/Variant2(Web%2BApi)/Reporting-Portal-Api-Spec.pdf)

## Technologies
Project is created with:
-	Docker
-	Java 17
-	Selenium  / Aquality Selenium 
-	Rest Assured 
-	TestNG 
