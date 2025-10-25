import net.datafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AutomateTest {

    @Test
    public void fillForm() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSdUCd3UWQ3VOgeg0ZzNeT-xzNawU8AJ7Xidml-w1vhfBcvBWQ/viewform");
        WebElement fullName = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        WebElement contactNumber = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
        WebElement emailId = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
        WebElement fullAddress = driver.findElement(By.xpath("//textarea[@aria-label='Your answer']"));
        WebElement pinCode = driver.findElement(By.xpath("(//input[@type='text'])[4]"));
        WebElement dob = driver.findElement(By.xpath("//input[@type='date']"));
        WebElement gender = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
        WebElement code = driver.findElement(By.xpath("(//input[@type='text'])[6]"));
        WebElement submit = driver.findElement(By.xpath("//span[contains(text(),'Submit')]"));


        Faker faker = new Faker();

        fullName.sendKeys(faker.name().fullName());
        contactNumber.sendKeys(faker.phoneNumber().cellPhone());
        emailId.sendKeys(faker.internet().emailAddress());
        fullAddress.sendKeys(faker.address().fullAddress());
        pinCode.sendKeys(faker.address().postcode());
        dob.sendKeys("01-11-2000");
        gender.sendKeys(faker.gender().binaryTypes());
        code.sendKeys("GNFPYC");
        submit.click();
        WebElement confirmMsg = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        if(confirmMsg.isDisplayed()) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("screenshot.png"));
        }
        driver.quit();



    }

}
