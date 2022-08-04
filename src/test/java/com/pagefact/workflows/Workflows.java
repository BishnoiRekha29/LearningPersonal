package com.pagefact.workflows;

import com.pagefact.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class Workflows extends BasePage {
    public Workflows(WebDriver driver) {
        super(driver);
    }

    public void workflowLoginpage()
    {
        HomePage().loadPage();
        HomePage().enterEmail("abc.123@gmail.com");
        HomePage().clickOnStartTestingButton();
        SignUpPage().isLTPageOpen();
    }
}
