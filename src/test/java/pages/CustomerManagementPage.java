package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CustomerManagementPage {

    public CustomerManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
        @FindBy(xpath = "//button[contains(text(),'Add Customer')]")
                public WebElement addCustomer;

    @FindBy(xpath = "//input[@ng-model='fName']")
    public  WebElement firstName;


    @FindBy(xpath = "//input[@ng-model='lName']")
    public  WebElement lastName;


    @FindBy(xpath = "//input[@ng-model='postCd']")
    public  WebElement postCode;


    @FindBy(xpath = "//button[@type='submit']")
    public  WebElement addCustomerButton;

    @FindBy(xpath = "//button[contains(text(),'Customers')]")
    public  WebElement CustomersButton;

    @FindBy(xpath = "//input[@ng-model='searchCustomer']")
    public  WebElement search;

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    public  WebElement deleteCustomer;




}



