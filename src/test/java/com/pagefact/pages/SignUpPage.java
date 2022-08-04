package com.pagefact.pages;

import com.DriverFactory.Wrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignUpPage extends BasePage
{
    @FindBy(xpath = "//div[contains(@class,'loginArea')]//img[contains(@class,'mx-auto block')]")
    private WebElement form_title;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailTxt;

    @FindBy(css = ".btn")
    private WebElement SignUpButton;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }


    public void isLTPageOpen()
    {
        wrapper.waitForPageLoadToComplete();
        Assert.assertTrue(wrapper.getAttribute(form_title,"alt").contains("LambdaTest"));
    }

}