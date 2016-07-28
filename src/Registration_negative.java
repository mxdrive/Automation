import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import java.util.ArrayList;
import java.util.Arrays;

public class Registration_negative {
    WebDriver webDriver = new WebDriverInit().getWebDriver();

    @org.testng.annotations.Test
    public void emptyFields() {

        webDriver.findElement(By.className("btn")).click();
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
        webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            System.out.println("Empty Fields Registration Test: success");
        }
    }

    @org.testng.annotations.Test
    public void noEmail() {

        WebElement element1 = webDriver.findElement(By.id("username_signup"));
        element1.sendKeys("Company_000");
        WebElement element2 = webDriver.findElement(By.id("password_signup"));
        element2.sendKeys("123123123");
        WebElement element3 = webDriver.findElement(By.id("password_confirmation"));
        element3.sendKeys("123123123");
        element3.submit();
//        WebElement element4 = webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]"));
//        element4.click();
//        webDriver.findElement(By.className("btn")).click();
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
        webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            System.out.println("No Email Registration Test: success");
        }
    }

    @org.testng.annotations.Test
    public void notAnEmail() {

        webDriver.get("http://versionhistory.demo.zerp.info/login");
        webDriver.findElement(By.id("email_signup")).sendKeys("useruser");
        webDriver.findElement(By.id("username_signup")).sendKeys("Coop");
        webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
        webDriver.findElement(By.id("password_confirmation")).sendKeys("123123123");
        webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
        webDriver.findElement(By.id("password_confirmation")).submit();
        try {
            new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
            if (webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).isDisplayed()) {
                System.out.println("Not An Email Registration Test: success");
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            }
        } catch (Exception ignored) {}
    }

    @org.testng.annotations.Test
    public void noCompany() {

        WebElement element = webDriver.findElement(By.id("email_signup"));
        element.sendKeys("user@user1r1111.user");
        WebElement element2 = webDriver.findElement(By.id("password_signup"));
        element2.sendKeys("123123123");
        WebElement element3 = webDriver.findElement(By.id("password_confirmation"));
        element3.sendKeys("123123123");
        WebElement element4 = webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]"));
        element4.click();
        webDriver.findElement(By.className("btn")).click();
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
        webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
        if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
            System.out.println("No Company Name Registration Test: success");
        }
    }

    @org.testng.annotations.Test
    void wrongConfirmPassword() {

        webDriver.get("http://versionhistory.demo.zerp.info/login");
        webDriver.findElement(By.id("email_signup")).sendKeys("user@u.ser");
        webDriver.findElement(By.id("username_signup")).sendKeys("Coop");
        webDriver.findElement(By.id("password_signup")).sendKeys("123123123");
        webDriver.findElement(By.id("password_confirmation")).sendKeys("12312312");
        webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]")).click();
        webDriver.findElement(By.className("btn")).click();
        try {
            new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")));
            if (webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).isDisplayed()) {
                System.out.println("Wrong Confirm Password Registration Test: success");
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            }
        } catch (Exception ignored) {}
    }

    @org.testng.annotations.Test
    public void deprecatedSymbolsEmail() {

        ArrayList<String> list = new ArrayList<>(Arrays.asList("'", "<", "*", "&", "%", "$"));
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("a", "s", "d", "f", "g", "h"));

        for (int i = 0; i < list.size(); i++) {
            webDriver.findElement(By.id("email_signup")).sendKeys("us" + list.get(i) + "er@us.er");
//            element.sendKeys("us" + list.get(i) + "er@us.er");
            webDriver.findElement(By.id("username_signup")).sendKeys("Comp" + list1.get(i) + "Name");
            WebElement element2 = webDriver.findElement(By.id("password_signup"));
            element2.sendKeys("123123123");
            WebElement element3 = webDriver.findElement(By.id("password_confirmation"));
            element3.sendKeys("123123123");
            WebElement element4 = webDriver.findElement(By.xpath(".//*[@id='pricing_plan']/option[3]"));
            element4.click();
            webDriver.findElement(By.className("btn")).click();
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/admin/comp" + list1.get(i) + "name/projects")) {
                System.out.println("Deprecated Symbols Registration Test: fail!");
            }else if (webDriver.getCurrentUrl().equalsIgnoreCase("http://versionhistory.demo.zerp.info/login")) {
                Alert popup = null;
                try {
                    new WebDriverWait(webDriver, 3).until(ExpectedConditions.alertIsPresent());
                    popup = webDriver.switchTo().alert();
                } catch (Exception ignored) {}
                System.out.println(popup);
                if (popup != null) {
                    if (popup.getText().contains("A part of followed")) {
                        System.out.println("Deprecated Symbol " + list.get(i) + " Registration Test: success - Invalid email address");
                    }
                } else if (webDriver.getPageSource().contains("The Company URL has already been taken")) {
                    if (webDriver.getPageSource().contains("The name has already been taken")) {
                        System.out.println("Deprecated Symbol " + list.get(i) + " Registration Test: success - The Company URL and Name has already been taken");
                    } else {
                        System.out.println("Deprecated Symbol " + list.get(i) + " Registration Test: success - The Company URL has already been taken");
                    }
                } else if (webDriver.getPageSource().contains("Please enter an email address")) {
                    System.out.println("Deprecated Symbol " + list.get(i) + " Registration Test: success - Invalid email address");
                } else if (webDriver.getPageSource().contains("A part followed by")) {
                    System.out.println("A part followed by");
                } else if (webDriver.getPageSource().contains("The name has already been taken")) {
                    System.out.println("Deprecated Symbol " + list.get(i) + " Registration Test: success - The name has already been taken");
                }
                webDriver.get("http://versionhistory.demo.zerp.info/login");
            }
        }
    }

    @AfterTest(alwaysRun = true)
    public void setupAfterTest() {
        webDriver.quit();
    }
}
