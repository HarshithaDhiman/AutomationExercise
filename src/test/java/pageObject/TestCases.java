package pageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
	public WebDriver driver;
	WebDriverWait wait;

	public TestCases(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	@FindBy(xpath = "//a[contains(text(),'Test Cases')]") private WebElement TestCases;
	@FindBy(id = "aswift_9") private WebElement AdFrame;
	@FindBy(id = "aswift_8") private WebElement StartFrame;
	@FindBy(id = "dismiss-button") private WebElement Close_Btn;
	@FindBy(xpath = "//b[normalize-space()='Test Cases']") private WebElement TestCasesPage;

	public void clickOnTestCases() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelProductTestData();
		TestCases.click();
		if(AdFrame.isDisplayed()) {
			System.out.println("In AdFrame");
			driver.switchTo().frame(AdFrame);
			Close_Btn.click();
			driver.switchTo().defaultContent();
		}
		else if (StartFrame.isDisplayed()) {
			System.out.println("In StartFrame");
			driver.switchTo().frame(StartFrame);
			Close_Btn.click();
			driver.switchTo().defaultContent();
		}
		wait.until(ExpectedConditions.visibilityOf(TestCasesPage));
		assertEquals(TestCasesPage.getText(), sheet.getRow(3).getCell(0).toString());
		ColorLog.d("Navigated to 'TEST CASES' page successfully");
	}
}
