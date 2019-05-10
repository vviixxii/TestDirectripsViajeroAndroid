package directripsViajeroAndroid;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class IoSampleTest_Original {

	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Galaxy Nexus API 24");
		caps.setCapability("udid", "emulator-5554"); // DeviceId from "adb devices" command
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.0");
		caps.setCapability("skipUnlock", "true");
		caps.setCapability("appPackage", "directrips.viajero");
		caps.setCapability("appActivity", "directrips.viajero.functionalities.splashScreen.controllers.activities.SplashActivity");
		caps.setCapability("noReset", "false");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.1.1:4723/wd/hub"), caps);
		wait = new WebDriverWait(driver, 30);
	}

	@Test
	public void basicTest() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/button_start_session"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/editText_account_email"))).sendKeys("yo@x.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/editText_account_password"))).sendKeys("pruebas");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/button_start_session"))).click();
		
		Thread.sleep(5000);
		
		// Notification Allow
		System.out.println("Size 1 -> " + driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size());
		if (driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size() > 0) {
			System.out.println("Size 2 -> " + driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size());
			driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).get(0).click();
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/textView_inputQuery"))).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/textView_inputQuery"))).sendKeys("Hermosillo");
		
		Thread.sleep(5000);
		System.out.println("Pelation !!!!");

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
