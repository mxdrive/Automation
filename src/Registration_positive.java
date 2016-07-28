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

        webDriver.findElement(By.id("email_signup")).sendKeys("user@user11.user");
        webDriver.findElement(By.id("username_signup")).sendKeys("Company_0");
        webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
        webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
        webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
        webDriver.findElement(By.className("btn")).click();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/co/projects")) {
            System.out.println("Registration: success");
        } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            System.out.println("Registration: fail - User exists");
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
