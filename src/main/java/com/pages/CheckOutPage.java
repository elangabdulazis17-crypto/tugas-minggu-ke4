package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOutPage {
    AppiumDriver driver;


    @AndroidFindBy (accessibility = "test-CHECKOUT")
    private WebElement checkoutButton;

    @AndroidFindBy (accessibility = "test-First Name")
    private WebElement firstNameField;

    @AndroidFindBy (accessibility = "test-Last Name")
    private WebElement lastNameField;

    @AndroidFindBy (accessibility = "test-Zip/Postal Code")
    private WebElement postalCodeField;

    @AndroidFindBy (accessibility = "test-CONTINUE")
    private WebElement continueButton;

    @AndroidFindBy(accessibility = "test-CHECKOUT")
    private WebElement btnCheckoutSebenarnya;

    @AndroidFindBy (accessibility = "test-FINISH")
    private WebElement finishButton;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'THANK YOU')]")
    private WebElement thankYouMessage;

    public CheckOutPage(AppiumDriver driver) {
        this.driver =  driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(10)), this);
    }

    public void inputInformasi(String firstName, String lastName, String zip) {
        
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(zip);
        continueButton.click();
    }

     public void klikTombolCheckout() {
    // Jika tidak ketemu, gunakan scroll yang saya berikan sebelumnya
    btnCheckoutSebenarnya.click();
}
    public void finishCheckout() {
    // 1. Scroll ke bawah sampai tombol FINISH kelihatan
    
    driver.findElement(AppiumBy.androidUIAutomator(
    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"test-FINISH\"))"
    ));

    // 2. Baru klik tombol FINISH
    finishButton.click(); 
}

    public String getThankYouMessage() {
        return thankYouMessage.getText();
    }

}
