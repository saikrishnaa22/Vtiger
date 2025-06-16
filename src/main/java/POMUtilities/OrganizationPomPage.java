package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPomPage {
	// declare
	 @FindBy(linkText = "Organizations")
    private  WebElement header;
    
    @FindBy(xpath = "//img[@title='Create Organization...']")
    private  WebElement orgplusicon;

    //initialize
    WebDriver driver;
	public OrganizationPomPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getorgplusicon() {
		orgplusicon.click();
	}
    
    
    
    
    

}
