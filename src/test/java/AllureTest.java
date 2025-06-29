import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Check")
@Feature("Allure")
public class AllureTest {

    @Test(description = "בדיקה אם הכל פועל")
    @Severity(SeverityLevel.CRITICAL)
    @Description("זה טסט לבדיקה בסיסית")
    public void testAllure() {
        Allure.step("מתחיל טסט");
        System.out.println("Hello Allure");
    }
}