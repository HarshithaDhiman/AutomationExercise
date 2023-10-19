package pageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecommendedItems {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public RecommendedItems(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//h2[text()='recommended items']") private WebElement RecommmendedItemsElement;
	@FindBy(xpath = "(//div[@class='item active']//div[@class='col-sm-4'])[3]//descendant::div[3]/p") private WebElement RecommmendedItemInformation;
	@FindBy(xpath = "(//div[@class='item active']//div[@class='col-sm-4'])[3]//descendant::a") private WebElement RecommmendedItemAddToCart;
	@FindBy(xpath = "//u[text()='View Cart']") private WebElement RecommmendedItemViewCart;

	@FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr/td[2]/h4") private WebElement CartItemName;
	@FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr/td[3]/p") private WebElement CartItemPrice;

	public void recommendedItems() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		js.executeScript("arguments[0].scrollIntoView(true);",RecommmendedItemsElement);
		wait.until(ExpectedConditions.visibilityOf(RecommmendedItemsElement));
		assertEquals(RecommmendedItemsElement.getText(), sheet.getRow(20).getCell(0).toString());
		ColorLog.d("'RECOMMENDED ITEMS' is visible.");
	}

	public void addRecommendedItemsToCart() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		assertEquals(RecommmendedItemInformation.getText(), sheet.getRow(20).getCell(1).toString());
		wait.until(ExpectedConditions.visibilityOf(RecommmendedItemAddToCart));
		RecommmendedItemAddToCart.click();
		wait.until(ExpectedConditions.visibilityOf(RecommmendedItemViewCart));
		RecommmendedItemViewCart.click();
		assertEquals(CartItemName.getText(), sheet.getRow(20).getCell(1).toString());
		ColorLog.d("The product clicked on recommended items is same as the one displayed in cart page");
	}
}
