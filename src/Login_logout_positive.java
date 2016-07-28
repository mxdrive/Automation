import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Login_logout_positive {
    WebDriver webDriver = new WebDriverInit().getWebDriver();

    @org.testng.annotations.Test
    public void login () {

        WebElement element = webDriver.findElement(By.id("email_signin"));
        element.sendKeys("user@user11.user");
        WebElement element1 = webDriver.findElement(By.id("password_signin"));
        element1.sendKeys("123123123");
        WebElement element2 = webDriver.findElement(By.name("remember"));
        element2.click();
        element2.submit();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/company_0/projects")) {
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
