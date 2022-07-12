package com.telran.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

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
}
