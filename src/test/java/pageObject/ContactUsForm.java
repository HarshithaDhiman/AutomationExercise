package pageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsForm {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public ContactUsForm(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Contact us']") private WebElement ContactUs;
	@FindBy(xpath = "//h2[normalize-space()='Get In Touch']") private WebElement GetInTouch;
	@FindBy(xpath = "//input[@name='name']") private WebElement Name;
	@FindBy(xpath = "//input[@name='email']") private WebElement Email;
	@FindBy(xpath = "//input[@name='subject']") private WebElement Subject;
	@FindBy(xpath = "//textarea[@id='message']") private WebElement Message;
	@FindBy(xpath = "//input[@name='upload_file']") private WebElement ChooseFile_Btn;
	@FindBy(xpath = "//input[@name='submit']") private WebElement Submit_Btn;
	@FindBy(id = "aswift_4") private WebElement StartFrame;
	@FindBy(id = "dismiss-button") private WebElement Close_Btn;
	/*there pops an alert accept it*/
	@FindBy(xpath = "//div[@class='status alert alert-success']") private WebElement SuccessMsg;
	@FindBy(xpath = "//a[@class='btn btn-success']") private WebElement Home_btn;
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/child::li/a")private List<WebElement>  WebPageHeading;

	public void clickOnContactUs() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelProductTestData();
		ContactUs.click();
		assertEquals(GetInTouch.getText(), sheet.getRow(5).getCell(0).toString());
		ColorLog.d("'GET IN TOUCH' is visible");
		Name.sendKeys(sheet.getRow(7).getCell(0).toString());
		Email.sendKeys(sheet.getRow(7).getCell(1).toString());
		Subject.sendKeys(sheet.getRow(7).getCell(2).toString());
		Message.sendKeys(sheet.getRow(7).getCell(3).toString());
		ChooseFile_Btn.sendKeys("C:\\Users\\harsh\\OneDrive\\Pictures\\Screenshots\\Screenshot (456).png");
		js.executeScript("arguments[0].scrollIntoView(true);", Submit_Btn);
		Submit_Btn.click();
		Alert a = driver.switchTo().alert();
		System.out.println(a.getText());
		a.accept();
		assertEquals(SuccessMsg.getText(), sheet.getRow(7).getCell(4).toString());
		ColorLog.d("'Success! Your details have been submitted successfully.' is visible");
		js.executeScript("arguments[0].scrollIntoView(true);", Home_btn);
		Home_btn.click();
		/*if(StartFrame.isDisplayed()) {
			driver.switchTo().frame(StartFrame);
			Close_Btn.click();
			driver.switchTo().defaultContent();
		}
		for(int i = 0;i < WebPageHeading.size();i++) {
			//System.out.println(WebPageHeading.get(i).getText());
			assertEquals(WebPageHeading.get(i).getText(), sheet.getRow(8).getCell(i).toString());
		}*/
		ColorLog.d("Landed on to Home Page");
	}
}
