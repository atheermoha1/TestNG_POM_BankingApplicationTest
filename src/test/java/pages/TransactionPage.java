package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class TransactionPage {
    public TransactionPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy (id="userSelect")
    public WebElement selectCustomer2;

    @FindBy(xpath = "//button[contains(text(),'Customer Login')]")
    public WebElement CustomerLogInButton;


    @FindBy(xpath = "//button[contains(text(),'Login')]")
    public WebElement LogInButton;

    @FindBy(xpath = "//button[contains(text(),'Deposit')]")
    public WebElement depositButton;


    @FindBy (xpath="//input[@ng-model='amount']")
    public WebElement amount;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement SecDepositButton;

    @FindBy(xpath = "//button[@class='btn logout']")
    public WebElement logoutButton;

    @FindBy(xpath = "//button[contains(text(),'Withdrawl')]")
    public WebElement withdrawlButton;

    @FindBy(xpath = "//button[normalize-space(text())='Withdraw']")
    public WebElement SecwithdrawlButton;


    @FindBy(xpath = "//span[contains(text(),'Deposit Successful')]")
    public  WebElement DepositSuccessful;

    @FindBy(xpath = "//span[contains(@class,'error') and text()='Transaction successful']")
    public  WebElement Transactionsuccessful;

    @FindBy(xpath = "//strong[@class='ng-binding'][2]")
    public  WebElement balance;


}
