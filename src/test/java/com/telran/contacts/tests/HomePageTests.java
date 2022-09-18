package com.telran.contacts.tests;

import org.testng.annotations.Test;


public class HomePageTests extends TestBase {

    @Test
    public void openHomePageTest() {

        System.out.println("Home Component: " + app.getHomePage().isHomeComponentPresent2());
        app.getHomePage().isHomeComponentPresent();

    }


}
