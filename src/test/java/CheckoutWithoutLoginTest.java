import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("Cart")
@Feature("Checkout Without Login")
public class CheckoutWithoutLoginTest extends BaseTest {

    @Test(description = "Verify user is redirected to login page when trying to checkout without logging in")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Checkout flow without authentication")
    public void testCheckoutWithoutLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get("https://automationexercise.com");

        // Add product to cart
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);

        // Wait and click "View Cart"
        WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartLink);

        // Scroll to Proceed To Checkout and click
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='btn btn-default check_out']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
       Assert.assertEquals( driver.findElement(By.xpath("//*[@id=\"checkoutModal\"]/div/div/div[2]/p[1]")).getText(),"Register / Login account to proceed on checkout.");



    }
}
