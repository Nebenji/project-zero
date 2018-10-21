package bank.interactions;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import bank.operations.Bank;
import bank.operations.Customer;
import bank.operations.UserAccount;
import bank.statemaintainence.SaverLoader;

public class CreateAccount {

	protected StringBuilder userName;
	protected StringBuilder passWord;

	public void createAccount(Bank bank, File file, Scanner scanner, SaverLoader saveLoad, int option) {

		System.out.println("Welcome to the Create an Account Screen!");
		UserAccount create = new UserAccount();
		ArrayList<UserAccount> accts = bank.getAccounts();

		this.createUsername(accts, scanner);

		this.createPassword(scanner);

		create = this.createJointAccount(create, scanner, option);

		create.accountNum = 1000000 + accts.size();
		create.setUsername(userName);
		create.setPassword(passWord);
		create.accountBal = 0;
		create.approved = false;
		System.out.println(create);
		accts.add(create);
		bank.setAccounts(accts);
		file = new File("bank.ser");
		saveLoad.save(bank, file);
		saveLoad.load(bank, file);

	}


	public void createUsername(ArrayList<UserAccount> accts, Scanner scanner) {

		// StringBuilder userName;
		boolean inUse;
		while (true) {
			// Creates username
			inUse = false;
			System.out.println("Please Create a Username!");
			scanner.nextLine();
			userName = new StringBuilder(scanner.nextLine());
			for (UserAccount i : accts) {

				StringBuilder use = i.getUsername();

				if (use.toString().equals(userName.toString())) {

					System.out.println("Username already in use! Try again!");
					inUse = true;
					break;

				}

			}
			if (inUse) {

				scanner.reset();
				scanner = new Scanner(System.in);
				continue;

			} else {

				break;

			}

			
		}

	}

	public void createPassword(Scanner scanner) {

		// StringBuilder passWord;
		// Creates password
		System.out.println("Choose a Password!");
		passWord = new StringBuilder(scanner.nextLine());

	}
	
	public UserAccount createJointAccount(UserAccount create, Scanner scanner, int option) {
		
		create = new UserAccount();
		boolean done = false;
		while (!done) {

			System.out.println("Is this a joint account?");
			System.out.println("1. Yes");
			System.out.println("2. No");

			try {

				option = scanner.nextInt();

			} catch (InputMismatchException e) {

				System.out.println("Please input a valid option!");
				scanner.reset();
				scanner = new Scanner(System.in);
				continue;

			}
			if (option != 1 & option != 2) {

				System.out.println("Please input a valid option!");
				continue;

			}
			ArrayList<StringBuilder> holders = new ArrayList<StringBuilder>();
			String firstName;
			String lastName;
			switch (option) {
			case 1:
				scanner.nextLine();
				System.out.println("Enter First Account Holder's First Name");
				create.setCustomer(new Customer());
				firstName = scanner.nextLine();
				create.getCustomer().setFirstName(new StringBuilder(firstName));
				scanner.nextLine();
				System.out.println("Enter First Account Holder's Last Name");
				lastName = scanner.nextLine();
				create.getCustomer().setLastName(new StringBuilder(lastName));
				holders.add(new StringBuilder(create.getCustomer().getFirstName().toString() + " "
						+ create.getCustomer().getLastName().toString()));
				String name = "";
				System.out.println("Enter Second Account Holder's First Name");
				name = scanner.nextLine();
				System.out.println("Enter Second Account Holder's Last Name");
				name = name + " " + scanner.nextLine();
				holders.add(new StringBuilder(name));
				create.accountHolders = holders;
				done = true;
				break;

			case 2:
				scanner.nextLine();
				System.out.println("Enter Account Holder's First Name");
				create.setCustomer(new Customer());
				firstName = scanner.nextLine();
				create.getCustomer().setFirstName(new StringBuilder(firstName));
				scanner.nextLine();
				System.out.println("Enter First Account Holder's Last Name");
				lastName = scanner.nextLine();
				create.getCustomer().setLastName(new StringBuilder(lastName));
				holders.add(new StringBuilder(create.getCustomer().getFirstName().toString() + " "
						+ create.getCustomer().getLastName().toString()));
				create.accountHolders = holders;
				done = true;
				break;

			}

		}
		
		return create;
		
	}

}
