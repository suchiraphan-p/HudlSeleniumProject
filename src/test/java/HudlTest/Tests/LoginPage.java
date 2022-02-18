package HudlTest.Tests;

import HudlTest.Model.Page;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {

    /**
     * Test for opening log in page after
     * clicking log in button on the first page
     */
    @Test
    public void navigateToLogin_successful() {
        driver.get(baseUrl);
        WebElement loginBtn = driver.findElement(By.linkText(cssLoginButton));
        loginBtn.click();
        waitForPage(loginPage);
        assertEqualsUrl(baseUrl + login);
        assertElement(cssLoginSubmitButton);
    }

    /**
     * Test for logging in to hudl.com with valid
     *  email and password
     */
    @Test
    public void login_successful() {
        login(hudlEmail, hudlPassword);
        waitForElement(cssWebNav);
        waitForPage(homePage);
        assertEqualsUrl(baseUrl + home);
    }

    /**
     * Test for resetting password
     */
    @Test
    public void openResetPassword_successful() {
        driver.get(baseUrl + login);
        WebElement forgotPasswordLink = driver.findElement(By.cssSelector(cssForgotPasswordLink));
        forgotPasswordLink.click();
        waitForElement(cssForgotEmail);
        WebElement forgotEmailInput = driver.findElement(By.cssSelector(cssForgotEmail));
        forgotEmailInput.sendKeys(hudlEmail);
        WebElement resetButton = driver.findElement(By.cssSelector(cssResetButton));
        resetButton.click();
        waitForElement(cssResetInfo);
        assertElement(cssResetInfo);
    }

    /**
     * Test for logging in to hudl.com with incorrect
     *  email and password
     */
    @Test
    public void login_failed() {
        // email is invalid, password is invalid
        login(invalidEmail, invalidPassword);
        sleep(120);
        assertElement(cssLoginError);

        // email is invalid, password is empty
        login(invalidEmail, "");
        sleep(120);
        assertElement(cssLoginError);

        // email is empty, password is invalid
        login("", invalidPassword);
        sleep(120);
        assertElement(cssLoginError);

        // email is empty, password is empty
        login("", "");
        sleep(120);
        assertElement(cssLoginError);
    }

    /**
     * Test for logging in to hudl.com with valid
     *  email and password and keeping logging in
     *  after re-navigating to hudl.com
     */
    @Test
    public void keepLoginAfterLogin_successful() {
        login(hudlEmail, hudlPassword);
        waitForElement(cssWebNav);
        driver.get(baseUrl);
        waitForPage(homePage);
        assertEqualsUrl(baseUrl + home);
    }

    /**
     * Test for logging out by clicking log out button
     */
    @Test
    public void logoutOnClickButton_successful() {
        login_successful();
        WebElement userMenu = driver.findElement(By.cssSelector(cssGlobalUserMenu));
        userMenu.click();

        WebElement logout = driver.findElement(By.linkText("Log Out"));
        logout.click();

        waitForElement(cssMainNav);
        assertEqualsUrl(baseUrl);
    }

    /**
     * Test for logging out by entering url with /logout
     */
    @Test
    public void logoutByUrl_successful() {
        login_successful();
        driver.get(baseUrl + logout);
        waitForElement(cssMainNav);
        assertEqualsUrl(baseUrl);
    }

    /**
     * Test for navigating to home page failed
     * after logging out
     */
    @Test
    public void navigateToHomeAfterLogout_Failed() {
        logoutByUrl_successful();
        driver.get(baseUrl + home);
        waitForElement(cssLoginSubmitButton);
        assertEqualsUrl(baseUrl + login + forward + home);
        assertNotEqualsUrl(baseUrl + home);
    }

    /**
     * Test for navigating to account settings page
     * after entering /profile url and logging in
     * with valid email and password
     */
    @Test
    public void returnUrlToProfile_Successful() {
        driver.get(baseUrl + profile);
        fillInLoginCredential(hudlEmail, hudlPassword);
        waitForPage(profilePage);
        String actualUrl = baseUrl + profile;
        assertEqualsUrl(actualUrl);
    }

}
