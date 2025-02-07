package org.example;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class TestApp {
    private AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("platformName", "android")
                .amend("appium:automationName", "UiAutomator2")
                .amend("appium:deviceName", "local")
                .amend("appium:udid", "emulator-5554")
                .amend("appium:appPackage", "com.androidsample.generalstore")
                .amend("appium:appActivity", "com.androidsample.generalstore.SplashActivity");

        URL remoteUrl = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(remoteUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void sampleTest() {
        var el1 = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry"));
        el1.click();
        WebElement location = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Brazil\"))"));
        location.click();

//        var el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Angola\")"));
//        el2.click();
        WebElement el3 = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"));
        el3.sendKeys("Hello");
        var el4 = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"));
        el4.click();
        var el5 = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"));
        el5.click();
        var el6 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/productAddCart\").instance(0)"));
        el6.click();


    }

    @AfterTest
    public void tearDown() {
        driver.removeApp("com.androidsample.generalstore");
        driver.quit();
    }
}

