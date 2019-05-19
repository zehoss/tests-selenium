package pl.blackfernsoft.spyro.page;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class BasePageObject {

    protected WebDriver driver;

    protected BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public static void takeScreenshot(WebDriver driver) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshots/" + screenshot.getName()));
    }

    public void takeScreenshot() throws IOException {
        takeScreenshot(driver);
    }
}
