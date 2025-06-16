package OrganizationModule;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.CreateNewOrganizationPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrganizationInfoPomPage;
import POMUtilities.OrganizationPomPage;

public class OrganizationAllTestSenarios extends BaseClass {
	// @Parameters("browser")

	@Test(groups ="smoke",retryAnalyzer =ListenersUtillity.IRetryAnalyser.class)

	public void Createneworg() throws IOException, InterruptedException {

		// Fetch the random number
		JavaUtility j = new JavaUtility();
		int num = j.generateRandomNumber();

		// Fetch the data from Excel

		ExcelFileUtility ex_util = new ExcelFileUtility();

		String orgname = ex_util.fetchThedatafromExcel("organization", 1, 3) + num;

		WebDriverUtility w = new WebDriverUtility();

		// click on organization tab with home
		HomePomPage h = new HomePomPage(driver);
		h.getOrganizationtab();

		// click on plus symbol
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getorgplusicon();

		// Enter the ORganization name and click on the save Button
		CreateNewOrganizationPomPage c_new = new CreateNewOrganizationPomPage(driver);
		c_new.getOrgname(orgname);
		c_new.getSave_btn();

		// verify the organization name //
		OrganizationInfoPomPage org_info = new OrganizationInfoPomPage(driver);
		String verify_orgname = org_info.getverifyOrgname();
		
		   // Hard Assert checking org name 
	     Assert.assertEquals(verify_orgname, orgname);
		
		// click on organization tab with pom
		h.getOrganizationtab();

		// Delete the Created organization
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);
		// Handle the Alert pop
		w.handleAlertandClickoK(driver);

		// close the workbook
		ex_util.closeExcelWorkbook();

	}

	// @Parameters("browser")

	@Test(retryAnalyzer =ListenersUtillity.IRetryAnalyser.class)
	
	public void createorgphone() throws IOException, InterruptedException {

		// Fetch the random number
		JavaUtility j = new JavaUtility();
		int num = j.generateRandomNumber();

		// fetch the data from excel

		ExcelFileUtility ex_util = new ExcelFileUtility();
		String orgname = ex_util.fetchThedatafromExcel("organization", 4, 3) + num;
		String phonenumber = ex_util.fetchThedatafromExcel("organization", 4, 4) + num;

		WebDriverUtility w = new WebDriverUtility();

		// click on organization tab with pom
		HomePomPage h = new HomePomPage(driver);
		h.getOrganizationtab();

		// click on plus symbol
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getorgplusicon();

		// Enter the ORganization name and click on the save Button
		CreateNewOrganizationPomPage org_new = new CreateNewOrganizationPomPage(driver);
		org_new.getOrgname(orgname);
		org_new.getPhoneno(phonenumber);
		org_new.getSave_btn();

		// verify the organization name //
		OrganizationInfoPomPage org_info = new OrganizationInfoPomPage(driver);

		String verify_orgname = org_info.getverifyOrgname();
		   // Hard Assert checking org name 
	     Assert.assertEquals(verify_orgname, orgname);
		
		// verify the phone number

		String phone = org_info.getverifyPhone();
		
		   // Hard Assert checking org name 
	     Assert.assertEquals(phone, phonenumber);
		
		// click on organization tab with pom

		h.getOrganizationtab();

		// Delete the Created organization
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the Alert pop
		w.handleAlertandClickoK(driver);

		// close the excel
		ex_util.closeExcelWorkbook();

	}

	// @Parameters("browser")

	@Test(retryAnalyzer =ListenersUtillity.IRetryAnalyser.class)

	public void CreateOrganisation_IndustryAndType() throws Exception {

		// Fetch the random number
		JavaUtility j = new JavaUtility();
		int num = j.generateRandomNumber();

		// fetch the data from the excelfile

		ExcelFileUtility ex_util = new ExcelFileUtility();
		String orgname = ex_util.fetchThedatafromExcel("organization", 7, 3) + num;
		String industry = ex_util.fetchThedatafromExcel("organization", 7, 4);
		String type = ex_util.fetchThedatafromExcel("organization", 7, 5);

		WebDriverUtility w = new WebDriverUtility();

		// click on organization tab in home
		HomePomPage h = new HomePomPage(driver);
		h.getOrganizationtab();

		// click on plus symbol
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getorgplusicon();

		// Enter the ORganization name and click on the save Button

		CreateNewOrganizationPomPage c = new CreateNewOrganizationPomPage(driver);
		c.getOrgname(orgname);
		WebElement ind_dd = c.getIndustryDD();
		w.SelectDropDownByValue(ind_dd, industry);
		WebElement type_dd = c.getTypeDD();
		w.SelectDropDownByVisibeText(type_dd, type);
		c.getSave_btn();

		// verify the organization name //
		OrganizationInfoPomPage org_info = new OrganizationInfoPomPage(driver);

		String verify_Orgname= org_info.getverifyOrgname();
		
		   // Hard Assert checking org name 
	     Assert.assertEquals(verify_Orgname, orgname);
		
		// verify the industry

		String actid = org_info.getverifyIndustyDD();
		
		   // Hard Assert checking org name 
	     Assert.assertEquals(actid, industry);
	 
		// verify the type
		String actty = org_info.getverifyTypeDD();
		
		   // Hard Assert checking org name 
	     Assert.assertEquals(actty, type);
	     

		// click on organization tab with pom

		h.getOrganizationtab();

		// Delete the Created organization
		driver.findElement(
				By.xpath("//a[text()='"+orgname+"']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the Alert pop
		w.handleAlertandClickoK(driver);

		// close excel
		ex_util.closeExcelWorkbook();

	}

}
