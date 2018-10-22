package bank.interactions;

import java.util.ArrayList;
import java.util.Scanner;

import bank.operations.Bank;
import bank.operations.UserAccount;
import bank.statemaintainence.SaverLoader;

public class Login {

	protected float money;
	protected boolean exists;
	protected boolean match;
	protected UserAccount current;

	public void login(Bank bank, SaverLoader saveLoad, Scanner scanner, int option) {

		System.out.println("Welcome to the Login Screen!");
		ArrayList<UserAccount> accounts = bank.getAccounts();

		this.inputUsername(accounts, scanner);

		if (exists) {
			int tries = 0;
			boolean loggedOut = false;
			while (tries < 3) {

				this.inputPassword(accounts, scanner);

				if (match) {
					// Switch to the account management screen
					AccountManagement manage = new AccountManagement();
					manage.accountManagement(bank, current, saveLoad, scanner, option, money);
					loggedOut = true;
					break;

				} else {
					tries++;
					System.out.println("Wrong Password");
				}
			}
			if(loggedOut) {
				
				
				
			}
			else {
				
			System.out.println("Contact Customer Service or Try Logging in again!");
			
			}

		} else {
			// Account does not exist
			System.out.println("Account not found!");
			System.out.println("Create an account or Try logging in again!");

		}

	}

	public void inputUsername(ArrayList<UserAccount> accounts, Scanner scanner) {

		System.out.println("Please input your username.");
		scanner.nextLine();
		StringBuilder username = new StringBuilder(scanner.nextLine());
		current = new UserAccount();
		exists = false;
		for (UserAccount i : accounts) {
			StringBuilder log = i.getUsername();

			if (log.toString().equals(username.toString())) {
				exists = true;
				System.out.println("Account found!");
				break;
			}

		}

	}

	public void inputPassword(ArrayList<UserAccount> accounts, Scanner scanner) {

		// Ask for password
		System.out.println("Please enter your password.");
		match = false;
		StringBuilder password = new StringBuilder(scanner.nextLine());
		for (UserAccount i : accounts) {
			StringBuilder pass = i.getPassword();

			if (pass.toString().equals(password.toString())) {
				match = true;
				current = i;
				break;
			}

		}

	}

}
