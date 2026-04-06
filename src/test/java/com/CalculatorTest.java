package com;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.appium.driver.DriverSingletown;
import com.pages.CalculatorPage;

import io.appium.java_client.AppiumDriver;

public class CalculatorTest {
    private AppiumDriver driver;
    private CalculatorPage calculatorPage;

    @BeforeClass
    public void setUp() {
        // Initialize the Appium driver and calculator page
        DriverSingletown.getInstance("Android");
        driver = DriverSingletown.getDriver();
        calculatorPage = new CalculatorPage(driver);
    }

    @AfterClass
    public void tearDown() {
        // Close the Appium driver
        DriverSingletown.closeObjectInstance();
    }

    @Test(description = "Test Case: 5 + 3 = 8")
    public void testAddition() {
        // Perform addition and verify the result
        calculatorPage.performAddition();
        String result = calculatorPage.getResult();
        Assert.assertEquals(result, "8", "Expected result of 5 + 3 should be 8");
    }
    @Test
    public void Kurang(){
        calculatorPage.performSubtraction();
        String result = calculatorPage.getResult();
        Assert.assertEquals(result, "6", "Expected result of 10 - 4 s0hould be 6");
    }

}
