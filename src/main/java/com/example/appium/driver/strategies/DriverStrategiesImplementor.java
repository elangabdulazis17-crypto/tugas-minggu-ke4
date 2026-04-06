package com.example.appium.driver.strategies;

public class DriverStrategiesImplementor {

 public DriverStragies chooseStrategy(String strategy) {
    switch (strategy.toLowerCase()) {
        case "android":
            return new AndroidManager();
        case "ios":
            return new IOSManager();
        default:
            return null;
    }
}
}
