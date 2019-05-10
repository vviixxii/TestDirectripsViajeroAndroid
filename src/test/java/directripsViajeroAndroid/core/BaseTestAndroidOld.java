package directripsViajeroAndroid.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.Log;

/**
 * Clase base para la creación de los Test Case
 * 
 * @author Ricardo Romero
 * @version 1.0.0
 * @date 26/04/19
 */
public class BaseTestAndroidOld {

	static AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	String propertyFilePath = "config//config.properties";
	protected Properties prop = new Properties();
	
	private String platformName = null;
	private String skipUnlock = null;
	private String appPackage = null;
	private String appActivity = null;
	private String noReset = null;
	private String androidDriverUrl = null;
	private String deviceName = null;
	private String udid = null;
	private String platformVersion = null;

//	public static AndroidDriver<io.appium.java_client.MobileElement> getDriver() throws MalformedURLException {
//		if (driver == null) {
//			
//			DesiredCapabilities caps = new DesiredCapabilities();
//			caps.setCapability("deviceName", "Galaxy Nexus API 24");
//			caps.setCapability("udid", "emulator-5554");
//			caps.setCapability("platformName", "Android");
//			caps.setCapability("platformVersion", "7.0");
//			caps.setCapability("skipUnlock", "true");
//			caps.setCapability("appPackage", "directrips.viajero");
//			caps.setCapability("appActivity", "directrips.viajero.functionalities.splashScreen.controllers.activities.SplashActivity");
//			caps.setCapability("noReset", "false");
//			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.1.1:4723/wd/hub"), caps);
//		}
//		return driver;
//	}

	@BeforeClass(description = "Configuración a nivel de clase!")
	public void setup() throws MalformedURLException {
		try {
			prop.load(new FileInputStream(propertyFilePath));
			deviceName = prop.getProperty("caps.deviceName");
			udid = prop.getProperty("caps.udid");
			platformName = prop.getProperty("caps.platformName");
			platformVersion = prop.getProperty("caps.platformVersion");
			skipUnlock = prop.getProperty("caps.skipUnlock");
			appPackage = prop.getProperty("caps.appPackage");
			appActivity = prop.getProperty("caps.appActivity");
			noReset = prop.getProperty("caps.noReset");
			androidDriverUrl = prop.getProperty("android.driver.url");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", deviceName);
			caps.setCapability("udid", udid);
			caps.setCapability("platformName", platformName);
			caps.setCapability("platformVersion", platformVersion);
			caps.setCapability("skipUnlock", skipUnlock);
			caps.setCapability("appPackage", appPackage);
			caps.setCapability("appActivity", appActivity);
			caps.setCapability("noReset", noReset);
			driver = new AndroidDriver<MobileElement>(new URL(androidDriverUrl), caps);
		} catch (IOException e) {
			Log.error("El archivo config.properties no se encontró.");
		}
	}

	@AfterClass(description = "Desglose a nivel de clase!")
	public void teardown() {
		driver.quit();
	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}
}
