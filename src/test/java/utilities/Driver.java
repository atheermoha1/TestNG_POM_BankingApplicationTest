package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

//this class is instead of test base
//Singleton driver class
public class Driver {

    //encapsulation
    private static WebDriver driver; //> without initialization this is null

    private Driver(){   // none can create object from this class

    }

    //create a new webdriver if does not exist
    public static WebDriver getDriver() {

        if(driver==null){
            switch (ConfigReader.getProperty("browser").toLowerCase()){
                case "firefox":
                    driver=new FirefoxDriver();
                    break;

                case "Edge":
                    driver=new EdgeDriver();
                    break;

                default:
                    driver=new ChromeDriver();
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    //safely close the driver
    public static void CloseDriver() throws InterruptedException {
      Thread.sleep(2000);
       if(driver != null){
           driver.quit();
           driver=null;
       }
    }

}
