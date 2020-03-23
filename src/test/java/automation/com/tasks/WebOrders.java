package automation.com.tasks;

import automation.utilities.BrowserUtils;
import automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebOrders {
    /**
     * until 5:37
     * :: TASK for 10 minutes ::
     * Go to web orders page
     * Verify that each person's zip code is matching with expected results saved in list
     */
    private WebDriver driver;
    private By usernameBy= By.id("ctl00_MainContent_username");
    private By passwordBy= By.id("ctl00_MainContent_password");


    @DataProvider (name = "testData")
    public static Object[][] nameSearch(){
        return new Object[][]{{"Paul Brown","748"},{"Mark Smith","76743"},
                             {"Steve Johns","21233"},{"Charles Dodgeson","23233"},
                             {"Susan McLaren","21444"},{"Bob Feather","81734"},
                             {"Samuel Clemens","53665"},{"Clare Jefferson","63325"}};
    }

    @Test (dataProvider = "testData")
    public void checkZipAndUpdate(String name, String zipcode){
        String actual=driver.findElement(By.xpath("//td[text()='"+name+"']/following-sibling::td[7]")).getText();
        String expected = zipcode;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void editZip(){
        List<WebElement> editBtns = driver.findElements(By.xpath("//td/input"));
        WebDriverWait wait = new WebDriverWait(driver,5);
        List<WebElement> actual=driver.findElements(By.xpath("//td[9]"));
        for (WebElement editBtn : editBtns) {
            editBtn.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h2")));
            WebElement zipcode = driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$TextBox5']"));
            zipcode.clear();
            zipcode.sendKeys("11111");
            List<WebElement> radioBtns = driver.findElements(By.name("ctl00$MainContent$fmwOrder$cardList"));

            for (WebElement radioBtn : radioBtns) {
                if(!radioBtn.isSelected()){
                    radioBtn.click();
                    break;
                }
            }

            driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
            BrowserUtils.wait(3);
        }

    }

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.manage().window().maximize();
        driver.findElement(usernameBy).sendKeys("Tester");
        driver.findElement(passwordBy).sendKeys("test", Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }


}
