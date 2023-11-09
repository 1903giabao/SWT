package BaiTap;

import pom.BackEndPage;
import pom.CartPage;
import pom.CheckoutPage;
import pom.LoginPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.SQLOutput;

@Test
public class TestCase10 {
    public static void testTC10() {
        WebDriver driver = driverFactory.getChromeDriver();
        String username = "user01";
        String password = "guru99com";
        String orderId = "1919";
        String fromDate = "4/4/2023";
        String toDate = "5/7/2023";
        String number = "10";

        try{
            BackEndPage page = new BackEndPage(driver);
            page.Login(username, password);
            page.clickLogin();
            Thread.sleep(2000);

            page.closePopUp();
            page.goToOrders();
            Thread.sleep(2000);

            page.enterOrderId(orderId);
            page.enterFromDate(fromDate);
            page.enterToDate(toDate);
            page.clickSearch();
            Thread.sleep(2000);

            page.takeScreenShot(number);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
