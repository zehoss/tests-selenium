package pl.blackfernsoft.spyro.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver forBrowser(Browser browser) {
        ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        switch (browser) {
            case CRHOME:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                driver.set(new ChromeDriver());
                break;

            case FIREFOX:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/firefox");
                driver.set(new FirefoxDriver());
                break;

            case IE:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/ie");
                driver.set(new InternetExplorerDriver());
                break;

            default:
                throw new UnsupportedOperationException("Unsupported browser");
        }

        return driver.get();
    }
}
