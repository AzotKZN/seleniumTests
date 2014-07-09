
import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.Select;

public class newnote {
  private WebDriver driver = null;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://178.205.251.227:8087/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  public void openHeadless() throws Exception
  {
      FirefoxBinary binary = new FirefoxBinary(new File("/usr/local/bin/firefox"));
      binary.setEnvironmentProperty("DISPLAY",System.getProperty("lmportal.xvfb.id",":99"));
      driver = new FirefoxDriver(binary,null);
  }

  @Test
  public void testNewnote() throws Exception {
   	driver.get(baseUrl);
   	WebDriver augmentedDriver = new Augmenter().augment(driver);
    File screenshot = ((TakesScreenshot)augmentedDriver).
                        getScreenshotAs(OutputType.FILE);
	driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("lol");
    driver.findElement(By.id("passwrd")).clear();
    driver.findElement(By.id("passwrd")).sendKeys("l");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    driver.findElement(By.cssSelector("div > img")).click();
    driver.findElement(By.linkText("Сохранить")).click();
    driver.findElement(By.cssSelector("a.mainButton")).click();
    driver.findElement(By.linkText("Выйти из системы")).click();

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
