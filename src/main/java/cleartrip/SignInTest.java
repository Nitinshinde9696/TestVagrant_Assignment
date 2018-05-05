package cleartrip;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonutil.Utility;

public class SignInTest {

	WebDriver driver;;
	Utility Utility = new Utility();
	
	@BeforeMethod
	public void openConnection() {
		Utility.setDriverPath();
		driver = Utility.setBrowserandLaunchApplication(driver);
	}

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {


		Utility.waitFor(2000);
		driver.findElement(By.linkText("Your trips")).click();
		System.out.println("Your trips success");
		driver.findElement(By.id("SignIn")).click();
		System.out.println("Sign In Click");
		Utility.waitFor(2000);
		driver.switchTo().frame("modal_window");
		Utility.waitFor(2000);
		System.out.println("Navigate to frame success");
	
		driver.findElement(By.id("signInButton")).click();
		System.out.println("Click on signin success");
		String errors1 = driver.findElement(By.id("errors1")).getText();
		System.out.println("Error message"+errors1);
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
	}
	@AfterMethod
	public void closeConnection() {
		driver.close();
	}

}
