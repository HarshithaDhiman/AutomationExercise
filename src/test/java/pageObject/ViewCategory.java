package pageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewCategory {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public ViewCategory(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='left-sidebar']/h2") private WebElement Category;
	@FindBy(xpath = "//a[normalize-space()='Women']") private WebElement CategoryWomen;
	@FindBy(xpath = "//div[@class='features_items']/div") private List<WebElement> WomenDressesTable;
	@FindBy(xpath = "//div[@id='Women']//a[contains(text(),'Dress')]") private WebElement WomenDress;
	@FindBy(id = "aswift_6") private WebElement Frame1;
	@FindBy(id = "aswift_4") private WebElement Frame2;
	@FindBy(xpath = "//iframe[@id='ad_iframe' and @title='Advertisement']") private WebElement AdFrame;
	@FindBy(xpath = "//div[@id='dismiss-button']") private WebElement Close_Btn;
	@FindBy(xpath = "//h2[text()='Women - Dress Products']") private WebElement WomenCategoryPage;
	@FindBy(xpath = "//div[@id='accordian']/child::div[2]/descendant::a[1]") private WebElement CategoryMen;
	@FindBy(xpath = "//div[@id='Men']/descendant::li[1]/child::a[1]") private WebElement MenTshirts;
	@FindBy(xpath = "//h2[@class='title text-center']") private WebElement MenCategoryPage;


	public void categoryVerification() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		js.executeScript("arguments[0].scrollIntoView(true);", Category);
		wait.until(ExpectedConditions.visibilityOf(Category));
		assertEquals(Category.getText(), sheet.getRow(4).getCell(0).toString());
	}

	public void clickOnWomenCategory() throws InterruptedException, IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		wait.until(ExpectedConditions.visibilityOfAllElements(CategoryWomen));
		js.executeScript("arguments[0].click()", CategoryWomen);
		assertEquals(CategoryWomen.getText(), sheet.getRow(4).getCell(1).toString());
		ColorLog.d("Clicked on 'WOMEN' in sidebar under 'CATEGORY'");
		wait.until(ExpectedConditions.visibilityOf(WomenDress));
		assertEquals(WomenDress.getText(), sheet.getRow(4).getCell(2).toString());
		WomenDress.click();
		Thread.sleep(2000);/*to handle the ad popup*/
		ColorLog.d("Clicked on 'Dress' in sidebar under 'WOMEN'");
		wait.until(ExpectedConditions.visibilityOf(WomenCategoryPage));
		assertEquals(WomenCategoryPage.getText(), sheet.getRow(4).getCell(3).toString());
		ColorLog.d("Landed on to 'WOMEN - DRESS PRODUCTS' page");
	}

	public void clickOnMenCategory() throws InterruptedException, IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		wait.until(ExpectedConditions.visibilityOf(CategoryMen));
		js.executeScript("arguments[0].click()", CategoryMen);
		assertEquals(CategoryMen.getText(), sheet.getRow(4).getCell(4).toString());
		ColorLog.d("Clicked on 'Men' in sidebar under Category");
		wait.until(ExpectedConditions.visibilityOf(MenTshirts));
		assertEquals(MenTshirts.getText(), sheet.getRow(4).getCell(5).toString());
		MenTshirts.click();
		Thread.sleep(2000);
		ColorLog.d("Clicked on 'TSHIRTS' in sidebar under 'MEN'");
		/*to handle the ad popup*/
		wait.until(ExpectedConditions.visibilityOf(MenCategoryPage));
		assertEquals(MenCategoryPage.getText(), sheet.getRow(4).getCell(6).toString());
		ColorLog.d("Landed on to 'MEN - TSHIRTS PRODUCTS' page");
	}
}
