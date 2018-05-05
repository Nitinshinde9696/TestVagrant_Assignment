package commonutil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class Utility {
	
	WebDriver driverlocal;
	
		public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	public void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}

	}

	public WebDriver setBrowserandLaunchApplication(WebDriver driver) {

		System.out.println("before driver initiation");
		driver = new ChromeDriver();
		System.out.println("driver initialization");
		driver.get("https://www.cleartrip.com/");
		System.out.println("Navigating to URL");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driverlocal= driver;
		return driver;
		
	}

	public void screenShot(WebDriver driver, String filename) throws IOException {
		
		System.out.println("Screen Shot method Started");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(filename + ".png"));
		System.out.println("Screen Shot method Finished");
	}


}
