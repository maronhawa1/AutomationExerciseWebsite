
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

@Epic("Login Feature")
@Feature("Login Functionality")
@Listeners({AllureTestNg.class})
public class LoginTest extends BaseTest {

    @Test
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user can login with valid credentials")
    public void testValidLogin() {
        LoginPage login = new LoginPage(driver);
        login.login("maro1@gmail.com", "Maron1234$");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logout')]"))
        ).isDisplayed(), "Login failed!");
    }
}