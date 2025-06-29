import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterNameAndEmail(String name, String email) {
        WebElement nameInput = driver.findElement(By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[2]"));
        WebElement emailInput = driver.findElement(By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[3]"));
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);

    }
    public boolean userAlreadyExists() {
        try {
            WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/p"));
            return errorMsg.getText().equals("Email Address already exist!");
        } catch (Exception e) {
            return false; // אם אין שגיאה, כנראה שהמייל לא קיים
        }
    }

    public void fillRegisterData(
            String password, String day, String month, String year,
            String firstName, String lastName, String address,
            String country, String state, String city,
            String zipCode, String phoneNumber) throws InterruptedException {

        // Password and birth date
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        new Select(driver.findElement(By.name("days"))).selectByValue(day);
        new Select(driver.findElement(By.name("months"))).selectByValue(month);
        new Select(driver.findElement(By.name("years"))).selectByValue(year);

        // Personal details
        driver.findElement(By.id("first_name")).sendKeys(firstName);
        driver.findElement(By.id("last_name")).sendKeys(lastName);
        driver.findElement(By.id("address1")).sendKeys(address);
        new Select(driver.findElement(By.name("country"))).selectByVisibleText(country); // עדיף visibleText למניעת שגיאות

        // Contact info
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zipcode")).sendKeys(zipCode);
        driver.findElement(By.id("mobile_number")).sendKeys(phoneNumber);
        WebElement createButton = driver.findElement(By.xpath("//*[@id='form']/div/div/div/div[1]/form/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createButton);
        Thread.sleep(500); // תן זמן לגלילה
        createButton.click();

        // Submit
     }
}
