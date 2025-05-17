package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Given("user is logged in")
    public void user_is_logged_in() {
        driver.get("https://www.saucedemo.com");
         driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("user adds an item to the cart")
    public void user_adds_item_to_cart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("the cart badge should show {string}")
    public void cart_badge_should_show(String expectedCount) {
        String actualCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(expectedCount, actualCount);
    }

    @Given("user has one item in the cart")
    public void user_has_one_item_in_cart() {
        user_is_logged_in(); // reuse login
        user_adds_item_to_cart(); // reuse add
    }

    @When("user removes the item")
    public void user_removes_item() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    @Then("the cart badge should not be visible")
    public void cart_badge_should_not_be_visible() {
        boolean badgeVisible;
        try {
            badgeVisible = driver.findElement(By.className("shopping_cart_badge")).isDisplayed();
        } catch (NoSuchElementException e) {
            badgeVisible = false; // badge disappears if no items
        }
        Assert.assertFalse(badgeVisible);
    }
}

