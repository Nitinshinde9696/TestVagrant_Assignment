package cleartrip;

import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import commonutil.Utility;

public class HotelBookingTest {

	WebDriver driver;
	Utility Utility = new Utility();
	@BeforeMethod
	public void openConnection() {

		Utility.setDriverPath();
		driver = Utility.setBrowserandLaunchApplication(driver);
	}

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@Test
	public void shouldBeAbleToSearchForHotels() {

		PageFactory.initElements(driver, this);
		
		System.out.println("Test Method");
		hotelLink.click();
		System.out.println("clicked on hotel link");
		Utility.waitFor(3000);
		localityTextBox.sendKeys("Indiranagar, Bangalor");
		Utility.waitFor(2000);
		
		List<WebElement> location = driver.findElements(By.xpath("//*[@id='ui-id-3']"));
		System.out.println("Size of Elements "+location.size());
		location.get(0).click();
		
		driver.findElement(By.xpath("//*[@id='Home']/section/div/div/div[1]/i")).click();
		driver.findElement(By.xpath("//*[@id='CheckInDate']")).click();

		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[2]/td[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='Home']/section/div/div/div[1]/i")).click();
		driver.findElement(By.xpath("//*[@id='CheckOutDate']")).click();
		
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[4]/td[5]/a")).click();

		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		searchButton.click();

	}

	@AfterMethod
	public void closeConnection() {
		driver.close();
	}

}
