package automation.com.day1;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class MyFirstProject {
    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.etsy.com/");

        // prosto opened a page

        WebElement search = driver.findElement(By.id("global-enhancements-search-query"));
        search.sendKeys("Golden Earrings");
        search.submit();
        Thread.sleep(4000);

//        WebElement searchButton = driver.findElement(By.className("etsy-icon wt-nudge-b-1"));
//        searchButton.submit();
//        Thread.sleep(4000);

        driver.quit();
    }
}
