package BaiTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

@Test
public class TestCase03 {
    public static void testCase03() {
        WebDriver driver = new ChromeDriver();
        try{
            // Step 1: Go to link
            driver.get("http://live.techpanda.org/");

            // Step 2: Click on 'MOBILE' menu
            driver.findElement(By.linkText("MOBILE")).click();

            // Step 3: Click on 'ADD TO CART' for Sony Xperia
            WebElement addToCartButton = driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]//span[1]//span[1]"));
            addToCartButton.click();
            Thread.sleep(2000);

            // Step 4: Change 'QTY' to 1000 and click 'UPDATE' button
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("1000");
            driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();
            Thread.sleep(2000);

            // Step 5: Verify the error message
            String expectedErrorMessage = "The requested quantity for 'Sony Xperia' is not available";
            WebElement actualErrorMessage = driver.findElement(By.xpath("//p[@class='item-msg error']"));
            Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
            Thread.sleep(2000);

            // Step 6: Click on 'EMPTY CART' link
            driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]")).click();
            Thread.sleep(2000);

            // Step 7: Verify cart is empty
            WebElement emptyCartMessage = driver.findElement(By.xpath("//div[@class='page-title']/h1"));
            if (emptyCartMessage.getText().equals("SHOPPING CART IS EMPTY")) {
                System.out.println("Cart is empty.");
            } else {
                System.out.println("Cart is not empty.");
            }
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
