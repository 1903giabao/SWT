package BaiTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestCase04 {
    public static void testCase04() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on 'MOBILE' menu
        driver.findElement(By.linkText("MOBILE")).click();

        // Step 3: Click on 'Add To Compare' for Sony Xperia
        driver.findElement(By.xpath("//a[text()='Add to Compare']")).click();

        // Click on 'Add To Compare' for Iphone
        driver.findElement(By.xpath("(//a[text()='Add to Compare'])[2]")).click();

        // Step 4: Click on 'COMPARE' button
        driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]")).click();

        // Step 5: Switch to the popup window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Verify the pop-up window and check the products are reflected
        WebElement popupHeading = driver.findElement(By.xpath("//h1[normalize-space()='Compare Products']"));
        String expetedHeading = "COMPARE PRODUCTS";
        Assert.assertEquals(popupHeading.getText(), expetedHeading);

        // Step 6: Close the popup window
        driver.close();

        // Switch back to the main window
        //driver.switchTo().defaultContent();

        driver.quit();
    }
}
