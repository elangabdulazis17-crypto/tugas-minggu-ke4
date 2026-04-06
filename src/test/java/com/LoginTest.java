package com;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.appium.driver.DriverSingletown;
import com.pages.CheckOutPage;
import com.pages.LoginPage;
import com.pages.ProductPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginTest {
    private AppiumDriver driver;
    LoginPage loginPage;


    @BeforeClass
    public void setUp() {
        // Initialize the Appium driver and calculator page
        DriverSingletown.getInstance("Android");
        driver = DriverSingletown.getDriver();
        loginPage = new LoginPage(driver);
    }
    public void reset() {
       // Paksa aplikasi balik ke layar awal sebelum tiap tes
    try {
        ((AndroidDriver) driver).terminateApp("com.swaglabsmobileapp");
        ((AndroidDriver) driver).activateApp("com.swaglabsmobileapp");
    } catch (Exception e) {
        // Jika casting gagal, gunakan cara alternatif restart
        driver.quit(); 
        // Panggil method setup driver kamu lagi di sini jika perlu
    }
    loginPage = new LoginPage(driver);
}

    @Test
    public void loginTest() {
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), loginPage);
        }

    @Test
    public void testcomplate(){
        loginPage.login("standard_user","secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.sortProduct("low");

        
        productPage.clickAddToCart();
        productPage.goToCheckout();
        
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.klikTombolCheckout();


        checkOutPage.inputInformasi("Elang", "Azis", "12345");
        checkOutPage.finishCheckout();

        String actualMessage = checkOutPage.getThankYouMessage();
        Assert.assertEquals(actualMessage, "THANK YOU FOR YOU ORDER");

    }

@Test
public void testLoginWrongPassword() {
    loginPage.login("standard_user", "salah_password");
    String errorMsg = loginPage.getErrorMessage();
    Assert.assertTrue(errorMsg.contains("Username and password do not match any user in this service."), "Error message should indicate wrong credentials");
}
}
