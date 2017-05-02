import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import Common.Waiter;

public class JavaRemoteFirefoxDriver {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
    	WebDriver driver ;
    	String baseurl;
    	String nodeurl;
    	
    	baseurl = "https://www.browserstack.com/";
    	nodeurl = "http://10.100.101.102:5566/wd/hub";
    	
    	//System.setProperty("webdriver.chrome.driver","/Applications/chromedriver");

    	DesiredCapabilities capability = DesiredCapabilities.firefox();
    	capability.setBrowserName("firefox");
    	capability.setPlatform(Platform.MAC);
    	
    	
        String expectedTitle = "Dashboard";
        String actualTitle = "";
        
    	try
    	{
    		driver = new RemoteWebDriver(new URL(nodeurl), capability);
            Waiter _waiter = new Waiter(driver);
    		
	        //Step 1: Open the BrowserStack site
	    	driver.get(baseurl);
	    	driver.manage().window().maximize();
	    	_waiter.waitForMe(By.linkText("Sign in"), 5);
	    	
	    	//Step 2: Search for Sign In link and click on it
	    	driver.findElement(By.linkText("Sign in")).click();  
	    	_waiter.waitForMe(By.id("user_email_login"), 5);
	        
	    	//Step 3: Enter the email and password and click on Sign in button
	        driver.findElement(By.id("user_email_login")).sendKeys("email");
	        driver.findElement(By.id("user_password")).sendKeys("password");
	        driver.findElement(By.id("user_submit")).click(); 
	        _waiter.waitForMe(By.xpath("//*[@id=\"rf-browsers\"]/div/div[2]/div[4]/ul/li[1]/a"), 5);
	        
	        //Step 5: Select the browser you want to open live session for and click on it
		    driver.findElement(By.xpath("//*[@id=\"rf-browsers\"]/div/div[2]/div[4]/ul/li[1]/a")).click(); 
		    _waiter.waitForMe(By.id("dock"), 10);
	        
	        actualTitle = driver.getTitle();
	        
	        //Step 6: Check whether the live session was successful by checking the title of the Live session page
	        if (actualTitle.contentEquals(expectedTitle)){
	            System.out.println("Test Passed!");
	        } else {
	            System.out.println("Test Failed");
	        } 
	        
	        //close Firefox Driver
	        driver.quit();
	        
    	}
    	catch(Exception e)
    	{
            System.out.println("Exception");

    	}
       
        // exit the program explicitly
        System.exit(0);
    }

}