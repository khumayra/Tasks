package automation.com.tasks;

import automation.utilities.BrowserUtils;
import automation.utilities.DriverFactory;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class WebTable {
    /**
     * Go to http://practice.cybertekschool.com/tables
     * 1.find all data from row 2
     * 2.find all data from column 3
     * 3.find all information about Doe Jason and print it out
     * 4.return column index by column name: first name
     * 5.delete all records from table 1
     */
    private WebDriver driver;
    private String URL = "http://practice.cybertekschool.com/tables";
    private By row2By = By.xpath("//table[1]//tr[2]//td");
    private By column3By = By.xpath("//table[1]//tr//td[3]");
    private By lastNameBy = By.xpath("//table[1]//tr//td[1]");
    private By firstNameBy = By.xpath("//table[1]//tr//td[2]");
    private By headerBy = By.xpath("//table[1]//th");
    private By deleteBy = By.xpath("//table[1]//a[@href='#delete']");

    @Test (description = "find all data from row 2")
    public void test1(){
        List <String> expected =  new ArrayList<>(Arrays.asList("Bach","Frank", "fbach@yahoo.com", "$51.00", "http://www.frank.com", "edit delete"));
        List <String> actual = BrowserUtils.printWebElementsText(driver.findElements(row2By));
        System.out.println(actual);
        System.out.println(expected);
        Assert.assertEquals(actual,expected);
    }
    @Test (description = "find all data from column 3")
    public void test2(){
        List <String> expected = new ArrayList<>(Arrays.asList("jsmith@gmail.com","fbach@yahoo.com","jdoe@hotmail.com","tconway@earthlink.net"));
        List <String> actual = BrowserUtils.printWebElementsText(driver.findElements(column3By));
        Assert.assertEquals(actual,expected);
    }
    @Test (description = "find all information about Doe Jason and print it out")
    public void test3(){
    List<String> lastNames=BrowserUtils.printWebElementsText(driver.findElements(lastNameBy));
    List<String> firstNames = BrowserUtils.printWebElementsText(driver.findElements(firstNameBy));

    int index =0;
        for (int i = 0; i <lastNames.size() ; i++) {
                if(lastNames.get(i).equalsIgnoreCase("Doe")
                    &&firstNames.get(i).equalsIgnoreCase("Jason")){
                    index=i+1;
            }
        }

        List<String> actual =BrowserUtils.printWebElementsText(driver.findElements(By.xpath("//table[1]//tr["+index+"]//td")));
        List <String> expected = new ArrayList<>(Arrays.asList("Doe","Jason","jdoe@hotmail.com","$100.00","http://www.jdoe.com","edit delete"));
        Assert.assertEquals(actual,expected);
    }
    @Test(description = "return column index by column name : first name")
    public void test4(){
        List<String> headers=BrowserUtils.printWebElementsText(driver.findElements(headerBy));
        int index=0;
        BrowserUtils.wait(5);
        for (int i=0; i< headers.size();i++) {
            if (headers.get(i).equalsIgnoreCase("first name")) {
                index = i + 1;
            }
        }
            BrowserUtils.wait(5);
            List<String> actual = BrowserUtils.printWebElementsText(driver.findElements(By.xpath("//table[1]//td["+index+"]")));////table[1]//td[2]
            List<String> expected=new ArrayList<>(Arrays.asList("John","Frank","Jason","Tim"));
            Assert.assertEquals(actual,expected);
        }

    @Test (description ="delete all records from table 1 and verify that all records are deleted")
    public void test5(){
        List<WebElement> links = driver.findElements(deleteBy);

        for (int i = 0; i <links.size() ; i++) {
            links.get(i).click();
            --i;
            links = driver.findElements(deleteBy);
        }
        System.out.println(BrowserUtils.printWebElementsText(links));
        Assert.assertTrue(links.isEmpty());
    }

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        driver.get(URL);
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
    }
    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
