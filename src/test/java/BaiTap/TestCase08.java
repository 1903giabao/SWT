package BaiTap;

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
public class TestCase08 {
    public static void testTC8() {
        WebDriver driver = driverFactory.getChromeDriver();
        String email = "giabao1903@gmail.com";
        String password = "19032003";
        String country = "United States";
        String state = "Florida";
        String zip = "2000";
        String firstname = "gia";
        String lastname = "bao";
        String address = "hcm362";
        String city = "HCM";
        String telephone = "0123456789";

        try {
            LoginPage login = new LoginPage(driver);
            login.goToMyAccount();
            login.Login(email, password);
            login.clickLogin();
            Thread.sleep(2000);

            WebElement reorder = driver.findElement(By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']"));
            reorder.click();

            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("10");
            Thread.sleep(2000);
            WebElement update = driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]"));
            WebElement expectedTotal = driver.findElement(By.xpath("//strong//span[@class='price'][normalize-space()='$130.00']"));
            update.click();
            Thread.sleep(2000);

            WebElement grandTotal = driver.findElement(By.xpath("//strong//span[@class='price'][normalize-space()='$1,300.00']"));
            Assert.assertNotEquals(expectedTotal, grandTotal.getText());
            System.out.println("Grand Total is Changed");

            CartPage cart = new CartPage(driver);
            cart.clickCheckout();

            CheckoutPage checkout = new CheckoutPage(driver);
            checkout.newBillingAddress();
            checkout.fillForm1(firstname, lastname, address, city);
            checkout.fillForm2(state, zip, country, telephone);
            checkout.chooseDifferentAddress();
            checkout.clickBillingContinue();
            Thread.sleep(2000);

            checkout.newShippingAddress();
            checkout.fillForm3(firstname, lastname, address, city);
            checkout.fillForm4(state, zip, country, telephone);
            checkout.clickShippingContinue();
            Thread.sleep(2000);

            checkout.clickShippingMethod();
            Thread.sleep(2000);

            checkout.selectCheck();
            checkout.clickPaymentContinue();
            Thread.sleep(2000);

            checkout.clickPlaceOrder();
            Thread.sleep(2000);

            checkout.verifyOrder();
            Thread.sleep(2000);

            checkout.getOrderId();

            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            String png = ("D:\\FPT\\Term 5\\SWT301\\selenium-webdriver-java\\testcase8.png");
            FileUtils.copyFile(srcFile, new File(png));

        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
