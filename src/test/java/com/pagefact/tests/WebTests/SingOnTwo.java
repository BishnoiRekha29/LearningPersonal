package com.pagefact.tests.WebTests;

import com.pagefact.pages.BasePage;
import com.pagefact.tests.WebTests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class SingOnTwo extends BaseTest {


    @Test
    public void test_signupOnLambdaTest2()
    {  WebDriver driver=getDriver();
        BasePage pages = new BasePage(driver);
        pages.WorkFlows().workflowLoginpage();

        System.out.println("Page Factory demo complete\n");
        driver.quit();
    }


}