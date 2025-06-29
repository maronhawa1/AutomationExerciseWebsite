import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }
    public void addProductToCart() {
        // הסרת פרסומות iframe ו-divים שמפריעים ללחיצה
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, div[id^=\"aswift\"], ins, .adsbygoogle').forEach(el => el.remove());"
        );

        // המתנה לכך שהכפתור יהיה לחיץ
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-product-id='1']"))
        );

        // גלילה לאלמנט
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);

        // המתנה קצרה אחרי גלילה
        try {
            Thread.sleep(500); // אפשר להמיר ל־WebDriverWait אם רוצים יותר יציבות
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // לחיצה
        addToCartButton.click();
    }



    public void navigateToCheckout() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, .adsbygoogle, div[id^=aswift]').forEach(el => el.remove());"
        );
        driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();
        Thread.sleep(1000);
        WebElement placeOrderButton = driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"));

// גלול לכפתור
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
        Thread.sleep(500); // תן זמן לגלילה להתבצע

// קליק דרך JavaScript (עוקף את הבעיה של iframe)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButton);



    }

    public void fillPaymentDetails(String name, String cardNumber, String cvc, String month, String year) {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe, div[id^=\"aswift\"], ins, .adsbygoogle').forEach(el => el.remove());"
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name_on_card"))).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("card_number"))).sendKeys(cardNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cvc"))).sendKeys(cvc);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("expiry_month"))).sendKeys(month);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("expiry_year"))).sendKeys(year);
    }


    public void submitPayment() {
        driver.findElement(By.id("submit")).click();
    }
}
