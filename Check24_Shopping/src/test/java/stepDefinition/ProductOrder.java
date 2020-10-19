package stepDefinition;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;


public class ProductOrder extends BaseUtilities {
	
	WebDriver driver=getDriver();
	JavascriptExecutor jse=getJseDriver();
	
	@Given("^User is on the Shopping Homepage$")
	public void User_is_on_the_Shopping_Homepage() throws Throwable {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://shopping.check24.de");
		WebElement ele=driver.findElement(By.xpath("//a[text()='Akzeptieren']"));
		if(ele.isDisplayed())
		{
			ele.click();
		}   
	}

	@When("^User Clicks on zur Kasse gehen link$")
	public void User_Clicks_on_zur_Kasse_gehen_link() throws Throwable {
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[contains(text(),'zur Kasse gehen')]")));
	   
	}

	@Then("^User should be navigated to checkout page$")
	public void User_should_be_navigated_to_checkout_page() throws Throwable {
		String actualTitle=driver.getTitle();
		String expectedTitle = "Bezahlung – Shopping │ CHECK24";
		assertEquals("Checkout page is not displayed", expectedTitle, actualTitle);
	    
	}

	@When("^User Enters \"([^\"]*)\" and \"([^\"]*)\" without selecting the radio button$")
	public void User_Enters_and_without_selecting_the_radio_button(String userName, String password) throws Throwable {
		Thread.sleep(2000);
		WebElement username_txt=driver.findElement(By.xpath("//input[@type='email']"));
		username_txt.clear();
		username_txt.sendKeys(userName);
	
		WebElement weiter_btn=driver.findElement(By.xpath("//button[@value='weiter']"));	
	    jse.executeScript("arguments[0].click();", weiter_btn);
		
		Thread.sleep(2000);
		WebElement password_txt=driver.findElement(By.xpath("//input[@type='password']"));
		password_txt.clear();
		password_txt.sendKeys(password);
	
		WebElement login_btn=driver.findElement(By.xpath("//span[text()='anmelden']"));
		jse.executeScript("arguments[0].click();", login_btn);

		Thread.sleep(1000);
		WebElement weiter_btn1=driver.findElement(By.xpath("//button[@id='c24-uli-points-btn']"));
	    jse.executeScript("arguments[0].click();", weiter_btn1);
			
}
	
	
	@Then("^User should be able to see error message$")
	public void User_should_be_able_to_see_error_message() throws Throwable {
		WebElement act_msg=driver.findElement(By.xpath("//div[@class='clearfix style-scope unified-login']/div[3]"));
		assertTrue("Error message is not displayed",act_msg.isDisplayed());
		
	}

	@When("^User selects the radio button$")
	public void User_selects_the_radio_button() throws Throwable {
		WebElement yes_btn=driver.findElement(By.xpath("(//span[@class='style-scope unified-login']/strong)[1]"));
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", yes_btn);
		
		WebElement weiter_btn=driver.findElement(By.xpath("//button[@id='c24-uli-points-btn']"));
	    jse.executeScript("arguments[0].click();", weiter_btn);
	}

	@Then("^User see the same \"([^\"]*)\",\"([^\"]*)\" details on bestellung page$")
	public void User_see_the_same_details_on_bestellung_page(String exp_zwischensumme, String exp_gesamtsumme) throws Throwable {
		String act_zwischensumme=driver.findElement(By.xpath("//div[@class='summary__subtotal']/div[2]")).getText().trim();
		assertEquals("Actual and Expected zwischensumme are not matching", exp_zwischensumme, act_zwischensumme);
	 
		String act_gesamtsumme=driver.findElement(By.xpath("(//div[@class='summary__total']/div[2])[2]")).getText().trim(); 
		assertEquals("Actual and Expected gesamtssumme are not matching", exp_gesamtsumme, act_gesamtsumme);
		
	}

	@When("^User clicks on  the Firma checkbox$")
	public void User_clicks_on_the_Firma_checkbox() throws Throwable {
		driver.findElement(By.xpath("//div[@class='company-address-container']/label[contains(.,'Firma')]")).click();
	  
	}

	@Then("^User should be able to see the appearance of the textbox Firma$")
	public void User_should_be_able_to_see_the_appearance_of_the_textbox_Firma() throws Throwable {
		WebElement firma_txt=driver.findElement(By.xpath("//div[@class='checkout-floatLabel floatLabel-empty']/label[contains(.,'Firma')]"));
		assertTrue("Firma textbox is not displayed",firma_txt.isDisplayed());
	  
	}

	@When("^User unchecks the Firma checkbox$")
	public void User_unchecks_the_Firma_checkbox() throws Throwable {
		driver.findElement(By.xpath("//div[@class='company-address-container']/label[contains(.,'Firma')]")).click();
	}

	@Then("^User should be able to see the disappearance of the textbox Firma$")
	public void User_should_be_able_to_see_the_disappearance_of_the_textbox_Firma() throws Throwable {
		WebElement firma_txt=driver.findElement(By.xpath("//div[@class='checkout-floatLabel floatLabel-empty']/label[contains(.,'Firma')]"));
        assertFalse("Firma textbox is displayed",firma_txt.isDisplayed());
	}


   @When("^clicks on jetzt kaufen button$")
   public void clicks_on_jetzt_kaufen_button() throws Throwable {
	    driver.findElement(By.xpath("(//button[contains(.,'jetzt kaufen')])[1]")).click();
	}

   @Then("^User should be able to see error \"([^\"]*)\"$")
   public void User_should_be_able_to_see_error(String exp_msg) throws Throwable {
	   String act_msg=driver.findElement(By.xpath("//div[@class='flash-message-box']")).getText().trim();
	   assertEquals("Actual and Expected messages are not matching", exp_msg, act_msg);
	   Thread.sleep(1000);
	    
	}

   @When("^User enters \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\" details$")
   public void User_enters_and_details(String vorname, String nachname, String plz, String ort, String strasse, String nummer, String telephoneNummer) throws Throwable {
	   driver.findElement(By.xpath("//label[@for='address-female-title-billing']/span")).click();
	   WebElement vorname_txt=driver.findElement(By.id("address-first-name-billing"));
	   vorname_txt.clear();
	   vorname_txt.sendKeys(vorname);
	   WebElement nachname_txt=driver.findElement(By.id("address-last-name-billing"));
	   nachname_txt.clear();
	   nachname_txt.sendKeys(nachname);
	   WebElement plz_txt=driver.findElement(By.id("address-po-number-billing"));
	   plz_txt.clear();
	   plz_txt.sendKeys(plz);
	   Thread.sleep(3000);
	   WebElement straße_txt=driver.findElement(By.cssSelector("#address-street-billing"));
	   straße_txt.sendKeys(strasse);
	   WebElement nummer_txt=driver.findElement(By.cssSelector("#address-house-number-billing"));
	   nummer_txt.sendKeys(nummer);
	   WebElement telefonnummer_txt=driver.findElement(By.id("address-phone-number-billing"));
	   telefonnummer_txt.clear();
	   telefonnummer_txt.sendKeys(telephoneNummer);
	}

	@Then("^User should be navigated to payment page$")
	public void User_should_be_navigated_to_payment_page() throws Throwable {
		String actualTitle=driver.getTitle();
		String expectedTitle = "Bezahlung – Shopping │ CHECK24";
		assertEquals("Payment page is not displayed", expectedTitle, actualTitle);
		Thread.sleep(3000);
		jse.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@id='back']")) );
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Löschen')]")).click();
		driver.close();
	 
	}

}
