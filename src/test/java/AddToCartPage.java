
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCartPage {
    WebDriver driver;
    WebDriverWait wait;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addItemToCart() {
        driver.get("https://www.automationexercise.com/products");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-product-id='1']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id='cartModal']//button[contains(text(),'Continue Shopping')]"))).click();
    }
}
