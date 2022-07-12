package com.telran.contacts;

import org.testng.annotations.Test;


public class HomePageTests extends TestBase{

    @Test
    public void openHomePageTest() {
        System.out.println("Site opened!!!");
        //verify to displayed Home Component form
 //       driver.findElement(By.cssSelector("div:nth-child(2)>div>div"));
 //       isComponentFormPresent();
 //      System.out.println("Component Form:" + isComponentFormPresent());
        //isElemententPresent(By.cssSelector("div:nth-child(2)>div>div"));
        app.isComponentFormPresent();

    }


}
