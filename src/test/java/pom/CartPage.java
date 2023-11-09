package pom;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCountry(String country){
        WebElement selectCountry = driver.findElement(By.id("country"));
        Select select = new Select(selectCountry);
        select.selectByVisibleText(country);
    }

    public void enterStateProvince(String state){
        WebElement selectState = driver.findElement(By.id("region_id"));
        Select select = new Select(selectState);
        select.selectByVisibleText(state);
    }

    public void enterZip(String zip){
        WebElement selectZip = driver.findElement(By.id("postcode"));
        selectZip.clear();
        selectZip.sendKeys(zip);
    }

    public void clickEstimate(){
        WebElement estimate = driver.findElement(By.xpath("//span[contains(text(),'Estimate')]"));
        estimate.click();
    }

    public void verifyShippingCost(){
        WebElement cost = driver.findElement(By.xpath("//label[@for='s_method_flatrate_flatrate']//span[@class='price'][normalize-space()='$5.00']"));
        String expectedCost = "$5.00";
        Assert.assertEquals(expectedCost, cost.getText());
        cost.click();
    }

    public void clickUpdateTotal(){
        WebElement update = driver.findElement(By.xpath("//span[contains(text(),'Update Total')]"));
        update.click();
    }

    public void verifyTotal(){
        WebElement cost = driver.findElement(By.xpath("//td[@class='a-right']//span[@class='price'][normalize-space()='$5.00']"));
        String expectedCost = "$5.00";
        Assert.assertEquals(expectedCost, cost.getText());
        WebElement grandTotal = driver.findElement(By.xpath("//span[normalize-space()='$135.00']"));
        String expectedTotal = "$135.00";
        Assert.assertEquals(expectedTotal, grandTotal.getText());
    }

    public void clickCheckout(){
        WebElement checkout = driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']"));
        checkout.click();
    }

    public void enterCoupon(String coupon){
        WebElement c = driver.findElement(By.id("coupon_code"));
        c.clear();
        c.sendKeys(coupon);
    }

    public void applyCoupon(){
        WebElement apply = driver.findElement(By.xpath("//span[contains(text(),'Apply')]"));
        apply.click();
    }

    public void verifyDiscount(String expected){
        WebElement discount = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[2]/td[2]"));
        Assert.assertEquals(discount.getText(), expected);
    }

    public void verifyTotalAfterDiscount(double expected){
        WebElement grandTotal = driver.findElement(By.cssSelector("td[class='product-cart-price'] span[class='price']"));
        String total = grandTotal.getText().substring(1, grandTotal.getText().length());
        double a = Double.parseDouble(total);
        double b = a*(1 - expected);
        String c = "$" + b + "0";
        WebElement actual = driver.findElement(By.cssSelector("strong span[class='price']"));
        Assert.assertEquals(actual.getText(), c);
    }
}