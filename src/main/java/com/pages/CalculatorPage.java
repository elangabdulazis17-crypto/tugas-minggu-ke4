package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v143.page.Page;
import org.openqa.selenium.support.PageFactory;

import com.example.appium.App;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITBy;

public class CalculatorPage {

    public CalculatorPage(AppiumDriver driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_5")
    @iOSXCUITBy(accessibility = "5")
    private WebElement digit5;

    @AndroidFindBy(id = "com.google.android.calculator:id/op_add")
    @iOSXCUITBy(accessibility = "add")
    private WebElement addButton;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_3")
    @iOSXCUITBy(accessibility = "3")
    private WebElement digit3;

    @AndroidFindBy(id = "com.google.android.calculator:id/eq")
    @iOSXCUITBy(accessibility = "equals")
    private WebElement equalsButton;

    @AndroidFindBy(id = "com.google.android.calculator:id/result_final")
    @iOSXCUITBy(accessibility = "result")
    private WebElement resultText;

   

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_1")
    @iOSXCUITBy(accessibility = "1")
    private WebElement digit1;
     @AndroidFindBy(id = "com.google.android.calculator:id/digit_0")
    @iOSXCUITBy(accessibility = "0")
    private WebElement digit0;

    @AndroidFindBy(id = "com.google.android.calculator:id/op_sub")
    @iOSXCUITBy(accessibility = "subtract")
    private WebElement subButton;
    
    @AndroidFindBy(id = "com.google.android.calculator:id/digit_4")
    @iOSXCUITBy(accessibility = "4")    
    private WebElement digit4Sub;


    public void performAddition() {
        digit5.click();
        addButton.click();
        digit3.click();
        equalsButton.click();
    }

    public void performSubtraction() {
        digit1.click();
        digit0.click();
        subButton.click();
        digit4Sub.click();
        equalsButton.click();
    }

    public String getResult() {
        return resultText.getText();
    }
}
