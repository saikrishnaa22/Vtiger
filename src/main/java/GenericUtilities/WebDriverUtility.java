package GenericUtilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v129.layertree.model.StickyPositionConstraint;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void Maxmizethewindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waittheelementFound(WebDriver driver, String Timeouts)

	{
		long time = Long.parseLong(Timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void NavigateToURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public String parentwindow(WebDriver driver) {
		String pwind = driver.getWindowHandle();
		return pwind;
	}

	public void switchtoChildWindwBaseonURL(WebDriver driver, String partial_url) {
		Set<String> wids = driver.getWindowHandles();
		for (String id : wids) {
			driver.switchTo().window(id);
			if (driver.getCurrentUrl().contains(partial_url)) {
				break;
			}
		}
	}

	public void switchtoChildWindwBaseonTitle(WebDriver driver, String partial_title) {
		Set<String> wids = driver.getWindowHandles();
		for (String id : wids) {
			driver.switchTo().window(id);
			if (driver.getCurrentUrl().contains(partial_title)) {
				break;
			}
		}
	}

	public void handleAlertandPassText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void handleAlertandClickoK(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void handleAlertandClickCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String handleAlertandFetchTheText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void Mouseovering(WebDriver driver, WebElement elememt) {
		Actions a = new Actions(driver);
		a.moveToElement(elememt).perform();
	}
	
	

	public void SelectDropDownByValue(WebElement dropdowElement, String value) {

		Select s = new Select(dropdowElement);
		s.selectByValue(value);
	}

	public void SelectDropDownByIndex(WebElement dropdowElement, String index) {

		Select s = new Select(dropdowElement);
		s.selectByValue(index);
	}

	public void SelectDropDownByVisibeText(WebElement dropdowElement, String text) {

		Select s = new Select(dropdowElement);
		s.selectByValue(text);
	}

	public void ClosetheBrowser(WebDriver driver) {
		driver.close();
	}

	public void QuittheBrowser(WebDriver driver) {
		driver.quit();
	}

	public void dragAndDropOnElement(WebDriver driver, WebElement dragable_elElement, WebElement dropable_Element) {
		Actions act = new Actions(driver);
		act.dragAndDrop(dragable_elElement, dropable_Element);
	}

	public void switchToFrameOnIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrameOnElement(WebDriver driver, WebElement frameele) {
		driver.switchTo().frame(frameele);
	}

	public void switchToMainWebpageFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void waitTilElementIsVisible(WebDriver driver, WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitTilElementIsClickable(WebDriver driver, WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void WaitTillTitleIsDisplayed(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains(title));
	}

}
