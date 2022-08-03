package com.telran.contacts.tests;

import com.telran.contacts.models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        if (!app.getHeader().isLoginLinkPresent()){
            app.getHeader().clickOnSignOutButton();
        }else{
            app.getUser().login();
            app.getContact().addContact(new Contact().setName("Guss").setLastName("Hiddink").setPhone("1234567")
                    .setEmail("hiddink@gmail.com").setAddress("Amsterdam").setDescription("coach"));
        }
    }

    @Test
    public void removeContactTest()  {
        app.getUser().pause(2000);
        int sizeBefore = app.getContact().sizeOfContacts();
        System.out.println(sizeBefore);
        app.getContact().removeContact();
        app.getUser().pause(2000);
        int sizeAfter = app.getContact().sizeOfContacts();
        System.out.println(sizeAfter);
        Assert.assertEquals(sizeAfter,sizeBefore-1);
    }

}
