package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageobjects.HomePage;
import pagetests.HomePageTest;

import java.time.Duration;

import static pagetests.HomePageTest.prop;

public class SetupDriver {
    public WebDriver driver;
    public static WebDriverWait wait;
    public HomePage homePage;
    public HomePageTest homePageTest;

    @BeforeClass(alwaysRun = true)
    public void tearUp(){
        driver = launchBrowser(prop.getProperty("browser"));//.toLowerCase()
        System.out.println("Launching Browser");
        driver.get(prop.getProperty("url"));
        System.out.println("Current URL : "+driver.getCurrentUrl());
        System.out.println("Website Title : "+driver.getTitle());
        //Creating object of home page
        homePage = new HomePage(driver);
        homePageTest = new HomePageTest(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }
    @BeforeMethod(alwaysRun = true)
    public void Goto(){
        driver.get(prop.getProperty("url"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        System.out.println("Closing Browser");
    }

    public static WebDriver launchBrowser(String browserName){
        // i add this option to avoid this error :
        // Java.io.IOException: Invalid Status code=403 text=Forbidden
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        // and i add option between brackets for chrome
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(option);
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("Browser name is incorrect");
            System.exit(1);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return driver;
    }

}
