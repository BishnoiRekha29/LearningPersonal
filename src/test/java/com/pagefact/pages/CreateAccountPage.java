package com.pagefact.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class CreateAccountPage
{
    /* Local Selenium Grid */
    /* private WebDriver driver; */

    /* Remote Selenium Grid */
    private RemoteWebDriver driver;

    @FindBy(xpath = "//input[@name='name']")
    WebElement full_name;

    @FindBy(css = "[name='email']")
    WebElement org_email;

    @FindBy(xpath = "//input[@id='userpassword']")
    WebElement acc_password;

    @FindBy(xpath = "//input[@name='phone']")
    WebElement phone_number;

    @FindBy(css = "[name='designation']")
    WebElement designation;

    @FindBy(css = "[name='org_size']")
    WebElement org_size;

    @FindBy(xpath = "//samp[@class='customcheckbox']")
    WebElement check_box;

    @FindBy(css = ".btn")
    WebElement create_account_button;

    @FindBy(css = "#recaptcha-verify-button")
    @CacheLookup
    WebElement recaptcha_button;
    public CreateAccountPage(RemoteWebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void setName(String fullname)
    {
        full_name.clear();
        full_name.sendKeys(fullname);
    }
    public void setEmail(String email)
    {
        org_email.clear();
        org_email.sendKeys(email);
    }
    public void setAccount_password(String pwd)
    {
        acc_password.clear();
        acc_password.sendKeys(pwd);
    }
    public void setDesignation(String desig_name) {
        Select dropdown;
        dropdown = new Select(designation);
        dropdown.selectByValue(desig_name);
    }
    public void setCompanySize(String comp_size) {
        Select dropdown;
        dropdown = new Select(org_size);
        dropdown.selectByValue(comp_size);
    }
    public void setPhone_number (String phonenum)
    {
        phone_number.clear();
        phone_number.sendKeys(phonenum);
    }
    public void clickAcceptTCButton()
    {
        check_box.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        create_account_button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public boolean isVerifyPageOpen()
    {
        String expected_title = "Verify Your Email Address";
        String win_title = driver.getTitle();
        boolean isWinFound = win_title.indexOf(expected_title) != -1? true: false;
        return isWinFound;
    }
}