package com.example.appium.driver.strategies;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class IOSManager implements DriverStragies {


 @Override

    public AppiumDriver setStrategy() {
        // Implementasi untuk iOS driver
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 12");
        options.setPlatformName("iOS");
        options.setPlatformVersion("14.5");
        options.setApp("com.google.calculator"); // Ganti dengan path ke aplikasi iOS yang sesuai
        options.setNoReset(true);

        AppiumDriver driver = null;
        try {
            driver = new IOSDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalStateException("Appium server URL is invalid", e);
        }   
        return driver;

    }


}
