package pageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpAndLoginPage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public SignUpAndLoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		this.driver = driver;
		this.wait = wait;
		this.js = js;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/child::li/a")private List<WebElement>  WebPageHeading;
	@FindBy(xpath = "//a[normalize-space()='Signup / Login']") private WebElement SignUpLogin;
	@FindBy(xpath = "//h2[normalize-space()='New User Signup!']") private WebElement NewUserSignup;
	@FindBy(xpath = "//input[@placeholder='Name']") private WebElement NewUserName;
	@FindBy(xpath = "//input[@data-qa='signup-email']") private WebElement NewUserEmailAddress;
	@FindBy(xpath = "//button[@data-qa='signup-button']") private WebElement SignUp_Btn;
	@FindBy(xpath = "//b[normalize-space()='Enter Account Information']") private WebElement EnterAccountInformation;
	@FindBy(xpath = "//form[@action='/signup']/child::div") private WebElement AccountInformationDetails;
	@FindBy(xpath = "//input[@id='id_gender2']") private static WebElement Title_RadioBtn;
	public static String TitleValue;
	@FindBy(xpath = "//input[@id='name']") private WebElement Name;
	@FindBy(xpath = "//input[@id='email']") private WebElement Email;
	@FindBy(xpath = "//input[@id='password']") private WebElement password;
	@FindBy(xpath = "//select[@id='days']") private WebElement Day;
	@FindBy(xpath = "//select[@id='months']") private WebElement Month;
	@FindBy(xpath = "//select[@id='years']") private WebElement Year;
	@FindBy(xpath = "//div[@class='checkbox']/descendant::input[@id='newsletter']") private WebElement NewsLetter_CheckBox;
	@FindBy(xpath = "//input[@id='optin']") private WebElement SpecialOffers_CheckBox;
	@FindBy(xpath = "//input[@id='first_name']") private WebElement FirstName;
	@FindBy(xpath = "//input[@id='last_name']") private WebElement LastName;
	@FindBy(xpath = "//input[@id='company']") private WebElement Company;
	@FindBy(xpath = "//input[@id='address1']") private WebElement Address1;
	@FindBy(xpath = "//input[@id='address2']") private WebElement Address2;
	@FindBy(xpath = "//select[@id='country']") private WebElement Country;
	@FindBy(xpath = "//input[@id='state']") private WebElement State;
	@FindBy(xpath = "//input[@id='city']") private WebElement City;
	@FindBy(xpath = "//input[@id='zipcode']") private WebElement ZipCode;
	@FindBy(xpath = "//input[@id='mobile_number']") private WebElement MobileNumber;
	@FindBy(xpath = "//section[@id='form']/descendant::button[1]") private WebElement CreateAccount_Btn;
	@FindBy(xpath = "//b[normalize-space()='Account Created!']") private WebElement AccountCreated;
	@FindBy(xpath = "//a[@class='btn btn-primary']") private WebElement Continue_Btn;
	@FindBy(id =  "ad_iframe") private WebElement AdFrame;
	@FindBy(id = "aswift_1") private WebElement SingleFrame;
	@FindBy(id = "aswift_3") private WebElement DoubleFrame;
	@FindBy(css = "#dismiss-button") private WebElement PopUp1Close_Btn;
	@FindBy(xpath = "//li[10]//a[1]/b") private WebElement LoggedInAs;
	@FindBy(xpath = "//a[normalize-space()='Delete Account']") private WebElement DeleteAccount;
	@FindBy(xpath = "//b[normalize-space()='Account Deleted!']") private WebElement AccountDeleted;

	@FindBy(xpath = "//form[@action='/login']/child::input") private List<WebElement> LoginElements;
	@FindBy(xpath = "//h2[normalize-space()='Login to your account']") private WebElement LoginToYourAccount;
	@FindBy(xpath = "//input[@data-qa='login-email']") private WebElement LoginEmailAddress;
	@FindBy(xpath = "//input[@placeholder='Password']") private WebElement LoginPassword;
	@FindBy(xpath = "//button[normalize-space()='Login']") private WebElement Login_Btn;
	@FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']") private WebElement ErrorMsg;
	@FindBy(xpath = "//a[normalize-space()='Logout']") private WebElement Logout_Btn;
	@FindBy(xpath = "//p[normalize-space()='Email Address already exist!']") private WebElement SignupError;

	public void launchBrowser() throws IOException {
		driver.get("http://automationexercise.com");
		wait.until(ExpectedConditions.visibilityOfAllElements(WebPageHeading));
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		for(int i = 0;i < WebPageHeading.size();i++) {
			assertNotEquals(WebPageHeading.get(i).getText(), sheet.getRow(1).getCell(i));
		}
		ColorLog.d("Home Page is Visible successfully!");
	}

	public void clickOnSignUp() throws IOException {
		wait.until(ExpectedConditions.visibilityOfAllElements(WebPageHeading));
		SignUpLogin.click();
		wait.until(ExpectedConditions.visibilityOf(NewUserSignup));
	}

	public void registerUser() throws IOException, InterruptedException {
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		assertEquals(NewUserSignup.getText(),sheet.getRow(3).getCell(0).toString());
		ColorLog.d("'New User Signup!' is visible");
		wait.until(ExpectedConditions.visibilityOf(SignUp_Btn));
		NewUserName.sendKeys(sheet.getRow(3).getCell(1).toString());
		NewUserEmailAddress.sendKeys(sheet.getRow(3).getCell(2).toString());
		SignUp_Btn.click();
		wait.until(ExpectedConditions.visibilityOf(EnterAccountInformation));
		assertEquals(EnterAccountInformation.getText(), sheet.getRow(5).getCell(0).toString());
		ColorLog.d("'ENTER ACCOUNT INFORMATION' is visible!");
		wait.until(ExpectedConditions.visibilityOfAllElements(AccountInformationDetails));
		Title_RadioBtn.click();
		assertEquals(Name.getAttribute("value"),sheet.getRow(3).getCell(1).toString());
		assertEquals(Email.getAttribute("value"), sheet.getRow(3).getCell(2).toString());
		password.sendKeys(sheet.getRow(5).getCell(0).toString());
		Select day = new Select(Day);
		day.selectByValue(sheet.getRow(5).getCell(2).getRawValue());
		Select mon = new Select(Month);
		mon.selectByValue(sheet.getRow(5).getCell(3).getRawValue());
		Select year = new Select(Year);
		year.selectByVisibleText(sheet.getRow(5).getCell(4).getRawValue());
		js.executeScript("arguments[0].click()", NewsLetter_CheckBox);
		js.executeScript("arguments[0].click()", SpecialOffers_CheckBox);
		FirstName.sendKeys(sheet.getRow(7).getCell(0).toString());
		LastName.sendKeys(sheet.getRow(7).getCell(1).toString());
		Company.sendKeys(sheet.getRow(7).getCell(2).toString());
		Address1.sendKeys(sheet.getRow(7).getCell(3).toString());
		Address2.sendKeys(sheet.getRow(7).getCell(4).toString());
		Select s = new Select(Country);
		s.selectByVisibleText(sheet.getRow(7).getCell(5).toString());
		State.sendKeys(sheet.getRow(7).getCell(6).toString());
		City.sendKeys(sheet.getRow(7).getCell(7).toString());
		ZipCode.sendKeys(sheet.getRow(7).getCell(8).toString());
		MobileNumber.sendKeys(sheet.getRow(7).getCell(9).getRawValue());
		js.executeScript("arguments[0].click();", CreateAccount_Btn);
		Thread.sleep(4000);
		assertEquals(AccountCreated.getText(), sheet.getRow(7).getCell(10).toString());
		ColorLog.d("'ACCOUNT CREATED!' is visible");
		js.executeScript("arguments[0].click()", Continue_Btn);
		wait.until(ExpectedConditions.visibilityOf(LoggedInAs));
		assertEquals(LoggedInAs.getText(), sheet.getRow(3).getCell(1).toString());
		System.out.println("Logged in as "+LoggedInAs.getText()+" is visible!");
	}
	
	public void deleteAccount() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		wait.until(ExpectedConditions.visibilityOf(DeleteAccount));
		DeleteAccount.click();
		wait.until(ExpectedConditions.visibilityOf(AccountDeleted));
		assertEquals(AccountDeleted.getText(), sheet.getRow(7).getCell(11).toString());
		ColorLog.d("'ACCOUNT DELETED!' is visible!");
		js.executeScript("arguments[0].click()", Continue_Btn);
	}

	public void createAccount() throws IOException, InterruptedException {
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		NewUserName.sendKeys(sheet.getRow(9).getCell(0).toString());
		NewUserEmailAddress.sendKeys(sheet.getRow(9).getCell(1).toString());
		SignUp_Btn.click();
		ColorLog.d("'ENTER ACCOUNT INFORMATION' is visible!");
		wait.until(ExpectedConditions.visibilityOfAllElements(AccountInformationDetails));
		Title_RadioBtn.click();
		TitleValue = Title_RadioBtn.getAttribute("value");
		password.sendKeys(sheet.getRow(9).getCell(2).toString());
		Select day = new Select(Day);
		day.selectByValue(sheet.getRow(9).getCell(3).getRawValue());
		Select mon = new Select(Month);
		mon.selectByValue(sheet.getRow(9).getCell(4).getRawValue());
		Select year = new Select(Year);
		year.selectByVisibleText(sheet.getRow(9).getCell(5).getRawValue());
		js.executeScript("arguments[0].click()", NewsLetter_CheckBox);
		js.executeScript("arguments[0].click()", SpecialOffers_CheckBox);
		FirstName.sendKeys(sheet.getRow(9).getCell(6).toString());
		LastName.sendKeys(sheet.getRow(9).getCell(7).toString());
		Company.sendKeys(sheet.getRow(9).getCell(8).toString());
		Address1.sendKeys(sheet.getRow(9).getCell(9).toString());
		Address2.sendKeys(sheet.getRow(9).getCell(10).toString());
		Select s = new Select(Country);
		s.selectByVisibleText(sheet.getRow(9).getCell(11).toString());
		State.sendKeys(sheet.getRow(9).getCell(12).toString());
		City.sendKeys(sheet.getRow(9).getCell(13).toString());
		ZipCode.sendKeys(sheet.getRow(9).getCell(14).toString());
		MobileNumber.sendKeys(sheet.getRow(9).getCell(15).getRawValue());
		js.executeScript("arguments[0].click();", CreateAccount_Btn);
		Thread.sleep(4000);
		ColorLog.d("'ACCOUNT CREATED!' is visible");
		js.executeScript("arguments[0].click()", Continue_Btn);
		wait.until(ExpectedConditions.visibilityOf(LoggedInAs));
		assertEquals(LoggedInAs.getText(), sheet.getRow(9).getCell(0).toString());
		ColorLog.d("'ACCOUNT CREATED!' is visible");
	}

	public void validLoginCredentials() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(LoginToYourAccount));
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		assertEquals(LoginToYourAccount.getText(), sheet.getRow(11).getCell(0).toString());
		ColorLog.d(" 'Login to your account' is visible!");
		LoginEmailAddress.sendKeys(sheet.getRow(11).getCell(1).toString());
		LoginPassword.sendKeys(sheet.getRow(11).getCell(2).toString());
		Login_Btn.click();
		assertEquals(LoggedInAs.getText(), sheet.getRow(9).getCell(0).toString());
		System.out.println("Logged in as "+LoggedInAs.getText()+" is visible!");
	}

	public void logout() {
		wait.until(ExpectedConditions.visibilityOf(Logout_Btn));
		js.executeScript("arguments[0].click()", Logout_Btn);
		ColorLog.d("Logged Out!");
	}

	public void invalidLoginCredentials() throws IOException {
		wait.until(ExpectedConditions.visibilityOf(LoginToYourAccount));
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		assertEquals(LoginToYourAccount.getText(), sheet.getRow(11).getCell(0).toString());
		ColorLog.d("'Login to your account' is visible");
		LoginEmailAddress.sendKeys(sheet.getRow(3).getCell(2).toString());
		LoginPassword.sendKeys(sheet.getRow(5).getCell(0).toString());
		Login_Btn.click();
		assertEquals(ErrorMsg.getText(), sheet.getRow(13).getCell(2).toString());
		ColorLog.d("'Your email or password is incorrect!' is visible!");
	}

	public void registerUserWithExistingEmailId() throws IOException {
		XSSFSheet sheet = TestDataFromExcel.ExcelData();
		NewUserName.sendKeys(sheet.getRow(9).getCell(0).toString());
		NewUserEmailAddress.sendKeys(sheet.getRow(11).getCell(1).toString());
		ColorLog.d("'Login to your account' is visible");
		SignUp_Btn.click();		
		assertEquals(SignupError.getText(), sheet.getRow(15).getCell(0).toString());
		ColorLog.d("Email Address already exist!");
	}

}
