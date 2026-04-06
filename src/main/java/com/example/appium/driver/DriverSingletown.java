package com.example.appium.driver;

import java.time.Duration;

import com.example.appium.driver.strategies.DriverStragies;
import com.example.appium.driver.strategies.DriverStrategiesImplementor;

import io.appium.java_client.AppiumDriver;

public class DriverSingletown {

    private static DriverSingletown instance = null;
    private static AppiumDriver driver;

    private DriverSingletown(String driverStrategy) {
        instantiate(driverStrategy);
    }

    public AppiumDriver instantiate(String strategy) {
        if (driver == null) {
            DriverStrategiesImplementor driverStrategyImplementer = new DriverStrategiesImplementor();
            DriverStragies driverStrategy = driverStrategyImplementer.chooseStrategy(strategy);
            driver = driverStrategy.setStrategy();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static DriverSingletown getInstance(String driver) {
        if (instance == null) {
            instance = new DriverSingletown(driver);
        }
        return instance;
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver has not been initialized. Call getInstance() first.");
        }
        return driver;
    }

    public static void closeObjectInstance() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        instance = null;
    }

}
