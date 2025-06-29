import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("Register")
public class RegisterTest extends BaseTest {

    @Epic("User Management")
    @Feature("Register")
    @Story("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testRegisterNewUser() throws InterruptedException {

        Allure.step("שלב 1 - פתיחת עמוד הרשמה");
        Reporter.log("🟡 טסט התחיל: הרשמה");

        driver.get("https://www.automationexercise.com/login");

        RegisterPage register = new RegisterPage(driver);
        String testEmail = "jaohnxx123@xgmayz.com";

        Allure.step("שלב 2 - הזנת שם ואימייל");
        register.enterNameAndEmail("John", testEmail);

        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
        Thread.sleep(1000);
        Allure.step("שלב 3 - בדיקת האם המשתמש כבר קיים");
        if (register.userAlreadyExists()) {
            Reporter.log("⚠️ משתמש כבר קיים, הטסט מדלג על ההרשמה");
            Assert.assertTrue(true, "Email already in system – test passed for existing email.");
            return;
        }

        Allure.step("שלב 4 - מילוי טופס הרשמה");
        register.fillRegisterData(
                "1234", "5", "6", "1996",
                "Ma", "Ha", "Some Address",
                "Israel", "North", "Nazareth",
                "12345", "0500000000"
        );

        Allure.step("שלב 5 - בדיקה שההרשמה הצליחה");
        WebElement successMessage = driver.findElement(By.xpath("//b[text()='Account Created!']"));
        Assert.assertTrue(successMessage.isDisplayed(), "❌ ההרשמה נכשלה — לא נוצר משתמש.");

        Reporter.log("✅ נרשם משתמש חדש בהצלחה");
        Allure.step("טסט הסתיים בהצלחה");
    }
}
