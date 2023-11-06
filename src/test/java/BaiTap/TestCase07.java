package BaiTap;

import pom.LoginPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

@Test
public class TestCase07 {
    public static void testCase07() {
        WebDriver driver = driverFactory.getChromeDriver();
        String email = "giabao1903@gmail.com";
        String password = "19032003";

        try {
            LoginPage login = new LoginPage(driver);
            login.goToMyAccount();
            login.Login(email, password);
            login.clickLogin();
            Thread.sleep(2000);

            WebElement orders = driver.findElement(By.linkText("MY ORDERS"));
            orders.click();

            WebElement view = driver.findElement(By.xpath("//tr[@class='first odd']//a[contains(text(),'View Order')]"));
            view.click();

            WebElement print = driver.findElement(By.xpath("//a[@class='link-print']"));
            print.click();
            Thread.sleep(2000);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
