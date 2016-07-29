import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import java.util.ArrayList;
import java.util.Arrays;

public class Registration_negative {
    WebDriver webDriver = new WebDriverInit().getWebDriver();
    int min = 0;
    int max = 9999;

    @org.testng.annotations.Test
    public void emptyFields() {

        for (int i = 0; i <= 2; i++) {
            webDriver.get("http://versionhistory.demo.zerp.info/login");
            if (i == 0) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[1]")).click();
            } else if (i == 1) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[2]")).click();
            } else {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
            }
            webDriver.findElement(By.className("btn")).click();
            if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
                System.out.println("Empty Fields Registration Test: success - Pricing Plan Option " + (i + 1));
                new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            } else {
                System.out.println("Empty Fields Registration Test: fail! - Pricing Plan Option " + (i + 1));
            }
        }
    }

    @org.testng.annotations.Test
    public void noEmail() {

        for (int i = 0; i <= 2; i++) {
            webDriver.get("http://versionhistory.demo.zerp.info/login");
            webDriver.findElement(By.id("username_signup")).sendKeys("Company_000");
            webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
            webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
            if (i == 0) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[1]")).click();
            } else if (i == 1) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[2]")).click();
            } else {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
            }
            webDriver.findElement(By.id("password_confirmation")).submit();
            if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
                System.out.println("No Email Registration Test: success - Pricing Plan Option " + (i + 1));
                new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            } else {
                System.out.println("No Email Registration Test: fail! - Pricing Plan Option " + (i + 1));
            }
        }
    }

    @org.testng.annotations.Test
    public void notAnEmail() {

        for (int i = 0; i <= 2; i++) {
            int random = min + (int)(Math.random() * ((max - min) + 1));
            webDriver.get("http://versionhistory.demo.zerp.info/login");
            webDriver.findElement(By.id("email_signup")).sendKeys("useru" + random +"ser");
            webDriver.findElement(By.id("username_signup")).sendKeys("Comp" + random);
            webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
            webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
            if (i == 0) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[1]")).click();
            } else if (i == 1) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[2]")).click();
            } else {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
            }
            webDriver.findElement(By.id("password_confirmation")).submit();
            try {
                new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
                if (webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).isDisplayed()) {
                    System.out.println("Not An Email Registration Test: success - Pricing Plan Option " + (i + 1));
                    webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
                } else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/comp" + random + "name/projects")) {
                    System.out.println("Not An Email Registration Test: fail! - Pricing Plan Option " + (i + 1));
                }
            } catch (Exception ignored) {}
        }
    }

    @org.testng.annotations.Test
    public void noCompany() {

        for (int i = 0; i <= 2; i++) {
            webDriver.get("http://versionhistory.demo.zerp.info/login");
            webDriver.findElement(By.id("email_signup")).sendKeys("user@user1r1111.user");
            webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
            webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
            if (i == 0) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[1]")).click();
            } else if (i == 1) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[2]")).click();
            } else {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
            }
            webDriver.findElement(By.className("btn")).click();
            new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
            webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
                System.out.println("No Company Name Registration Test: success - Pricing Plan Option " + (i + 1));
            } else {
                System.out.println("No Company Name Registration Test: fail! - Pricing Plan Option " + (i + 1));
            }
        }
    }

    @org.testng.annotations.Test
    void wrongConfirmPassword() {

        for (int i = 0; i <= 2; i++) {
            webDriver.get("http://versionhistory.demo.zerp.info/login");
            webDriver.findElement(By.id("email_signup")).sendKeys("user@u.ser");
            webDriver.findElement(By.id("username_signup")).sendKeys("Coop");
            webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
            webDriver.findElement(By.id("password_confirmation")).sendKeys("12312312");
            if (i == 0) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[1]")).click();
            } else if (i == 1) {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[2]")).click();
            } else {
                webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
            }
            webDriver.findElement(By.className("btn")).click();
            try {
                new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
                if (webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).isDisplayed()) {
                    System.out.println("Wrong Confirm Password Registration Test: success - Pricing Plan Option " + (i + 1));
                    webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
                } else {
                    System.out.println("Wrong Confirm Password Registration Test: fail! - Pricing Plan Option " + (i + 1));
                }
            } catch (Exception ignored) {}
        }
    }

    @org.testng.annotations.Test
    public void deprecatedSymbolsEmail() {

        for (int i = 0; i <= 2; i++) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList("'", "<", "*", "&", "%", "$"));
//            int random = min + (int)(Math.random() * ((max - min) + 1));

            for (int j = 0; j < list.size(); j++) {
                int random = min + (int)(Math.random() * ((max - min) + 1));
                webDriver.findElement(By.id("email_signup")).sendKeys("us" + list.get(j) + random + "er@us.er");
    //            element.sendKeys("us" + list.get(i) + "er@us.er");
                webDriver.findElement(By.id("username_signup")).sendKeys("Comp" + random + "Name");
                webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
                webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
                if (i == 0) {
                    webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[1]")).click();
                } else if (i == 1) {
                    webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[2]")).click();
                } else {
                    webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
                }
                webDriver.findElement(By.className("btn")).click();
    //            try {
    //                Thread.sleep(5000);
    //            } catch (InterruptedException e) {
    //                e.printStackTrace();
    //            }
                if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/comp" + random + "name/projects")) {
                    System.out.println("Deprecated Symbols " + list.get(j) + " Registration Test: fail (registration successful) - Pricing Plan Option " + (i + 1));
                    webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[4]/a")).click();
                }else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
                    Alert popup = null;
                    try {
                        new WebDriverWait(webDriver, 3).until(ExpectedConditions.alertIsPresent());
                        popup = webDriver.switchTo().alert();
                    } catch (Exception ignored) {}
//                    System.out.println(popup);
                    if (popup != null) {
                        if (popup.getText().contains("A part of followed")) {
                            System.out.println("Deprecated Symbol " + list.get(j) + " Registration Test: success - Invalid email address - Pricing Plan Option " + (i + 1));
                        }
                    } else if (webDriver.getPageSource().contains("The Company URL has already been taken")) {
                        if (webDriver.getPageSource().contains("The name has already been taken")) {
                            System.out.println("Deprecated Symbol " + list.get(j) + " Registration Test: success - The Company URL and Name has already been taken - Pricing Plan Option " + (i + 1));
                        } else {
                            System.out.println("Deprecated Symbol " + list.get(j) + " Registration Test: success - The Company URL has already been taken - Pricing Plan Option " + (i + 1));
                        }
                    } else if (webDriver.getPageSource().contains("Please enter an email address")) {
                        System.out.println("Deprecated Symbol " + list.get(j) + " Registration Test: success - Invalid email address - Pricing Plan Option " + (i + 1));
                    } else if (webDriver.getPageSource().contains("A part followed by")) {
                        System.out.println("A part followed by");
                    } else if (webDriver.getPageSource().contains("The name has already been taken")) {
                        System.out.println("Deprecated Symbol " + list.get(j) + " Registration Test: success - The name has already been taken - Pricing Plan Option " + (i + 1));
                    }
                    webDriver.get("http://versionhistory.demo.zerp.info/login");
                }
            }
        }
    }

    @AfterTest(alwaysRun = true)
    public void setupAfterTest() {
        webDriver.quit();
    }
}
