package HudlTest.Model;

import HudlTest.Main;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Page extends Main {
    /**
     * Waits for page with given title
     * @param expectedPageTitle expected page title
     */
    public void waitForPage(String expectedPageTitle) {
        wait.until((ExpectedCondition<Boolean>) d -> driver.getTitle().compareTo(expectedPageTitle) == 0);
    }

    /**
     * Waits for element visibility
     * @param cssSelector element css selector
     */
    public void waitForElement (String cssSelector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    /**
     * Sleeps specified amount of milliseconds
     * @param ms time to wait in milliseconds
     */
    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void assertElement(String cssSelector) {
        Assert.assertTrue(cssSelector + " not found!", driver.findElements(By.cssSelector(cssSelector)).size() > 0);
    }

    public void navigateToLogin() {
        driver.get(baseUrl + login);
        waitForElement(".login-go");
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = baseUrl + login;
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    public void fillInLoginCredential(String userName, String password) {
        waitForElement(".login-go");
        WebElement usernameInput = driver.findElement(By.cssSelector("#email"));
        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        WebElement loginBtn = driver.findElement(By.cssSelector(".login-go"));
        usernameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void login(String userName, String password) {
        navigateToLogin();
        fillInLoginCredential(userName, password);
    }

    public void assertEqualsUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    public void assertNotEqualsUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(expectedUrl, actualUrl);
    }
}
