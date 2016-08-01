import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Account_Section {

    WebDriver webDriver = new WebDriverInit().getWebDriver();
    int min = 0;
    int max = 9999;

    @BeforeTest
    void Login() {

        int random = min + (int)(Math.random() * ((max - min) + 1));
        webDriver.get("http://versionhistory.demo.zerp.info/login");
        webDriver.findElement(By.id("email_signup")).sendKeys("user@us" + random + "er.user");
        webDriver.findElement(By.id("username_signup")).sendKeys("Company" + random);
        webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
        webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
        webDriver.findElement(By.className("btn")).click();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects")) {
        } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            System.out.println("Account Section Test (BeforeTest): fail - User exists");
            new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
            webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
        } else {
            System.out.println("Account Section Test (BeforeTest): server error");
        }
    }

    @Test
    void personalInformationPositive() {
        webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
        webDriver.findElement(By.xpath(".//*[@id='p_fname']")).sendKeys("FirstName");
        webDriver.findElement(By.xpath(".//*[@id='p_lname']")).sendKeys("LastName");
        webDriver.findElement(By.xpath(".//*[@id='p_username']")).sendKeys("CompanyName");
        webDriver.findElement(By.xpath(".//*[@id='p_phone']")).sendKeys("(000)0000000");
        webDriver.findElement(By.xpath(".//*[@id='p_password']")).sendKeys("123123123");
        webDriver.findElement(By.xpath(".//*[@id='p_password_confirmation']")).sendKeys("123123123");
        webDriver.findElement(By.xpath(".//*[@id='p_password_confirmation']")).submit();
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")));
        if (webDriver.findElement(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")).isEnabled()) {
            webDriver.findElement(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")).click();
            System.out.println("Account Section Test (Personal Information Positive): success");
        }
    }

    @AfterTest(alwaysRun = true)
    public void setupAfterTest() {
        webDriver.quit();
    }
}
