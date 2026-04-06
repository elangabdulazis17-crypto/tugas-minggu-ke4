package com;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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

     @BeforeMethod
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
    @AfterClass
    public void tearDown() {
        // Close the Appium driver
        DriverSingletown.closeObjectInstance();

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
    public void COHigh(){
        loginPage.login("standard_user","secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.sortProduct("high");

        productPage.clickAddToCart();
        productPage.goToCheckout();

        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.klikTombolCheckout();

        checkOutPage.inputInformasi("elang", "Abdul Azis", "24567");
        checkOutPage.finishCheckout();

        String actualMassage = checkOutPage.getThankYouMessage();
        Assert.assertEquals("THANK YOU FOR YOU ORDER", actualMassage);

    }


@Test
public void testLoginWrongPassword() {
   loginPage.login("locked_out_user", "salah_password");
    
    String errorMsg = loginPage.getErrorMessage();
    
    // Pakai assertTrue dengan bantuan toLowerCase() supaya gak sensitif huruf besar/kecil
    Assert.assertTrue(errorMsg.toLowerCase().contains("match"), 
        "Pesan error salah! Munculnya: " + errorMsg);
    }

    @Test
    public void testCheckoutWithEmptyForm() {

    ProductPage productPage = new ProductPage(driver);

    loginPage.login("problem_user", "secret_sauce");

    // 1. Flow sampai ke halaman checkout (asumsi sudah ada barang di cart)
    productPage.clickAddToCart();
    productPage.goToCheckout();
    
    CheckOutPage checkoutPage = new CheckOutPage(driver);
    checkoutPage.klikTombolCheckout(); // Masuk ke halaman "Your Information"

    // 2. Langsung klik Continue tanpa isi data
    checkoutPage.clickContinue();

    // 3. Validasi pesan error
    String error = checkoutPage.getErrorMessage();
    System.out.println("Error yang muncul: " + error);
    
    // Asersi: Pastikan errornya spesifik soal First Name
    Assert.assertTrue(error.contains("First Name is required"), 
        "Pesan error salah! Munculnya: " + error);
    }
}
