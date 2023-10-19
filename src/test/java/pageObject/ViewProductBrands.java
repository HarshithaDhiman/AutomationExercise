package pageObject;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewProductBrands {
	WebDriver driver;
	WebDriverWait wait;

	public ViewProductBrands(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	String[] Array = new String[10];
	@FindBy(xpath = "//div[@class='brands_products']//h2") private WebElement Brands;
	@FindBy(xpath = "//div[@class='left-sidebar']//div[@class='brands_products']//li/a") private List<WebElement> BrandsList;

	@FindBy(xpath = "//div[@class='left-sidebar']//div[@class='brands_products']//li[8]/a") private WebElement BIBA;
	@FindBy(xpath = "//h2[text()='Brand - Biba Products']") private WebElement BibaProductPage;
	@FindBy(xpath = "//div[@id='cartModal']/following-sibling::div[@class='col-sm-4']") private List<WebElement> ListOfBibaProducts;
	@FindBy(xpath = "//div[@class='left-sidebar']//div[@class='brands_products']//li[5]/a") private WebElement BabyHug;
	@FindBy(xpath = "//h2[text()='Brand - Babyhug Products']") private WebElement BabyHugProductPage;
	@FindBy(xpath = "//div[@id='cartModal']/following::div[@class='col-sm-4']") private List<WebElement> ListOfBabyHugProducts;

	public void itemsInBrands() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		wait.until(ExpectedConditions.visibilityOf(Brands));
		assertEquals(Brands.getText(), "BRANDS");
		for(int i=0; i<BrandsList.size();i++) {
			Array[i] = BrandsList.get(i).getText();
		}
		assertEquals(Array[0], sheet.getRow(6).getCell(0).toString());
		assertEquals(Array[1], sheet.getRow(6).getCell(1).toString());
		assertEquals(Array[2], sheet.getRow(6).getCell(2).toString());
		assertEquals(Array[3], sheet.getRow(6).getCell(3).toString());
		assertEquals(Array[4], sheet.getRow(6).getCell(4).toString());
		assertEquals(Array[5], sheet.getRow(6).getCell(5).toString());
		assertEquals(Array[6], sheet.getRow(6).getCell(6).toString());
		assertEquals(Array[7], sheet.getRow(6).getCell(7).toString());
		ColorLog.d("List of Brands present are validated");
	}

	public void BibaAndItsProducts() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		wait.until(ExpectedConditions.visibilityOf(BIBA));
		BIBA.click();
		assertEquals(BibaProductPage.getText(), sheet.getRow(8).getCell(0).toString());
		for(int i=0; i<ListOfBibaProducts.size();i++) {
			Array[i] = ListOfBibaProducts.get(i).getText();
		}
		assertEquals(Array[0], sheet.getRow(10).getCell(0).toString());
		assertEquals(Array[1], sheet.getRow(10).getCell(1).toString());
		assertEquals(Array[2], sheet.getRow(10).getCell(2).toString());
		assertEquals(Array[3], sheet.getRow(10).getCell(3).toString());
		assertEquals(Array[4], sheet.getRow(10).getCell(4).toString());
		ColorLog.d("List of products present under 'BIBA' are validated");
	}

	public void BabyHugAndItsProducts() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelPlaceOrderData();
		wait.until(ExpectedConditions.visibilityOf(BabyHug));
		BabyHug.click();
		assertEquals(BabyHugProductPage.getText(), sheet.getRow(8).getCell(1).toString());
		for(int i=0; i<ListOfBabyHugProducts.size();i++) {
			Array[i] = ListOfBabyHugProducts.get(i).getText();
		}
		assertEquals(Array[0], sheet.getRow(12).getCell(0).toString());
		assertEquals(Array[1], sheet.getRow(12).getCell(1).toString());
		assertEquals(Array[2], sheet.getRow(12).getCell(2).toString());
		assertEquals(Array[3], sheet.getRow(12).getCell(3).toString());
		ColorLog.d("List of products present under 'BABYHUG' are validated");
	}
}
