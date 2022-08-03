package com.telran.contacts.tests;

import com.telran.contacts.models.Contact;
import com.telran.contacts.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getHeader().isLoginLinkPresent()) {
            app.getHeader().clickOnSignOutButton();
        }else{
            app.getUser().login();
        }
    }

    @Test
    public void addContactPositiveTest() {
        app.getContact().addContact(new Contact().setName("Guss").setLastName("Hiddink").setPhone("1234567").setEmail("hiddink@gmail.com").setAddress("Amsterdam").setDescription("coach"));
        Assert.assertTrue(app.getContact().isContactCreated("Guss"));
    }



    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String lastName, String phone, String email,
                                                       String address, String description){
        app.getContact().click(By.xpath("//a[contains(text(),'ADD')]"));
        app.getContact().fillContactForm(new Contact().setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        app.getContact().clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
    }

    @Test(dataProvider = "addNewContactFromCSV", dataProviderClass = DataProviders.class)
        public void addContactPositiveTestFromCSV(Contact contact){
            app.getContact().click(By.xpath("//a[contains(text(),'ADD')]"));
            app.getContact().fillContactForm(contact);
            app.getContact().clickWithAction(By.cssSelector(".add_form__2rsm2 button"));

        }


    @AfterMethod
    public void postCondition(){
        app.getContact().removeContact();
    }

}
