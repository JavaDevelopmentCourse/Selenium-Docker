package abstracttest;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import listener.TestListener;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.Config;
import utils.Constants;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.UnrecoverableEntryException;
import java.time.Duration;


@Listeners({TestListener.class})
public abstract class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.init();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException, URISyntaxException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException, URISyntaxException {
        Capabilities capabilities = new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("grid url: {}", url);
        return new RemoteWebDriver(new URI(url).toURL(), capabilities);
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }


    // add sleep to get more time for execution so that we can see the real execution in nodes and we can see the recordings
  /* @AfterMethod
   public void sleep()
   {
       Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
   }*/


}


/*
package abstracttest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
   // @Parameters({"browser"})
    public void setDriver() throws MalformedURLException, URISyntaxException {
      */
/*  WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();*//*


        if (Boolean.getBoolean("selenium.grid.enabled"))
        {
            this.driver=getRemoteDriver();
        }
        else
        {
            this.driver=getLocalDriver();
        }
    }

    private WebDriver getRemoteDriver() throws MalformedURLException, URISyntaxException {
        Capabilities capabilities;

            if (System.getProperty("browser").equalsIgnoreCase("chrome"))
        //if (browser.equalsIgnoreCase("chrome"))
            {
                capabilities=new ChromeOptions();
            }else {
                capabilities = new FirefoxOptions();
            }
            return new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(),capabilities);

    }

    private WebDriver getLocalDriver()
    {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

}*/
