import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.testng.annotations.AfterSuite;

public class WebDriverInit{
    public WebDriver webDriver;

    void init() {
        System.setProperty("webdriver.chrome.driver", "/home/developer/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://demo:demo@versionhistory.demo.zerp.info/login");
    }

    void initFirefox() {
        System.setProperty("webdriver.gecko.driver", "/home/developer/IdeaProjects/Automation/geckodriver");
        webDriver = new MarionetteDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://demo:demo@versionhistory.demo.zerp.info/login");
        webDriver.switchTo().alert().accept();
    }

    public WebDriver getWebDriver() {
        init();
        return webDriver;
    }

    public WebDriver getFFDriver() {
        initFirefox();
        return webDriver;
    }

    @AfterSuite(alwaysRun = true)
    public void setupAfterSuite() {
        webDriver.quit();
    }
}
