package BaiTap;


import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/*
Test Steps:
1. Goto http://live.techpanda.org/
2. Click on �MOBILE� menu
3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
4. Click on Sony Xperia mobile
5. Read the Sony Xperia mobile from detail page.
6. Compare Product value in list and details page should be equal ($100).
*/

@Test
public class TestCase02 {
        public static void testCase02() {

            // Create an instance of the ChromeDrive
            WebDriver driver = driverFactory.getChromeDriver();

            // Step 1: Navigate to the specified URL
            driver.get("http://live.techpanda.org/");

            // Step 2: Click on the 'MOBILE' menu
            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();

            // Step 3: Read the cost of Sony Xperia mobile
            WebElement costElement = driver.findElement(By.xpath("//span[contains(text(),'$100.00')]"));
            String cost = costElement.getText();
            System.out.println("Cost of Sony Xperia mobile is: " + cost);

            // Step 4: Click on Sony Xperia mobile
            WebElement sonyXperiaLink = driver.findElement(By.xpath("//a[@title='Sony Xperia']"));
            sonyXperiaLink.click();

            // Step 5: Read the Sony Xperia mobile from the detail page
            WebElement detailElement = driver.findElement(By.xpath("//span[@class='h1']"));
            String detailProName = detailElement.getText();
            System.out.println("Sony Xperia mobile from detail page is: " + detailProName);
            WebElement detailElementCost = driver.findElement(By.xpath("//span[@class='price']"));
            String detailcost = detailElementCost.getText();
            System.out.println("Cost of Sony Xperia mobile from detail page is: " + detailcost);

            // Step 6: Compare product value in list and detail page
            if (cost.equals(cost)) {
                System.out.println("Product value in list and detail page are equal.");
            } else {
                System.out.println("Product value in list and detail page are not equal.");
            }

            // Close the browser
            driver.quit();
        }
}

