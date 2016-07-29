import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Sign_In_Invalid {
    WebDriver webDriver = new WebDriverInit().getWebDriver();

    @org.testng.annotations.Test
    void emptyEmail() {
        webDriver.findElement(By.xpath(".//*[@id='password_signin']")).sendKeys("eeeeeeeee");
        webDriver.findElement(By.xpath(".//*[@id='password_signin']")).submit();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/eee/projects")) {
            System.out.println("Empty Email Login Test: fail!");
        } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
            webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            System.out.println("Empty Email Login Test: success");
        }

    }


    @org.testng.annotations.Test
    void emptyPass() {
        webDriver.findElement(By.xpath(".//*[@id='email_signin']")).sendKeys("eee@e.e");
        webDriver.findElement(By.xpath(".//*[@id='email_signin']")).submit();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/eee/projects")) {
            System.out.println("Empty Password Login Test: fail!");
        } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            System.out.println("Empty Password Login Test: success");
        }
    }

    @org.testng.annotations.Test
    void deprecatedSymbols() {
        webDriver.findElement(By.xpath(".//*[@id='email_signin']")).sendKeys("<script>echo'Hello'</script>");
        webDriver.findElement(By.xpath(".//*[@id='password_signin']")).sendKeys("<script>echo'Hello'</script>");
        webDriver.findElement(By.xpath(".//*[@id='password_signin']")).submit();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/eee/projects")) {
            System.out.println("Deprecated Symbols Login Test: fail!");
        } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            webDriver.get("http://versionhistory.demo.zerp.info/login");
            System.out.println("Deprecated Symbols Login Test: success");
        }
    }

    @AfterTest(alwaysRun = true)
    public void setupAfterTest() {
        webDriver.quit();
    }
}
