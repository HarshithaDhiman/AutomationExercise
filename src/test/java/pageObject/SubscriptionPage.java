package pageObject;

import static org.testng.Assert.*;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionPage {
	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;

	public SubscriptionPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(xpath = "//h2[normalize-space()='Subscription']") private WebElement Subscription;
	@FindBy(xpath = "//input[@id='susbscribe_email']") private WebElement SubscriptionEmail;
	@FindBy(xpath = "//button[@id='subscribe']") private WebElement Subscription_Btn;
	@FindBy(xpath = "//div[@class='alert-success alert']") private WebElement SuccessAlertMsg;

	@FindBy(xpath = "//a[normalize-space()='Cart']") private WebElement Cart;

	@FindBy(xpath = "//footer[@id='footer']") private WebElement footer;
	@FindBy(xpath = "//a[@id='scrollUp']") private WebElement UpArrowButton;
	@FindBy(xpath = "(//h2[text()='Full-Fledged practice website for Automation Engineers'])[1]") private WebElement FullFledge;
	@FindBy(xpath = "//header[@id='header']") private WebElement Header;

	public void subscription() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelProductTestData();
		int deltaY = footer.getRect().y;
		Action scrollDown = act.scrollByAmount(0, deltaY).build();
		scrollDown.perform();
		assertEquals(Subscription.getText(), sheet.getRow(10).getCell(0).toString());
		ColorLog.d("Subscription is visible");
		SubscriptionEmail.sendKeys(sheet.getRow(10).getCell(1).toString());
		Subscription_Btn.click();
		assertEquals(SuccessAlertMsg.getText(), sheet.getRow(10).getCell(2).toString());
		ColorLog.d("'You have been successfully subscribed!' is visible");
	}
	
	public void clickOnCart() {
		wait.until(ExpectedConditions.visibilityOf(Cart));
		Cart.click();
		ColorLog.d("Cart Page is displayed");
	}
	
	public void scrollUoDownWithArrow() throws InterruptedException, IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		int deltaY = footer.getRect().y;
		Action scrollDown = act.scrollByAmount(0, deltaY).build();
		scrollDown.perform();
		assertTrue(Subscription.isDisplayed());
		ColorLog.d("Subscription is visible!");
		Thread.sleep(3000);
		UpArrowButton.click();
		assertEquals(FullFledge.getText(), sheet.getRow(21).getCell(0).toString());
		ColorLog.d("Scrolled Up with the help of arrow!");
	}

	public void scrollUpDown() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		int deltaY = footer.getRect().y;
		Action scrollDown = act.scrollByAmount(0, deltaY).build();
		act.scrollToElement(Subscription).build().perform();
		scrollDown.perform();
		Assert.assertTrue(Subscription.isDisplayed());
		ColorLog.d("Subscription is visible!");
		Action scrollUp = act.scrollByAmount(0, -deltaY).build();
		scrollUp.perform();
		assertEquals(FullFledge.getText(), sheet.getRow(21).getCell(0).toString());
		ColorLog.d("Scrolled Up!");
	}

}
