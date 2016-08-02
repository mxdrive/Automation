import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Account_Section_Communication {
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

    //если сервер отдает ошибку
//    @BeforeTest
//    void LoginTemp() {
//
//        webDriver.get("http://versionhistory.demo.zerp.info/login");
//        webDriver.findElement(By.id("email_signin")).sendKeys("user@user111.user");
//        webDriver.findElement(By.id("password_signin")).sendKeys("123123123");
//        webDriver.findElement(By.id("password_signin")).submit();
//        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/user/projects")) {
//        } else {
//            System.out.println("Account Section Test (BeforeTest): error");
//            webDriver.quit();
//        }
//    }

    @Test
    void checkbox() {
        webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
        webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/form[2]/div/div[1]/h3")).click();
        webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/form[2]/div/div[2]/div/div/div/label/input")).click();
        webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/form[2]/div/div[2]/div/div/div/label/input")).submit();
        if (webDriver.getPageSource().contains("Communication Preferences were saved")) {
            webDriver.findElement(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
            webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/form[2]/div/div[1]/h3")).click();
            if (webDriver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/div/form[2]/div/div[2]/div/div/div/label/input")).isSelected()) {
                System.out.println("Account Section Test (Communication Checkbox): success");
            } else {
                System.out.println("Account Section Test (Communication Checkbox): fail!");
            }
        }
    }

    @AfterTest(alwaysRun = true)
    void setupAfterTest() {
        webDriver.quit();
    }
}
