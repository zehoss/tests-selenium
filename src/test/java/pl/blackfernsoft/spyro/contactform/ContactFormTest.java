package pl.blackfernsoft.spyro.contactform;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.blackfernsoft.spyro.config.Browser;
import pl.blackfernsoft.spyro.config.DriverFactory;
import pl.blackfernsoft.spyro.page.BasePageObject;
import pl.blackfernsoft.spyro.page.MainPage;

import java.io.IOException;

public class ContactFormTest {

    private final String contactName = "Krzysiek";
    private final String contactEmail = "kwezowski@spyro-soft.com";
    private final String contactPhoneNumber = "123123";
    private final String mainPageUrl = "http://spyro-soft.com";
    private final String contactMessage = "Some test message - sorry for that :)";
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = DriverFactory.forBrowser(Browser.CRHOME);
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Closer browser");
        driver.close();
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("take screenshot");
            BasePageObject.takeScreenshot(driver);
        }
    }

    @Test(groups = "contactForm")
    public void pageHasCorrectTitle() throws InterruptedException {
        MainPage mainPage = new MainPage(driver, mainPageUrl);

        mainPage.openPage()
                .assertTitleEquals("SpyroSoft");
    }

    @Test(groups = "contactForm")
    public void whenNoAllAcceptance_validationFails() {
        MainPage mainPage = new MainPage(driver, mainPageUrl);

        mainPage.openPage()
                .clickContactUsMenu()
                .fillFormMandatoryFields(contactName, contactEmail, contactPhoneNumber, true, false, false)
                .fillMessage(contactMessage)
                .submitForm()
                .assertMainValidationMessage();
    }

    @Test(groups = "contactForm")
    public void whenNoName_validationFails() {
        MainPage mainPage = new MainPage(driver, mainPageUrl);

        mainPage.openPage()
                .clickContactUsMenu()
                .fillFormMandatoryFields("", contactEmail, contactPhoneNumber, true, false, false)
                .fillMessage(contactMessage)
                .submitForm()
                .assertNameValidationMessage();
    }

    @Test(groups = "contactForm")
    public void whenNoPhoneNumber_validationFails() {
        MainPage mainPage = new MainPage(driver, mainPageUrl);

        mainPage.openPage()
                .clickContactUsMenu()
                .fillFormMandatoryFields(contactName, contactEmail, "", true, false, false)
                .fillMessage(contactMessage)
                .submitForm()
                .assertPhoneNumberValidationMessage();
    }

    @Test(groups = "contactForm")
    public void whenNoEmail_validationFails() {
        MainPage mainPage = new MainPage(driver, mainPageUrl);

        mainPage.openPage()
                .clickContactUsMenu()
                .fillFormMandatoryFields(contactName, "", contactPhoneNumber, true, false, false)
                .fillMessage(contactMessage)
                .submitForm()
                .assertEmailValidationMessage();
    }

}
