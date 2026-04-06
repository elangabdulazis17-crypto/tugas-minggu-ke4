package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage {
    private AppiumDriver driver;

    @AndroidFindBy (accessibility = "test-Modal Selector Button")
    private WebElement sortButton;

    @AndroidFindBy (xpath = "//android.widget.TextView[@text='Price (low to high)']")
    private WebElement lowtoHigh;

    @AndroidFindBy (xpath = "//android.widget.TextView[@text='Price (high to low)']")
    private WebElement highToLow;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[1]")
    private WebElement firstAddToCartBtn;

    @AndroidFindBy(accessibility = "test-Cart")
    private WebElement btnIkonKeranjang;

    @AndroidFindBy(accessibility = "test-CHECKOUT")
    private WebElement btnCheckout;

    public ProductPage(AppiumDriver driver) { // Pastikan ini AppiumDriver
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
 

  public void sortProduct(String type) {
        sortButton.click();
        if (type.equalsIgnoreCase("low")) {
            lowtoHigh.click();
        } else {
            highToLow.click();
        }
    }

 
    public void clickAddToCart() {
        firstAddToCartBtn.click();
    }

     public void scrollToCheckout() {
    driver.findElement(AppiumBy.androidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"test-CHECKOUT\"))"
    ));
    }

    public void goToCartPage() {
    btnIkonKeranjang.click();
    }

    public void goToCheckout() {
        // 1. Klik ikon keranjang di kanan atas (Wajib!)
    driver.findElement(AppiumBy.accessibilityId("test-Cart")).click();
    
    // 2. Beri jeda 1-2 detik atau tunggu sampai halaman cart muncul
    // Karena transisi halaman butuh waktu
    
    // 3. Cari tombol CHECKOUT (Gunakan scroll jika tidak terlihat)
    try {
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"test-CHECKOUT\"))"
        )).click();
    } catch (Exception e) {
        // Jika tidak butuh scroll, langsung klik
        driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT")).click();
    }
    }
   
   
}
