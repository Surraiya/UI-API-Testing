package testScenario;


import aquality.selenium.core.logging.Logger;
import org.testng.annotations.AfterMethod;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BaseTest {

    protected static final Logger logger = Logger.getInstance();
//
//    @BeforeMethod
//    public void initializeBrowser(){
//        getBrowser().waitForPageToLoad();
//        getBrowser().maximize();
//    }

    @AfterMethod
    public void closeBrowser(){
        getBrowser().quit();
    }
}
