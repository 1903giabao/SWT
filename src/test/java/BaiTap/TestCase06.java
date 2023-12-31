package BaiTap;

import pom.CartPage;
import pom.CheckoutPage;
import pom.LoginPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import POM.RegisterPage;
import org.testng.asserts.Assertion;

@Test
public class TestCase06 {
    public static void testCase06() {
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

        try{
            LoginPage login = new LoginPage(driver);
            login.goToMyAccount();
            login.Login(email, password);
            login.clickLogin();
            Thread.sleep(2000);

            WebElement wishlist = driver.findElement(By.linkText("MY WISHLIST"));
            Thread.sleep(2000);

            wishlist.click();
            Thread.sleep(2000);

            WebElement addToCart = driver.findElement(By.xpath("//button[@type='button']//span//span[contains(text(),'Add to Cart')]"));
            Thread.sleep(2000);

            addToCart.click();
            Thread.sleep(2000);

            CartPage cart = new CartPage(driver);
            cart.enterCountry(country);
            cart.enterStateProvince(state);
            cart.enterZip(zip);
            cart.clickEstimate();
            Thread.sleep(2000);

            cart.verifyShippingCost();
            Thread.sleep(2000);

            cart.clickUpdateTotal();
            Thread.sleep(2000);

            cart.verifyTotal();
            Thread.sleep(2000);

            cart.clickCheckout();
            Thread.sleep(2000);

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

        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();

    }
}