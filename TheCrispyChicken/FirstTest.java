package TheCrispyChicken;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {
	String WebSite = "https://crispychicken.rest/";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void SetUp() {
		driver.get(WebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test()
	public void MakingAnOrder() throws InterruptedException {
		WebElement TheList = driver.findElement(By.className("poco-icon-bars"));
		TheList.click();
		WebElement TheMenu = driver.findElement(By.xpath("//*[@id=\"menu-mobile-menu-3\"]/li[2]/a"));
		String TheActualName = TheMenu.getText();
		TheMenu.click();
		WebElement ThePageOfTheMenu = driver.findElement(By.className("breadcrumb-heading"));
		Thread.sleep(2000);
		String TheExpectedName = ThePageOfTheMenu.getText();
		Assert.assertEquals(TheActualName, TheExpectedName);
		WebElement TheSingleMeal = driver.findElement(By.id("elementor-tab-title-2612"));
		TheSingleMeal.click();
		WebElement TheSecondItem = driver
				.findElement(By.xpath("//*[@id=\"elementor-tab-content-2612\"]/div/ul/li[2]/div/div[2]/h3/a"));
		String TheActualItemName = TheSecondItem.getText();
		TheSecondItem.click();
		WebElement TheItemFromInside = driver.findElement(By.xpath("//*[@id=\"product-7503\"]/div/div[2]/h1"));
		String TheExpectedItemName = TheItemFromInside.getText();
		Assert.assertEquals(TheActualItemName, TheExpectedItemName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		WebElement AddToCart = driver.findElement(By.xpath("//*[@id=\"page\"]/section/div/div/a"));
		AddToCart.click();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,700)");
		WebElement ThePriceElement = driver
				.findElement(By.xpath("//*[@id=\"order_review\"]/div[1]/table/tbody/tr/td[2]/span/bdi"));
		double TheItemPrice = Double.valueOf(ThePriceElement.getText().replace("JD", ""));
		WebElement TheFinalPrice = driver
				.findElement(By.xpath("//*[@id=\"order_review\"]/div[1]/table/tfoot/tr[2]/td/strong/span/bdi"));
		double TheFinalItemPrice = Double.valueOf(TheFinalPrice.getText().replace("JD", ""));
		Assert.assertEquals(TheItemPrice, TheFinalItemPrice);

	}

	@AfterTest
	public void Final() {
		WebElement GetBackToTheOrignalPage = driver
				.findElement(By.xpath("//*[@id=\"page\"]/div[4]/div/section[1]/div[2]"));
		GetBackToTheOrignalPage.click();
	}
}
