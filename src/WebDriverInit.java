import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import java.util.concurrent.TimeUnit;

public class WebDriverInit{
    public WebDriver webDriver;

    void init() {
        System.setProperty("webdriver.chrome.driver", "/home/developer/IdeaProjects/Automation/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("http://demo:demo@versionhistory.demo.zerp.info/login");

    }

    void initFirefox() {
        System.setProperty("webdriver.gecko.driver", "/home/developer/IdeaProjects/Automation/geckodriver");
        webDriver = new MarionetteDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("http://demo:demo@versionhistory.demo.zerp.info/login");
        webDriver.switchTo().alert().accept();
    }

    public WebDriver getWebDriver() {
        init();
        return webDriver;
    }

//    public WebDriver getWebDriver() {
//        for (int i = 0; i < 2; i++) {
//            webDriver = null;
//            if (i == 0) {
//                init();
//            } else if (i == 1) {
//                initFirefox();
//            }
//        }
//        return webDriver;
//    }

    public WebDriver getFFDriver() {
        initFirefox();
        return webDriver;
    }

    @AfterSuite(alwaysRun = true)
    public void setupAfterSuite() {
        webDriver.quit();
    }
}
