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
public class TestCase09 {
    public static void testTC9() {
        WebDriver driver = driverFactory.getChromeDriver();
        String coupon = "GURU50";
        String discount = "-$5.00";
        double dis = 0.05;

        try{
            driver.get("http://live.techpanda.org/");

            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();

            WebElement addToCartButton = driver.findElement(By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]"));
            addToCartButton.click();
            Thread.sleep(2000);

            CartPage page = new CartPage(driver);
            page.enterCoupon(coupon);
            page.applyCoupon();
            Thread.sleep(2000);

            page.verifyDiscount(discount);
            System.out.println("Discount generated: " + discount);

            page.verifyTotalAfterDiscount(dis);
            System.out.println("Price is discounted by 5%");

        }
        catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}