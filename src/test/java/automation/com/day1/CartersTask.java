package automation.com.day1;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class CartersTask {
    public static void main(String[] args) throws Exception {
        searchBox("dress");
    }
    public static void searchBox(String item) throws Exception{
        /**
         * Method goes to carters.com and searches for dress
         */
        WebDriverManager.chromedriver().version("79.0").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.etsy.com/");

        driver.findElement(By.id("global-enhancements-search-query")).sendKeys(item, Keys.ENTER);
        List <WebElement> allLinks = driver.findElements(By.partialLinkText("https://www.etsy.com"));
        System.out.println(allLinks);


        driver.quit();
    }
}
