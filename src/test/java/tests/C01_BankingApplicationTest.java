package tests;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

/*
Test Scenario:
1. Navigate to the application
2. Create a new user account
3. Login with the created user
4. Add 5 different contacts
5. Assert that all contacts are properly added and displayed

 */

public class C01_BankingApplicationTest {

    @Test
    void getDriver() throws InterruptedException {

        ManagerLoginPage managerLoginPage = new ManagerLoginPage();
        CustomerManagementPage customerManagementPage = new CustomerManagementPage();
        AccountManagementPage accountManagementPage=new AccountManagementPage();
        CustomerLoginPage customerLoginPage= new CustomerLoginPage();
        TransactionPage transactionPage= new TransactionPage();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(5));
        //navigate
        Driver.getDriver().get(ConfigReader.getProperty("Bank_url"));

        //click on BankMangerLogin
        managerLoginPage.BankMangerLogin.click();

        // click on Add customer
        customerManagementPage.addCustomer.click();
        // Add 5 customers
        for (int i=1;i<=5;i++){
            customerManagementPage.firstName.sendKeys(ConfigReader.getProperty("firstname"+i));
            customerManagementPage.lastName.sendKeys(ConfigReader.getProperty("lastname"+i));
            customerManagementPage.postCode.sendKeys(ConfigReader.getProperty("postcode"+i));
            customerManagementPage.addCustomerButton.click();
            Alert alert= wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        }
        //open 5 accounts
        accountManagementPage.OpenAccountButton.click();
        for(int i=1;i<=5;i++){
            Select select =new Select(accountManagementPage.selectCustomer);
            select.selectByVisibleText(ConfigReader.getProperty("firstname"+i)+" "+ConfigReader.getProperty("lastname"+i));

            Select select1=new Select(accountManagementPage.currency);
            select1.selectByVisibleText("Dollar");

            accountManagementPage.processButton.click();
            Alert aler= wait.until(ExpectedConditions.alertIsPresent());
            aler.accept();
        }

        customerLoginPage.homeButton.click();

        wait.until(ExpectedConditions.visibilityOf(transactionPage.CustomerLogInButton));
        transactionPage.CustomerLogInButton.click();


        //Deposit 100 USD to each account
        Select select =new Select(transactionPage.selectCustomer2);
        for(int i=1;i<=5;i++){
            select.selectByVisibleText(ConfigReader.getProperty("firstname"+i)+" "+ConfigReader.getProperty("lastname"+i));
            transactionPage.LogInButton.click();
            transactionPage.depositButton.click();
            transactionPage.amount.sendKeys(ConfigReader.getProperty("amount"));
            transactionPage.SecDepositButton.click();
            //assertion
            Assert.assertEquals(transactionPage.DepositSuccessful.getText(),"Deposit Successful");
            transactionPage.logoutButton.click();
        }

        //withdrawl first customer
        select.selectByVisibleText(ConfigReader.getProperty("firstname1")+" "+ConfigReader.getProperty("lastname1"));
        transactionPage.LogInButton.click();
        transactionPage.withdrawlButton.click();
        transactionPage.amount.sendKeys(ConfigReader.getProperty("amount"));
        transactionPage.SecwithdrawlButton.click();
        WebDriverWait wait2 = new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOf(transactionPage.Transactionsuccessful));
        Assert.assertEquals(transactionPage.Transactionsuccessful.getText(),"Transaction successful");

        //assertion for balance
        String balance= transactionPage.balance.getText();
        Assert.assertEquals(balance,"0","balance amount does not equal each other");
        transactionPage.logoutButton.click();
        customerLoginPage.homeButton.click();

        //Delete customers
        managerLoginPage.BankMangerLogin.click();
        customerManagementPage.CustomersButton.click();
        int counter=10;
        for(int i=1;i<=5;i++){
         customerManagementPage.search.sendKeys((ConfigReader.getProperty("firstname"+i)));
         Thread.sleep(3000);
         customerManagementPage.deleteCustomer.click();
         customerManagementPage.search.clear();
         counter--;
        }
        //account deletion
        Assert.assertEquals(counter,5,"The new customers created are not deleted");
        Driver.CloseDriver();
    }
}