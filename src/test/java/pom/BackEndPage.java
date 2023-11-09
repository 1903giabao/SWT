package pom;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class BackEndPage {
    private WebDriver driver;

    public BackEndPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://live.techpanda.org/index.php/backendlogin");
    }

    public void Login(String username, String password){
        WebElement user = driver.findElement(By.id("username"));
        user.clear();
        user.sendKeys(username);
        WebElement pass = driver.findElement(By.id("login"));
        pass.clear();
        pass.sendKeys(password);
    }

    public void clickLogin(){
        WebElement login = driver.findElement(By.xpath("//input[@title='Login']"));
        login.click();
    }

    public void closePopUp(){
        WebElement close = driver.findElement(By.xpath("//span[normalize-space()='close']"));
        close.click();
    }

    public void goToOrders(){
        WebElement sales = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)"));
        sales.click();
        WebElement orders = driver.findElement(By.xpath("//span[normalize-space()='Orders']"));
        orders.click();
    }

    public void enterOrderId(String id){
        WebElement orderId = driver.findElement(By.id("sales_order_grid_filter_real_order_id"));
        orderId.clear();
        orderId.sendKeys(id);
    }

    public void enterFromDate(String date){
        WebElement fromDate = driver.findElement(By.name("created_at[from]"));
        fromDate.clear();
        fromDate.sendKeys(date);
    }

    public void enterToDate(String date){
        WebElement toDate = driver.findElement(By.name("created_at[to]"));
        toDate.clear();
        toDate.sendKeys(date);
    }

    public void clickSearch(){
        WebElement search = driver.findElement(By.xpath("//span[contains(text(),'Search')]"));
        search.click();
    }

    public void takeScreenShot(String num){
        try{
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            String png = ("D:\\FPT\\Term 5\\SWT301\\selenium-webdriver-java\\" + num + ".png");
            FileUtils.copyFile(srcFile, new File(png));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
