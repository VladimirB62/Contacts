package com.telran.contacts.tests;

import com.telran.contacts.fw.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {


    protected ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void SetUp() {
        app.init();
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void  stertTest(Method m, Object[] p) {
        logger.info("Test start " + m.getName() + "with data: " + Arrays.asList(p));
    }

    @AfterMethod
    public  void stopTest(ITestResult result) {
        if (result.isSuccess()){
            logger.info("PASSED: test method " +result.getMethod().getMethodName());
        }else{
            logger.info("FAILED: test method " +result.getMethod().getMethodName() + "Screenshot: " + app.getUser().takeScreenshot());
        }
    }

}
