package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;
import utilities.Driver;

public class AccountManagementPage {

    public AccountManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
        @FindBy (xpath = "//button[@ng-class='btnClass2']")
         public WebElement OpenAccountButton;


    @FindBy (id="userSelect")
    public WebElement selectCustomer;

    @FindBy (id="currency")
    public WebElement currency;

    @FindBy (xpath="//button[@type='submit']")
    public WebElement processButton;



    }