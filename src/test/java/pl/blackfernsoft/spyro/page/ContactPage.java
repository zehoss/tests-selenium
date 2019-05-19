package pl.blackfernsoft.spyro.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;

public class ContactPage extends BasePageObject {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage fillFormMandatoryFields(String name, String email, String phoneNumber,
                                               boolean acceptanceField1, boolean acceptanceField2, boolean acceptanceField3) {
        WebElement contactFormElement = getContactForm();
        contactFormElement.findElement(By.cssSelector("input[name='your-name']")).sendKeys(name);
        contactFormElement.findElement(By.cssSelector("input[name='your-email']")).sendKeys(email);
        contactFormElement.findElement(By.cssSelector("input[name='tel']")).sendKeys(phoneNumber);

        if (acceptanceField1) {
            contactFormElement.findElement(By.cssSelector("input[name='acceptance-field']")).click();
        }

        if (acceptanceField2) {
            contactFormElement.findElement(By.cssSelector("input[name='acceptance-field-2']")).click();
        }

        if (acceptanceField3) {
            contactFormElement.findElement(By.cssSelector("input[name='acceptance-field-3']")).click();
        }

        return this;
    }

    public ContactPage fillMessage(String message) {
        WebElement contactFormElement = getContactForm();
        contactFormElement.findElement(By.cssSelector("textarea[name='your-message']")).sendKeys(message);
        return this;
    }

    public ContactPage submitForm() {
        WebElement contactFormElement = getContactForm();
        contactFormElement.findElement(By.cssSelector("input[type='submit']")).submit();
        return this;
    }

    public ContactPage assertMainValidationMessage() {
        WebElement contactFormElement = getContactForm();
        assertTrue(contactFormElement.findElement(By.cssSelector("div.wpcf7-validation-errors")).isDisplayed());
        return this;
    }

    public ContactPage assertNameValidationMessage() {
        assertTrue(getContactForm().findElement(By.cssSelector("input[name='your-name']+span[role='alert']")).isDisplayed());
        return this;
    }

    public ContactPage assertEmailValidationMessage() {
        assertTrue(getContactForm().findElement(By.cssSelector("input[name='your-email']+span[role='alert']")).isDisplayed());
        return this;
    }

    public ContactPage assertPhoneNumberValidationMessage() {
        assertTrue(getContactForm().findElement(By.cssSelector("input[name='tel']+span[role='alert']")).isDisplayed());
        return this;
    }

    private WebElement getContactForm() {
        return driver.findElement(By.cssSelector(".contact-form-wrapper form"));
    }

}
