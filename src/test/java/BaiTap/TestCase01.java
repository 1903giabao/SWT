package BaiTap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

import java.io.File;
import java.util.Locale;

/*

Test Steps
Step 1. Go to http://live.techpanda.org/
Step 2. Verify Title of the page
Step 3. Click on -> MOBILE -> menu
Step 4. In the list of all mobile , select SORT BY -> dropdown as name
Step 5. Verify all products are sorted by name

*/

public class TestCase01 {
        public static void main(String[] args) {
            // Create an instance of the ChromeDrive

            WebDriver driver = new ChromeDriver();
            // Step 1: Navigate to the specified URL

            driver.get("http://live.techpanda.org/");
            // Step 2: Verify the title of the page

            String expectedTitle = "THIS IS DEMO SITE FOR   ";

            String actualTitle = driver.findElement(By.cssSelector("h2")).getText();
            AssertJUnit.assertEquals(expectedTitle,actualTitle);
            // Step 3: Click on the "MOBILE" menu

            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();
            // Step 4: Select SORT BY -> dropdown as name

            WebElement sortByDropdown = driver.findElement(By.cssSelector("select[title='Sort By']"));
            Select select = new Select(sortByDropdown);
            select.selectByVisibleText("Name");
            // Step 5: Verify all products are sorted by name

            WebElement productList = driver.findElement(By.className("products-grid"));
            if (isSorted(productList, "h2")) {
                System.out.println("Products are sorted by name");
            } else {
                System.out.println("Products are not sorted by name");
            }
            //Step 6: Verify all products are
            int scc=1;
            scc = (scc+1);
            try{
                File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                String png = ("C:\\Users\\ADMIN\\Pictures\\Screenshots\\testcase") + scc + ".png";
                FileUtils.copyFile(srcFile, new File(png));
            }catch (Exception e){
                e.printStackTrace();
            }


            // Close the browser
//            Thread.sleep(2000);
            driver.quit();
        }
        private static boolean isSorted(WebElement element, String tag) {
            java.util.List<WebElement> elements = element.findElements(By.tagName(tag));
            String[] arr = new String[elements.size()];
            for (int i = 0; i < elements.size(); i++) {
                arr[i] = elements.get(i).getText();
            }
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    return false;
                }
            }
            return true;
        }
}
