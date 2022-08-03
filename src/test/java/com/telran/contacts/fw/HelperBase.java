package com.telran.contacts.fw;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HelperBase {

    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver=driver;
    }

    public void clickWithAction(By save) {
        Actions actions = new Actions(driver);
        var element = driver.findElement(save);

        actions.moveToElement(element).perform();
        element.click();
    }
    public boolean isElemententPresent(By locator) {
        return driver.findElements(locator).size()>0;
    }

    public boolean isElementPresent2(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public void type(By locator, String text){
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    //assert the button Sign out displayed
    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, 20).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        }else{
            driver.switchTo().alert();
            alert.accept();
        }
        return true;
    }

    public  String takeScreenshot() {
        var tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots/screen" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp,screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshot.getAbsolutePath();
    }

}
