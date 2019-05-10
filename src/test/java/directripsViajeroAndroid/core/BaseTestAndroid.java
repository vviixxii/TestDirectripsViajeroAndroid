package directripsViajeroAndroid.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.Log;

/**
 * Clase base para la creación de los Test Case
 * 
 * @author Ricardo Romero
 * @version 1.0.0
 * @date 29/04/19
 */
public class BaseTestAndroid {

	public static WebDriver driver;
	static String propertyFilePath = "config//config.properties";
	protected static Properties prop = new Properties();
	
	private static String platformName = null;
	private static String skipUnlock = null;
	private static String appPackage = null;
	private static String appActivity = null;
	private static String noReset = null;
	private static String androidDriverUrl = null;
	private static String deviceName = null;
	private static String udid = null;
	private static String platformVersion = null;

	public static void initialization() {
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
	
	public void takeSnapShot(String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}
}
