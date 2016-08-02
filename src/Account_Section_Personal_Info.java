import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

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
            Login();
        } else {
            System.out.println("Account Section Test (BeforeTest): server error");
            System.exit(1);
        }
    }

    @Test
    void personalInformationPositive() {
        webDriver.get("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects");
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
        webDriver.get("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects");
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
        String fieldName = null;
        WebElement invalidInput = null;

        //выполняем в цикле для проверки всех полей, для которых эта проверка возможна
        for (int i = 0; i <= 4; i++) {
            //переходим на страницу проектов
            webDriver.get("http://versionhistory.demo.zerp.info/admin/company" + random + "/projects");
            webDriver.findElement(By.xpath(".//*[@id='container']/header/div/div/div/ul/li[2]/a")).click();
            //считываем файл, содержимое которого будем добавлять в поля
            try {
                string = FileUtils.readFileToString(txt, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //выбираем поле ввода
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
                //присваиваем переменной элемент - попап браузера (если есть)
                invalidInput = webDriver.findElement(By.cssSelector("input:invalid"));
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //проверяем ранее присвоенное значение
            if (invalidInput != null) {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (webDriver.findElements(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")) == null){ //проверяем наличие окна ошибки ввода
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (webDriver.findElements(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).size() != 0){ //проверяем наличие окна ошибки ввода
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
            }
            webDriver.findElement(By.xpath(".//*[@id='p_password']")).sendKeys("123123123");
            webDriver.findElement(By.xpath(".//*[@id='p_password_confirmation']")).sendKeys("123123123");
            webDriver.findElement(By.xpath(".//*[@id='p_password_confirmation']")).submit();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 0) {
                fieldName = "First Name field";
            } else if (i == 1) {
                fieldName = "Last Name field";
            } else if (i == 2) {
                fieldName = "Company Name field";
            } else if (i == 3) {
                fieldName = "Phone Number field";
            }  else if (i == 4) {
                fieldName = "Email Address field";
            }
            if (webDriver.getPageSource().contains("may not be greater than")) {
                webDriver.findElement(By.xpath(".//*[@id='errorModal']/div/div/div[3]/button")).click();
                System.out.println("Account Section Test (Personal Information Long Fields): success - " + fieldName);
            } else if (webDriver.getPageSource().contains("Personal Information was saved")){
                System.out.println("Account Section Test (Personal Information Long Fields): fail! - " + fieldName);
            }
        }
    }

    @AfterTest(alwaysRun = true)
    public void setupAfterTest() {
        webDriver.quit();
    }
}
