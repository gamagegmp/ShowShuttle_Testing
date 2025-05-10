package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Given("user is on the login page")
    public void user_is_on_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("user enters valid username and password")
    public void user_enters_credentials() {
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
        );
        usernameField.sendKeys("standard_user");

        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );
        passwordField.sendKeys("secret_sauce");
    }

    @And("clicks the login button")
    public void user_clicks_login() {
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("login-button"))
        );
        loginButton.click();
    }

    @Then("user should be redirected to the inventory page")
    public void user_on_inventory_page() {
        String currentUrl = wait.until(d -> d.getCurrentUrl());
        if (!currentUrl.contains("inventory")) {
            throw new AssertionError("Login failed. Not redirected to inventory.");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}