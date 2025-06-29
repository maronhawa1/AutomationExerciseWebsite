import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("Delete Account")
public class DeleteAccountTest extends BaseTest {

    @Test(description = "Register -> Delete Account -> Attempt to login")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Full account deletion flow")
    public void testDeleteAccountFlow() {
        Reporter.log("🟠 Starting test: Delete Account Flow");
        Allure.step("Open login page");

        driver.get("https://www.automationexercise.com/login");

        RegisterPage register = new RegisterPage(driver);
        String testEmail = "user" + System.currentTimeMillis() + "@test.com";

        register.enterNameAndEmail("DeleteMe", testEmail);
        driver.findElement(By.xpath("//*[@id='form']/div/div/div[3]/div/form/button")).click();

        if (register.userAlreadyExists()) {
            Assert.fail("Email already exists. Test requires a new user.");
        }

        try {
            register.fillRegisterData(
                    "pass123", "1", "1", "2000",
                    "First", "Last", "Test Address",
                    "Israel", "Tel Aviv", "Tel Aviv",
                    "12345", "0501234567"
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify account created
        WebElement accountCreated = driver.findElement(By.xpath("//b[text()='Account Created!']"));
        Assert.assertTrue(accountCreated.isDisplayed(), "Account was not created");

        Allure.step("Account created. Proceeding to delete.");

        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();

        // Delete account
        driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();

        WebElement deletedMsg = driver.findElement(By.xpath("//b[text()='Account Deleted!']"));
        Assert.assertTrue(deletedMsg.isDisplayed(), "Account was not deleted!");

        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();

        // Try logging in with deleted user
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
        driver.findElement(By.xpath("//*[@data-qa='login-email']")).sendKeys(testEmail);
        driver.findElement(By.xpath("//*[@data-qa='login-password']")).sendKeys("pass123");
        driver.findElement(By.xpath("//*[@data-qa='login-button']")).click();

        // Verify error is displayed
        WebElement error = driver.findElement(By.xpath("//p[contains(text(),'incorrect') or contains(text(),'Invalid') or contains(text(),'not found') or contains(text(),'deleted') ]"));
        Assert.assertTrue(error.isDisplayed(), "Login succeeded with deleted account!");
    }
}
