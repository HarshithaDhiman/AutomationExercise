package pageObject;

import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddSearchProductsToCart {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;

	public AddSearchProductsToCart(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		act = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	ArrayList<String> ProductText = new ArrayList<>();
	@FindBy(xpath = "//input[@id='search_product']") private WebElement ProductSearchBox;
	@FindBy(xpath = "//button[@id='submit_search']") private WebElement ProductSearch_Btn;
	@FindBy(xpath = "//div[@id='cartModal']/following::div[@class='col-sm-4']") private List<WebElement> ListOfSearchedProduct;
	@FindBy(xpath = "(//div[@id='cartModal']/following::div[@class='col-sm-4'])[1]") private WebElement FirstProduct;
	@FindBy(xpath = "(//div[@id='cartModal']/following::div[@class='col-sm-4'])[2]") private WebElement SecondProduct;
	@FindBy(xpath = "(//div[@id='cartModal']/following::div[@class='col-sm-4'])[3]") private WebElement ThirdProduct;
	@FindBy(xpath = "//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[1]//div[2]//div[1]//a[1]") private WebElement FirstProductAddToCart;
	@FindBy(xpath = "//div[3]//div[1]//div[1]//div[2]//div[1]//a[1]") private WebElement SecondProductAddToCart;
	@FindBy(xpath = "(//a[@data-product-id='37'])[1]") private WebElement ThirdProductAddToCart;
	@FindBy(xpath = "//button[text()='Continue Shopping']") private WebElement ContinueShopping_Btn;
	@FindBy(xpath = "//a[normalize-space()='Cart']")private WebElement Cart;
	@FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr") private List<WebElement> NoOfRows;
	@FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr[1]/td") private List<WebElement> NoOfColumns;

	public void searchProducts() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		ProductSearchBox.sendKeys(sheet.getRow(14).getCell(0).toString());
		ProductSearch_Btn.click();
		ColorLog.d("All products related to search are visible");
	}

	public void addSearchProducts() {
		js.executeScript("arguments[0].scrollIntoView(true);", FirstProduct);
		act.moveToElement(FirstProduct).perform();
		wait.until(ExpectedConditions.visibilityOf(FirstProductAddToCart));
		js.executeScript("arguments[0].click()", FirstProductAddToCart);
		wait.until(ExpectedConditions.visibilityOf(ContinueShopping_Btn));
		js.executeScript("arguments[0].click()", ContinueShopping_Btn);

		act.moveToElement(SecondProduct).perform();
		wait.until(ExpectedConditions.visibilityOf(SecondProductAddToCart));
		js.executeScript("arguments[0].click()", SecondProductAddToCart);
		wait.until(ExpectedConditions.visibilityOf(ContinueShopping_Btn));
		js.executeScript("arguments[0].click()", ContinueShopping_Btn);

		act.moveToElement(ThirdProduct).perform();
		wait.until(ExpectedConditions.visibilityOf(ThirdProductAddToCart));
		js.executeScript("arguments[0].click()", ThirdProductAddToCart);
		wait.until(ExpectedConditions.visibilityOf(ContinueShopping_Btn));
		js.executeScript("arguments[0].click()", ContinueShopping_Btn);

		wait.until(ExpectedConditions.visibilityOf(Cart));
		Cart.click();
	}

	public void verifySeachAndAddProductsInCart() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		for(int i = 1;i <= NoOfRows.size();i++) {
			for(int j = 1; j <= NoOfColumns.size();j++) {
				if((j==1) || (j==6)) {
					continue;
				}
				ProductText.add(driver.findElement(By.xpath("//table[@id='cart_info_table']/tbody/tr["+i+"]/td["+j+"]")).getText());
			}
		}
		assertNotEquals(ProductText,sheet.getRow(16).getCell(0).toString());
		ColorLog.d("Verified that searched products are visible in cart after login as well");
	}
}
