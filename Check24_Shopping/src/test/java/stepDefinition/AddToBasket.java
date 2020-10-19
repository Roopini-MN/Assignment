package stepDefinition;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class AddToBasket extends BaseUtilities {
	
	WebDriver driver=getDriver();
	JavascriptExecutor jse=getJseDriver();
	
	@Given("^User is on check24 website$")
	public void User_is_on_check24_website() throws Throwable {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://check24.de");
	}

	@When("^User clicks on Shopping Link from Homepage$")
	public void User_clicks_on_Shopping_Link_from_Homepage() throws Throwable {
		WebElement ele=driver.findElement(By.xpath("//a[text()='Akzeptieren']"));
		if(ele.isDisplayed())
		{
			ele.click();
		}
		driver.findElement(By.xpath("//a[.='Shopping']")).click();
		Thread.sleep(3000);
	 
	}

	@Then("^User should be navigated to shopping home page$")
	public void User_should_be_navigated_to_shopping_home_page() throws Throwable {
		String actualTitle=driver.getTitle();
		String expectedTitle = "Preisvergleich - sicher günstig shoppen | CHECK24";
		assertEquals("Shopping Home page is not displayed", expectedTitle, actualTitle);
	   
	}

	@When("^User search for \"([^\"]*)\" in the search box$")
	public void User_search_for_in_the_search_box(String arg1) throws Throwable {
		WebElement search_btn=driver.findElement(By.id("query"));
		search_btn.clear();
		search_btn.sendKeys(arg1);
		driver.findElement(By.xpath("//button[@class='wf-search__button']")).click();
	 
	}

	@And("^Selects the Tute Luft product from result list$")
	public void Selects_the_Tute_Luft_product_from_result_list() throws Throwable {
		WebElement price_ele=driver.findElement(By.xpath("//div[.='Eine Tüte Luft']/following-sibling::div[@class='product__price']"));
		assertTrue("Price element is not displayed",price_ele.isDisplayed());
	    driver.findElement(By.xpath("//a[.='Eine Tüte Luft']")).click();	 
	}

	@Then("^User should be navigated to respective Product page$")
	public void User_should_be_navigated_to_respective_Product_page() throws Throwable {
		String actualTitle=driver.getTitle();
		String expectedTitle = "Eine Tüte Luft 2020 Testprodukte Spedition";
		assertEquals("Product Page page is not displayed", expectedTitle, actualTitle);
	}

	@When("^User clicks on the in den Warenkorb button$")
	public void User_clicks_on_the_in_den_Warenkorb_button() throws Throwable {
		Thread.sleep(3000);
		WebElement warenkorb_btn=driver.findElement(By.xpath("//button[contains(.,'in den Warenkorb')]"));
		warenkorb_btn.click();
		Thread.sleep(3000);
	}

	

	@Then("^Pop up should be displayed along with message$")
	public void Pop_up_should_be_displayed_along_with_message() throws Throwable {
	    WebElement message_ele=driver.findElement(By.xpath("//span[.='Ihr Produkt wurde erfolgreich zum Warenkorb hinzugefügt.']"));
	    assertTrue("Product is not added successfully to basket",message_ele.isDisplayed());
	    driver.findElement(By.xpath("//div[@class='modal-desktop__header']/div")).click();
	    Thread.sleep(1000);
	}
	
	@When("^User clicks on the Warenkorb link$")
	public void User_clicks_on_the_Warenkorb_link() throws Throwable {
		Thread.sleep(3000);
		WebElement warenkorb_lnk=driver.findElement(By.xpath("//a/div[contains(.,'Warenkorb')]"));
		warenkorb_lnk.click();
	
	}

	@Then("^User should be navigated to cart page$")
	public void User_should_be_navigated_to_cart_page() throws Throwable {
		String actualTitle=driver.getTitle();
		String expectedTitle = "Warenkorb - Shopping | CHECK24";
		assertEquals("Shopping Home page is not displayed", expectedTitle, actualTitle);
	    
	}

	@And("^User sees the \"([^\"]*)\" and \"([^\"]*)\" details$")
	public void User_sees_the_and_details(String exp_price,String exp_totalprice) throws Throwable {
	   String act_price=driver.findElement(By.xpath("//div[@class='cart-tile']//div[@class='cart-item-price']")).getText().trim();
	   System.out.println("Actual price is "+act_price);
	   assertEquals("Actual and Expected price are not matching", exp_price, act_price);
	   
	   String act_totalprice=driver.findElement(By.xpath("//div[@class='column cart-total__price']")).getText().trim();
	   System.out.println("Actual totalptrice is "+act_totalprice);
	   assertEquals("Actual and Expected total price are not matching", exp_totalprice, act_totalprice);
	}

	@When("^User changes the \"([^\"]*)\"$")
	public void User_changes_the(String quantity) throws Throwable {
		Select select=new Select(driver.findElement(By.xpath("//div[@class='cart-tile']//select")));
		select.selectByVisibleText(quantity);
		Thread.sleep(2000);
		}

	@Then("^User should be able to see the updated \"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\" details$")
	public void User_should_be_able_to_see_the_updated_and_details(String exp_zwischensumme, String exp_gesamtsumme, String exp_punkte) throws Throwable {
		String act_zwischensumme=driver.findElement(By.xpath("//div[@class='cart-summary']/div[1]/div[@class='column cart-list__price']")).getText().trim();
		System.out.println("zwischensumme is "+act_zwischensumme);
		assertEquals("Actual and Expected zwischensumme are not matching", exp_zwischensumme, act_zwischensumme);
	 
		String act_gesamtsumme=driver.findElement(By.xpath("//div[@class='cart-summary']/div[3]/div[@class='column cart-total__price']")).getText().trim();
		System.out.println("gesamtsumme is "+act_gesamtsumme);
		assertEquals("Actual and Expected gesamtssumme are not matching", exp_gesamtsumme, act_gesamtsumme);
	 
		String punkteDetails=driver.findElement(By.xpath("//div[@class='column small-expand']/span[2]/span[1]")).getText().trim();
		String act_punkte[]=punkteDetails.split(" ");
		System.out.println("punkte is "+punkteDetails);
		Thread.sleep(3000);
		assertEquals("Actual and Expected punkte are not matching", exp_punkte, act_punkte[0]);
	}

	@When("^User clicks on the right slider arrow button$")
	public void User_clicks_on_the_right_slider_arrow_button() throws Throwable {
		jse.executeScript("window.scrollBy(0,800)");
		driver.findElement(By.xpath("//div[@class='s2-carousel']/span[2]")).click();
		
	}
	
	@Then("^User should verify that the left arrow button is enabled$")
	public void User_should_verify_that_the_left_arrow_button_is_enabled() throws Throwable {
		WebElement leftArrow_btn=driver.findElement(By.xpath("//div[@class='s2-carousel']/span[1]"));
		assertTrue("Left arrow button is not enabled",leftArrow_btn.isEnabled());
	}
	
	@When("^User clicks on the link Löschen$")
	public void User_clicks_on_the_link_Löschen() throws Throwable {
	   driver.findElement(By.xpath("//div[@class='cart-item__controls']//a/span[.='Löschen']")).click();
	   Thread.sleep(1000);
	}


	@Then("^User should be able to see the \"([^\"]*)\"$")
	public void User_should_be_able_to_see_the(String exp_msg) throws Throwable {
	   String act_msg=driver.findElement(By.xpath("//div//p[contains(.,'Ihr Warenkorb ist zurzeit leer')]")).getText().trim();
	   assertEquals("Actual and Expected message are not matching", exp_msg, act_msg);
	  }

}
