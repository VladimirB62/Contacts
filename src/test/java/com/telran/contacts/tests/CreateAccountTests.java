package com.telran.contacts.tests;

import com.telran.contacts.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    //precondition :user should be logged out
    @BeforeMethod
    public void  ensurePrecondition() {
        if(!app.getHeader().isLoginLinkPresent()){
            app.getHeader().clickOnSignOutButton();
        }
    }
    //click on the link Login
    @Test(enabled = false)
    public void registrationPositiveTest(){
        app.getUser().registration();
        //assert the button Sign out displayed
        Assert.assertTrue(app.getHeader().isSignOutButtonPresent());
    }

    @Test
    public void registrationNegativeTestWithInvalidEmail() {
        app.getUser().registrationNegative();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }


}
