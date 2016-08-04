import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Account_Section_User_Accounts {
    WebDriver webDriver = new WebDriverInit().getWebDriver();
    int min = 0;
    int max = 9999;
    int random;

    @BeforeTest
    void Login() {

        random = min + (int)(Math.random() * ((max - min) + 1));
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
            webDriver.quit();
        }
    }

    @Test
    void changeEmailPositive() {
        random = min + (int)(Math.random() * ((max - min) + 1));
        webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
        webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/div[1]/div[1]/h3")).click();
        webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[2]/form/a")).click();
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='m_fname']")));
        webDriver.findElement(By.xpath(".//*[@id='m_email']")).clear();
        webDriver.findElement(By.xpath(".//*[@id='m_email']")).sendKeys("us@" + random + "ee.rr");
        webDriver.findElement(By.xpath(".//*[@id='m_password']")).sendKeys("123123123");
        webDriver.findElement(By.xpath(".//*[@id='m_password_confirmation']")).sendKeys("123123123");
        webDriver.findElement(By.xpath(".//*[@id='m_password_confirmation']")).submit();
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")));
        webDriver.findElement(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")).click();
        if (webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[2]/form/a")).isEnabled()) {
            System.out.println("Account Section Test (User Accounts section - changeEmailPositive): success");
        } else {
            System.out.println("Account Section Test (User Accounts section - changeEmailPositive): fail!");
        }
    }

    @Test
    void deleteAccount() {
        webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
        webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/div[1]/div[1]/h3")).click();
        webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/div[1]/div[2]/div/div/table/tbody/tr/td[2]/a")).click();
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='AccountDelete']/div/div/form/div[2]/input[1]")));
        webDriver.findElement(By.xpath(".//*[@id='AccountDelete']/div/div/form/div[2]/input[1]")).click();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login#user_account")) {
            System.out.println("Account Section Test (User Accounts section - deleteAccount): success");
        }
    }

    @Test
    void userAccountAdd() {

        webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
        webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/div[1]/div[1]/h3")).click();
        webDriver.findElement(By.xpath(".//*[@id='iuaEmailAddress']")).clear();
//        webDriver.findElement(By.xpath(".//*[@id='iuaEmailAddress']")).sendKeys("user" + random + "@user" + random + ".us");
        webDriver.findElement(By.xpath(".//*[@id='iuaEmailAddress']")).sendKeys("user@user111.user");
        webDriver.findElement(By.xpath(".//*[@id='iuaPassword']")).sendKeys("123123123");
        webDriver.findElement(By.xpath(".//*[@id='iuaPassword']")).submit();
        try {
            new WebDriverWait(webDriver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")));
        } catch (Exception e) {
            e.printStackTrace();
            webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            System.out.println("Account Section Test (User Accounts section - deleteAccount): email has been taken");
        }
        if (webDriver.findElement(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")).isDisplayed()) {
            webDriver.findElement(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")).click();
            System.out.println("Account Section Test (User Accounts section - deleteAccount): success");
        } else {
            webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            userAccountAdd();
        }
    }

    @AfterTest(alwaysRun = true)
    void setupAfterTest() {
        webDriver.quit();
    }
}
