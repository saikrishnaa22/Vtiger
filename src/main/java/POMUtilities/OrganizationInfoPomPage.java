package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPomPage {
	
	//Declare
	@FindBy(xpath = "//span[contains(text(),' Organization Information')]")
    private WebElement header;
	
	@FindBy(id = "dtlview_Organization Name")
    private WebElement verifyOrgname;
	
	@FindBy(id ="dtlview_Phone")
    private WebElement verifyPhone;
	
	@FindBy(id ="dtlview_Industry")
	private WebElement verifyIndustyDD;
	
	
	@FindBy(id ="dtlview_Type")
	private WebElement verifyTypeDD;


	//initialize
	WebDriver driver;
	public OrganizationInfoPomPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}


	public String getverifyOrgname() {
		return verifyOrgname.getText();
	}


	public String getverifyPhone() {
		return verifyPhone.getText();
	}


	public String getverifyIndustyDD() {
		return verifyIndustyDD.getText();
	}


	public String getverifyTypeDD() {
		return verifyTypeDD.getText();
	}
	
	
	

	
	
	
	
	
	

}
