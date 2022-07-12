package com.telran.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    //precondition :user should be logged out
    @BeforeMethod
    public void  ensurePrecondition() {
        if(!app.getHeader().isLoginLinkPresent()){
            app.getHeader().clickOnSignOutButton();
        }
    }



    @Test
    public void loginUserPositiveTest(){
        //click on login link
        app.getUser().login();
        //assert the button Sign Out
        Assert.assertTrue(app.getHeader().isSignOutButtonPresent());

    }

}
