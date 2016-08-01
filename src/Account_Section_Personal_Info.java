import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Account_Section_Personal_Info {

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
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Account Section Test (Personal Information Positive): success");
        } else {
            System.out.println("Account Section Test (Personal Information Positive): fail!");
        }
    }

    @Test
    void personalInformationEmptyFields() {
        webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
        webDriver.findElement(By.xpath(".//*[@id='p_fname']")).clear();
        webDriver.findElement(By.xpath(".//*[@id='p_lname']")).clear();
        webDriver.findElement(By.xpath(".//*[@id='p_username']")).clear();
        webDriver.findElement(By.xpath(".//*[@id='p_phone']")).clear();
        webDriver.findElement(By.xpath(".//*[@id='p_email']")).clear();
        webDriver.findElement(By.xpath(".//*[@id='p_password_confirmation']")).submit();
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
        if (webDriver.getPageSource().contains("The email field is required.")) {
            webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Account Section Test (Personal Information Empty Fields): success");
        } else {
            System.out.println("Account Section Test (Personal Information Empty Fields): fail!");
        }
    }

    @Test
    void personalInformationLongStrings() {
        File txt = new File("/home/developer/txt.txt");
        String string = null;

        for (int i = 0; i <= 6; i++) {
            webDriver.get("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects");
            webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
            try {
                string = FileUtils.readFileToString(txt, "UTF-8");
            } catch (IOException e) {
               e.printStackTrace();
            }
            if (i == 0) {
                webDriver.findElement(By.xpath(".//*[@id='p_fname']")).sendKeys(string);
            } else if (i == 1) {
                webDriver.findElement(By.xpath(".//*[@id='p_lname']")).sendKeys(string);
            } else if (i == 2){
                webDriver.findElement(By.xpath(".//*[@id='p_username']")).sendKeys(string);
            } else if (i == 3){
                webDriver.findElement(By.xpath(".//*[@id='p_phone']")).sendKeys(string);
            } else if (i == 4){
                webDriver.findElement(By.xpath(".//*[@id='p_email']")).sendKeys(string);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).isEnabled()) {
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            } else if (webDriver.findElement(By.cssSelector("input:invalid")).isEnabled()){
                webDriver.findElement(By.xpath(".//*[@id='p_password']")).click();
            }
            webDriver.findElement(By.xpath(".//*[@id='p_password']")).sendKeys("123123123");
            webDriver.findElement(By.xpath(".//*[@id='p_password_confirmation']")).sendKeys("123123123");
            webDriver.findElement(By.xpath(".//*[@id='p_password_confirmation']")).submit();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (webDriver.getPageSource().contains("The first name may not be greater than 255 characters")) {
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            }
//            if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects")) {
//                System.out.println("Registration Positive Test: fail!");
//                webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[4]/a")).click();
//            } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
//                System.out.println("Registration Positive Test: fail - User exists - Pricing Plan Option " + (i + 1));
//                new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
//                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
//            } else {
//                System.out.println("Registration Positive Test: server error - Pricing Plan Option " + (i + 1));
//            }
        }
    }

//    @AfterTest(alwaysRun = true)
//    public void setupAfterTest() {
//        webDriver.quit();
//    }
}
