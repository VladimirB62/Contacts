package com.telran.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isContactCreated(String text) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement el : contacts) {
            if (el.getText().contains(text))
                return true;
        }
        return false;
    }

    public void addContact(Contact contact) {
        int i = (int) ((System.currentTimeMillis())/1000)%3600;
        click(By.xpath("//a[contains(text(),'ADD')]"));
        fillContactForm(contact, i);
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void fillContactForm(Contact contact, int i) {
        type(By.cssSelector("input:nth-child(1)"), contact.getName());
        type(By.cssSelector("input:nth-child(2)"), contact.getLastName());
        type(By.cssSelector("input:nth-child(3)"), contact.getPhone() + i);
        type(By.cssSelector("input:nth-child(4)"), contact.getEmail());
        type(By.cssSelector("input:nth-child(5)"), contact.getAddress());
        type(By.cssSelector("input:nth-child(6)"), contact.getDescription());
    }

    public int sizeOfContacts() {
        if (isElemententPresent(By.cssSelector(".contact-item_card__2SOIM"))){
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        } return 0;
    }

    public void removeContact() {
        if (!isContactListEmpty()){
            click(By.cssSelector(".contact-item_card__2SOIM"));
            click(By.xpath("//button[contains(.,'Remove')]"));
        }
    }

    public boolean isContactListEmpty() {
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }
}
