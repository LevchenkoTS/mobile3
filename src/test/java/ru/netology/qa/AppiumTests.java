package ru.netology.qa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.netology.qa.screens.MainScreenForHomeWork;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumTests {

    private AndroidDriver driver;
    private String textToSet = "Netology";
    private String emptyString = " ";



    private String nonEmptyText = "Something";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testTryingToSetAnEmptyString() {

        MobileElement el5 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el5.click();
        el5.sendKeys(textToSet);
        MobileElement el6 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el6.click();
        el5.click();
        el5.sendKeys(emptyString);
        MobileElement el4 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        el4.click();

        assertEquals(textToSet, el4.getText());

    }
    @Test
    public void testCheckTextInNewActivity() {

        MobileElement el5 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el5.click();
        el5.sendKeys(textToSet);
        MobileElement el7 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el7.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        MobileElement el8 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        el8.click();

        assertEquals(textToSet, el8.getText());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}