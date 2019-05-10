package directripsViajeroAndroid;

import utils.Log;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import directripsViajeroAndroid.core.BaseTestAndroid;
import directripsViajeroAndroid.core.DirectripsAndroidDataProvider;
import directripsViajeroAndroid.core.DirectripsAndroidListener;
import io.appium.java_client.MobileElement;

/**
 * TestCase: Búsqueda por Hotel
 * 
 * @author Ricardo Romero
 * @version 1.0.0
 * @date 22/04/19
 */
@Listeners(DirectripsAndroidListener.class)
public class IoSampleTest extends BaseTestAndroid {

	public WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		initialization();
		wait = new WebDriverWait(driver, 30);
		Log.startTestCase(this.getClass().getName());
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(dataProvider = "busquedas", dataProviderClass = DirectripsAndroidDataProvider.class)
	public void basicTest(String... params) throws InterruptedException {
		try {

//			MobileElement el2 = (MobileElement) driver.findElementById("directrips.viajero:id/button_start_session");
//			el2.click();
//			MobileElement el3 = (MobileElement) driver.findElementById("directrips.viajero:id/editText_account_email");
//			el3.sendKeys("superusuario@societao.com");
//			MobileElement el4 = (MobileElement) driver.findElementById("directrips.viajero:id/editText_account_password");
//			el4.sendKeys("veureka2019t");
//			MobileElement el5 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout");
//			el5.click();
//			MobileElement el6 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup");
//			el6.click();
//			MobileElement el7 = (MobileElement) driver.findElementById("directrips.viajero:id/button_start_session");
//			el7.click();
//			MobileElement el8 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
//			el8.click();
//			MobileElement el9 = (MobileElement) driver.findElementById("directrips.viajero:id/imageView_lens");
//			el9.click();
//			MobileElement el10 = (MobileElement) driver.findElementById("directrips.viajero:id/constraintLayout_queryBox_login");
//			el10.click();
//			MobileElement el11 = (MobileElement) driver.findElementById("directrips.viajero:id/editText_query");
//			el11.sendKeys("Mazatlan");
//			MobileElement el12 = (MobileElement) driver.findElementById("directrips.viajero:id/editText_query");
//			el12.click();
//			MobileElement el13 = (MobileElement) driver.findElementById("directrips.viajero:id/imageView_lens");
//			el13.click();
//			MobileElement el14 = (MobileElement) driver.findElementById("directrips.viajero:id/textView_inputQuery");
//			el14.click();

			Log.info("Logeo del usuario");
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/button_start_session")))
					.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.id("directrips.viajero:id/editText_account_email")))
					.sendKeys(params[0]);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.id("directrips.viajero:id/editText_account_password")))
					.sendKeys(params[1]);
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/button_start_session")))
					.click();

			Thread.sleep(2000);

			Log.info("Acepta notificaciones");
			if (driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size() > 0) {
				driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).get(0).click();
			}

			try {
				Log.info("Hace tap en el campo de busqueda");
//				List<WebElement> lista = driver.findElements(By.id("directrips.viajero:id/textView_inputQuery"));
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.id("directrips.viajero:id/textView_inputQuery"))).click();
			} catch (NoSuchElementException e) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.id("directrips.viajero:id/constraintLayout_queryBox_login")))
						.click();
			}

			Thread.sleep(5000);
			Log.info("Busca el paramTexto");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("directrips.viajero:id/editText_query")))
					.sendKeys(params[2]);

			Thread.sleep(1000);
			System.out.println("Thats God !!!!");
		} catch (NoSuchElementException e) {
			System.out.println("Pelation !!!!");
			Log.info("No existe el elemento buscado ..... " + e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		Log.endTestCase(this.getClass().getName());
	}
}
