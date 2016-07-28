import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Registration_positive {
    WebDriver webDriver = new WebDriverInit().getWebDriver();

    @org.testng.annotations.Test
    public void firstTest () {

        WebElement element = webDriver.findElement(By.id("email_signup"));
        element.sendKeys("user@user11.user");
        WebElement element1 = webDriver.findElement(By.id("username_signup"));
        element1.sendKeys("Company_0");
        WebElement element2 = webDriver.findElement(By.id("password_signup"));
        element2.sendKeys("123123123");
        WebElement element3 = webDriver.findElement(By.id("password_confirmation"));
        element3.sendKeys("123123123");
        WebElement element4 = webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]"));
        element4.click();
        webDriver.findElement(By.className("btn")).click();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/co/projects")) {
            System.out.println("Registration: success");
        } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            System.out.println("Registration: fail - User exists");
            new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
            webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
        }
//        webDriver.close();
//        webDriver = null;
    }

    @AfterTest(alwaysRun = true)
    public void setupAfterTest() {
        webDriver.quit();
    }
}
