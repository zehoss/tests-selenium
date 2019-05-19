package pl.blackfernsoft.spyro.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPage extends BasePageObject {

    private String pageUrl;

    public MainPage(WebDriver driver, String pageUrl) {
        super(driver);
        this.pageUrl = pageUrl;
    }


    public MainPage openPage() {
        driver.manage().window().maximize();
        driver.navigate().to(pageUrl);
        return this;
    }

    public MainPage assertTitleEquals(String title) {
        assertThat("SpyroSoft", equalTo(title));
        return this;
    }

    public ContactPage clickContactUsMenu() {
        driver.findElement(By.cssSelector("a[title='Contact us']")).click();
        return new ContactPage(driver);
    }
}
