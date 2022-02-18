package HudlTest;

import Utils.PropertiesLoader;
import Utils.Utils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Utils {
    public WebDriver driver;
    public PropertiesLoader propertiesLoader = new PropertiesLoader();
    public WebDriverWait wait;
    public String baseUrl = new String();
    public String hudlEmail;
    public String hudlPassword;
    protected int TIMEOUT = 5;

    @Before
    public void beforeEachTestMethod() {
        System.setProperty("webdriver.chrome.driver", propertiesLoader.loadProperty("webdriver.chrome.driver").toString());
        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File(propertiesLoader.loadProperty("chrome.bin").toString()));
        Object chromeOptions = propertiesLoader.loadProperty("browser.chrome.options");
        if (chromeOptions == null || chromeOptions.toString().isEmpty()) {
            driver = new ChromeDriver();
        } else {
            String[] optionsArray = chromeOptions.toString().split(",");
            options.addArguments(Arrays.asList(optionsArray));
            driver = new ChromeDriver(options);
        }
        baseUrl = propertiesLoader.loadProperty("base.url").toString();
        wait = new WebDriverWait(driver, TIMEOUT);

        hudlEmail = propertiesLoader.loadProperty("hudl.email").toString();
        hudlPassword = propertiesLoader.loadProperty("hudl.password").toString();
    }


    @After
    public void end() {
        driver.quit();
        System.out.println("End");
    }

}
