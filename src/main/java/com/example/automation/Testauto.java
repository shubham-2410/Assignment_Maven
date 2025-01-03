package com.example.automation;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testauto {

    @Test
    public void testLogin() {
        // Set up the Chrome WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Configure ChromeOptions for specific testing needs
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the login page
            driver.get("https://practicetestautomation.com/practice-test-login/");

            // Locate and fill the username field
            WebElement usernameField = driver.findElement(By.id("username"));
            usernameField.sendKeys("student");

            // Locate and fill the password field
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("Password123");

            // Locate and click the login button
            WebElement loginButton = driver.findElement(By.id("submit"));
            loginButton.click();

            // Validate the title of the resulting page
            String expectedTitle = "Logged In Successfully | Practice Test Automation";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);

        } finally {
            // Always close the browser at the end of the test
            driver.quit();
        }
    }
}
