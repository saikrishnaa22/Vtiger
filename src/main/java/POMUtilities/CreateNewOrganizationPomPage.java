package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrganizationPomPage {

	//Declare 
	@FindBy(xpath = "//span[(text()= 'Creating New Organization')]")
	private WebElement header;
	
	@FindBy(name = "accountname")
	private WebElement orgnametf;
	
	@FindBy(id="phone")
	private WebElement phonenotf;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save_btn;

	// initialize
	
	WebDriver driver;
	public CreateNewOrganizationPomPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	
	// Utilize
	
	public String getHeader() {
		return header.getText();
	}

	public void getOrgname(String orgname) {
		orgnametf.sendKeys(orgname);
	}

	public void getPhoneno(String phone) {
		 phonenotf.sendKeys(phone);
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}

	public void getSave_btn() {
		 save_btn.click();
	}
	
	

	
	
	
}
