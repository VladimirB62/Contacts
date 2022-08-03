package com.telran.contacts.fw;

import com.telran.contacts.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void registration() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        Assert.assertTrue(isElemententPresent(By.cssSelector(".login_login__3EHKB")));
        //fill registration form
        fillLoginRegistrationForm(new User().setEmail("hiddink1@gmail.com").setPassword("123123Aa$"));
        //click on the button Registration
        click(By.xpath("//button[contains(.,'Registration')]"));
    }

    public void registrationNegative() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        Assert.assertTrue(isElemententPresent(By.cssSelector(".login_login__3EHKB")));
        //fill registration form
        fillLoginRegistrationForm(new User().setEmail("hiddink1@gmail.com").setPassword("123123Aa$"));
        //click on the button Registration
        click(By.xpath("//button[contains(.,'Registration')]"));
    }

    public void fillLoginRegistrationForm(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
    }

    public void login() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
        fillLoginRegistrationForm(new User().setEmail("hiddink1@gmail.com").setPassword("123123Aa$"));
        click(By.xpath("//button[contains(.,'Login')]"));
    }

    public boolean isErrorPresent() {
        return isElemententPresent(By.xpath("//div[.='Login Failed with code 400']"));
    }
}
