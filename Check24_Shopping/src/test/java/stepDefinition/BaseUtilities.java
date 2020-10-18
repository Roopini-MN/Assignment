package stepDefinition;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class BaseUtilities {
	
	protected static WebDriver driver;
	protected static WebDriver mobdriver;
	protected static JavascriptExecutor jse;
	
	protected WebDriver getDriver(){
		if (driver == null){
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	protected WebDriver getMobileDriver() {
		if(mobdriver==null) {
			
			Map<String,String> mobileEmulator=new HashMap<String,String>();
			mobileEmulator.put("deviceName", "iPhone 6/7/8");
			ChromeOptions chromeOptions=new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation",mobileEmulator);
			
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
			
			mobdriver=new ChromeDriver(chromeOptions);
		}
		return mobdriver;
	}
	
	protected JavascriptExecutor getJseDriver(){
		if (jse == null){
			jse = (JavascriptExecutor)driver;
		}
		return jse;		
	}

}
