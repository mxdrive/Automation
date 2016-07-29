import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Registration_positive {
    WebDriver webDriver = new WebDriverInit().getWebDriver();
    int min = 0;
    int max = 9999;

    @org.testng.annotations.Test
    public void registrationPositive () {

        for (int i = 0; i <= 2; i++) {
            int random = min + (int)(Math.random() * ((max - min) + 1));
            webDriver.get("http://versionhistory.demo.zerp.info/login");
            webDriver.findElement(By.id("email_signup")).sendKeys("user@us" + random + "er11.user");
            webDriver.findElement(By.id("username_signup")).sendKeys("Company" + random);
            webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
            webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
//            webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
            if (i == 0) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[1]")).click();
            } else if (i == 1) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[2]")).click();
            } else {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
            }
            webDriver.findElement(By.className("btn")).click();
            if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects")) {
                System.out.println("Registration: success - Pricing Plan Option " + (i + 1));
                webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[4]/a")).click();
            } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
                System.out.println("Registration: fail - User exists - Pricing Plan Option " + (i + 1));
                new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            }
        }
//        webDriver.close();
//        webDriver = null;
    }

    @AfterTest(alwaysRun = true)
    public void setupAfterTest() {
        webDriver.quit();
    }
}
