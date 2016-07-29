import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Login_logout_positive {
    WebDriver webDriver = new WebDriverInit().getWebDriver();
    int min = 0;
    int max = 9999;

    @org.testng.annotations.Test
    public void login () {

        int random = min + (int)(Math.random() * ((max - min) + 1));

        //registration
        webDriver.get("http://versionhistory.demo.zerp.info/login");
        webDriver.findElement(By.id("email_signup")).sendKeys("user@us" + random + "er.user");
        webDriver.findElement(By.id("username_signup")).sendKeys("Company" + random);
        webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
        webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
        webDriver.findElement(By.className("btn")).click();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects")) {
            webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[4]/a")).click();
        } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            System.out.println("Registration: fail - User exists");
            new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
            webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
        }

        WebElement element = webDriver.findElement(By.id("email_signin"));
        element.sendKeys("user@us" + random + "er.user");
        WebElement element1 = webDriver.findElement(By.id("password_signin"));
        element1.sendKeys("123123123");
        WebElement element2 = webDriver.findElement(By.name("remember"));
        element2.click();
        element2.submit();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects")) {
            System.out.println("Login Test: success");
        }
    }

    @org.testng.annotations.Test
    public void logout() {
        WebElement element = webDriver.findElement(By.linkText("Log Out"));
        element.click();
        System.out.println("Logout Test: success");
    }

//    @org.testng.annotations.Test
//    public void accountCancel() {
////        webDriver.get("http://versionhistory.demo.zerp.info/admin/company/account");
//        login();
//        WebElement element = webDriver.findElement(By.className("cancel_account"));
//        element.click();
//        WebElement element1 = webDriver.findElement(By.className("btn-danger"));
//        element1.click();
//
//        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='AccountCancel']/div/div/form/div[2]/input[1]")));
//        webDriver.findElement(By.xpath(".//*[@id='AccountCancel']/div/div/form/div[2]/input[1]")).click();
//
//        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")));
//        webDriver.findElement(By.xpath(".//*[@id='statusModal']/div/div/div[3]/button")).click();
//        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
//            System.out.println("Account Cancel Test: success");
//        }
//    }

    @AfterTest(alwaysRun = true)
    public void setupAfterTest() {
        webDriver.quit();
    }
}
