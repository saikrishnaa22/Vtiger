package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mysql.cj.jdbc.Driver;

public class LoginPomPage {
	
	//Declaration
	
	@FindBy(linkText = "vtiger")
	private WebElement header;

	@FindBy(name="user_name")
	private WebElement usernametf;
	
	@FindBy(name="user_password")
	private WebElement passwordtf;
	
	@FindBy(id ="submitButton")
	private WebElement login;

	//Initialization
	
	WebDriver driver;
	public LoginPomPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Utilization
	
	public String getheader() {
	return header.getText();
	}
	public void getUsername(String user) {
		 usernametf.sendKeys(user);
	}

	public void getPassword(String pass) {
		passwordtf.sendKeys(pass);
	}

	public void getLogin() {
		 login.click();
	}
	
     public void login(String url,String username, String password )
     {
    	driver.get(url);
    	 usernametf.sendKeys(username);
    	 passwordtf.sendKeys(password);
    	 login.click();
     }
	
	
	

}
