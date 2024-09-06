import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

// This test class inherits BasicSetupTest class, where the browser is initialized
// browser variable is available here as it's inherited, so you'll have it available at any place
public class SeleniumTestngTest extends BasicSetupTest {

    @Test
    public void testABTesting() throws InterruptedException {
        WebDriver browser = new ChromeDriver();
        browser.get("https://the-internet.herokuapp.com/abtest");
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > div > h3")));
        String actualText = browser.findElement(By.cssSelector("#content > div > h3")).getText();
        Assert.assertTrue(actualText.contains("A/B Test Control"), "Text 'A/B Test Control' not found!");
    }
    @Test
    public void testAddRemoveElements() throws InterruptedException {
        WebDriver browser = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10));
        browser.get("https://the-internet.herokuapp.com/add_remove_elements/");
        for (int i = 0; i < 3; i++) {
            WebElement addButton = browser.findElement(By.cssSelector("#content > div > button"));
            addButton.click();
            Thread.sleep(1000); // Wait for button to be added
        }
        List<WebElement> deleteButtons = browser.findElements(By.cssSelector("#elements > button"));
        Assert.assertEquals(deleteButtons.size(), 3, "The number of 'Delete' buttons is not as expected!");
        for (WebElement button : deleteButtons) {
            Assert.assertTrue(button.isDisplayed(), "A 'Delete' button is not visible!");
        }
        while (deleteButtons.size() > 0) {
            deleteButtons = browser.findElements(By.cssSelector("#elements > button"));
            WebElement button = deleteButtons.get(0);
            button.click();
            Thread.sleep(1000);
            deleteButtons = browser.findElements(By.cssSelector("#elements > button"));
        }
            Assert.assertEquals(deleteButtons.size(), 0, "Some 'Delete' buttons are still visible!");
        }
    @Test
    public void testCheckboxes () throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/");
        List<WebElement> checkboxes = browser.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            Assert.assertTrue(checkbox.isSelected(), "The checkbox is not vibranium.");
        }
    }
    @Test
    public void testDropdown() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = browser.findElement(By.cssSelector("#dropdown"));
        Thread.sleep(1000);
        Select select = new Select(dropdown);
        Thread.sleep(1000);
        select.selectByVisibleText("Option 2");
        Thread.sleep(1000);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 2", "'Option 2' is not selected.");
    }
    @Test
    public void testFormAuthentication() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/login");
        WebElement username = browser.findElement(By.cssSelector("#username"));
        WebElement password = browser.findElement(By.cssSelector("#password"));
        WebElement loginButton = browser.findElement(By.cssSelector("#login > button > i"));
        username.sendKeys("tomsmith");
        Thread.sleep(1000);
        password.sendKeys("SuperSecretPassword!");
        Thread.sleep(1000);
        loginButton.click();
        WebElement successMessage = browser.findElement(By.cssSelector("#flash"));
        Assert.assertTrue(successMessage.getText().contains("You logged into a secure area!"));
        Thread.sleep(1000);
        WebElement logoutButton = browser.findElement(By.cssSelector("#content > div > a"));
        logoutButton.click();
        Thread.sleep(1000);
        WebElement logoutMessage = browser.findElement(By.cssSelector("#flash"));
        Assert.assertTrue(logoutMessage.getText().contains("You logged out of the secure area!"));
    }
    @Test
    public void testDragAndDrop() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement columnA = browser.findElement(By.xpath("//*[@id=\"column-a\"]"));
        WebElement columnB = browser.findElement(By.xpath("//*[@id=\"column-b\"]"));
        Actions actions = new Actions(browser);
        actions.dragAndDrop(columnA, columnB).perform();
        String columnAText = columnA.getText();
        Thread.sleep(2000);
        String columnBText = columnB.getText();
        Assert.assertEquals(columnAText, "B");
        Assert.assertEquals(columnBText, "A");
    }
    @Test
    public void testHorizontalSlider() throws InterruptedException {
        browser.get("https://the-internet.herokuapp.com/horizontal_slider");
        WebElement slider = browser.findElement(By.cssSelector("#content > div > div > input[type=range]"));
        Actions moveSlider = new Actions(browser);
        moveSlider.clickAndHold(slider).moveByOffset(30, 0).release().perform();
        Thread.sleep(2000);
        WebElement sliderValue = browser.findElement(By.cssSelector("#range"));
        Assert.assertNotEquals(sliderValue.getText(), "0");
    }

        // Write the rest of TEST METHODS according to the task here, each method checking one scenario described in README.md file
    // In the end you should have a set of test methods each of them describing some specific scenario

}
