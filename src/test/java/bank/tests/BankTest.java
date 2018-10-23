package bank.tests;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Test;

import bank.interactions.Login;
import bank.operations.Bank;
import bank.operations.UserAccount;
import bank.statemaintainence.SaverLoader;

/***
 * 
 * This class tests Bank
 * @author Ben
 *
 */

public class BankTest {

	@Test
	public void bankTest() throws Exception{
		
		Bank bank = new Bank();
		SaverLoader saveLoad = new SaverLoader();
		bank.setAccounts(saveLoad.getUserAccounts());
		Login login = new Login();
		Scanner scanner = new Scanner(System.in);
		login.inputUsername(bank.getAccounts(), scanner);
		login.inputPassword(bank.getAccounts(), scanner);
		UserAccount user = new UserAccount();
		String username = login.getCurrent().getUsername() + "";
		user = login.getCurrent();
		
		assertEquals("The username Nebenji is in the database", "Nebenji",  username);
		user.desposit(500.00f);
		assertEquals(6055.55f, user.accountBal, 0.0);
		user.withdraw(500.00f);
		assertEquals(5555.55f, user.accountBal, 0.0);
		
		
	}
	
}
