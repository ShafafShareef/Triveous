package theoutpostP0TCs;



import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstFiveP0TCs {


	    WebDriver driver;

	    @BeforeTest
	    public void setup() {
	        WebDriverManager.chromedriver().setup();  
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://theoutpost.ai/");
	    }

	    @Test(priority = 1)
	    public void testPageLoad() {
	        long startTime = System.currentTimeMillis();
	        driver.get("http://theoutpost.ai/");
	        long endTime = System.currentTimeMillis();
	        long loadTime = endTime - startTime;
	        Assert.assertTrue(loadTime < 3000, "Page load time is too long.");
	    }

	    @Test(priority = 2)
	    public void checkPresenceofAIToolLinks() throws InterruptedException {
	        WebElement aiTools = driver.findElement(By.xpath("//div[text()='AI Tools']"));
	        Thread.sleep(3000);
	        Assert.assertTrue(aiTools.isDisplayed(), "aiTools is not present on the page");

	    }

	    @Test(priority = 3)
	    public void testResponsiveLayout() {
	        driver.manage().window().setSize(new Dimension(320, 568)); // Mobile resolution
	        WebElement logo = driver.findElement(By.xpath("/html/body/div[1]/nav/div[1]"));
	        Assert.assertTrue(logo.isDisplayed(), "Logo not visible on mobile.");
	    }

	    @Test(priority = 4)
	    public void testNewsletterSubscription() {
	        WebElement subscribeButton = driver.findElement(By.xpath("//button[text()='Subscribe']"));
	        WebElement emailInput = driver.findElement(By.xpath("//input[@placeholder='Enter your email']"));
	        subscribeButton.click();
	        emailInput.sendKeys("test@example.com");
	        WebElement confirmation = driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/div[2]/div[2]/div/div"));
	        Assert.assertTrue(confirmation.isDisplayed(), "Newsletter subscription failed.");
	    }

	    @Test(priority = 5)
	    public void testSearchFunctionality() throws InterruptedException {
	    	
	        WebElement searchIcon = driver.findElement(By.xpath("//button[1]"));
	        searchIcon.click();
	        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search AI tools']"));
	        searchInput.sendKeys("Gemini by Google");
	        Thread.sleep(4000);
	        searchInput.sendKeys(Keys.ENTER);
	        Thread.sleep(2000);
	        WebElement geminiIcon = driver.findElement(By.xpath("//img[@alt='Gemini by Google']"));
	        Assert.assertTrue(geminiIcon.isDisplayed(), "Search results are not relevant.");
	    }

	    @AfterTest
	    public void tearDown() {
	        driver.quit();
	    }
}


