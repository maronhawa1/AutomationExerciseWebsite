

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("User Payment")
@Feature("Make Payment")
public class PaymentTest extends BaseTest {

    @Test(description = "Full payment test flow with success")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Payment Flow")
    @Description("Test the full flow from login to payment success message")
    public void testFullPaymentFlow() throws InterruptedException {
        pages.LoginPage login = new pages.LoginPage(driver);
        login.login("maro1@gmail.com", "Maron1234$");

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.addProductToCart();
        paymentPage.navigateToCheckout();
     paymentPage.fillPaymentDetails("Test name", "4580458045804580", "123", "04", "2028");
        paymentPage.submitPayment();

        Assert.assertEquals(driver.getCurrentUrl(),
             "https://www.automationexercise.com/payment_done/500");
    }
}