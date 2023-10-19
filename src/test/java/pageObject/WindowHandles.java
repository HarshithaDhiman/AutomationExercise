package pageObject;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WindowHandles {
	public WebDriver driver;
	public WindowHandles(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void handledWindowSwitch() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> itr = handles.iterator();
		String ParentWindow = itr.next();
		String ChildWindow = itr.next();
		driver.switchTo().window(ParentWindow);
	}

}
