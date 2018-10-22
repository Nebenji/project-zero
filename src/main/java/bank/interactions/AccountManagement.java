package bank.interactions;

import java.util.InputMismatchException;
import java.util.Scanner;

import bank.operations.Bank;
import bank.operations.UserAccount;
import bank.statemaintainence.SaverLoader;

public class AccountManagement {

	public void accountManagement(Bank bank, UserAccount current, SaverLoader saveLoad, Scanner scanner,
			int option, float money) {

		boolean loggedIn =true;
		while (loggedIn) {

			System.out.println("Welcome " + current.accountHolders + "! What would you like to do?");
			System.out.println("1. View Account Information");
			System.out.println("2. Check Account Balance");
			System.out.println("3. Deposit");
			System.out.println("4. Withdraw");
			System.out.println("5. Transfer");
			System.out.println("6. Log Out");
			System.out.println("0. Exit");
			try {

				option = scanner.nextInt();

			} catch (InputMismatchException e) {

				System.out.println("Please input a valid option!");
				scanner.reset();
				scanner = new Scanner(System.in);
				continue;

			}
			if (option != 1 & option != 2 & option != 3 & option != 4 & option != 5 & option != 6 & option != 0) {

				System.out.println("Please input a valid option!");
				continue;

			}
			switch (option) {
			
			case 1:
				System.out.println(current);
				break;

			case 2:
				current.checkBal();
				break;
			case 3:

				System.out.println("How much would you like to deposit?");
				scanner.nextLine();
				try {

					money = scanner.nextFloat();

				} catch (InputMismatchException e) {

					System.out.println("Please input a valid option!");
					scanner.reset();
					scanner = new Scanner(System.in);
					continue;

				}
				current.desposit(money);
				for (int i = 0; i < bank.getAccounts().size(); i++) {

					if (bank.getAccounts().get(i).getUsername().toString().equals(current.getUsername().toString())) {

						bank.getAccounts().set(i, current);

					}

				}
				saveLoad.update(current);
				break;

			case 4:

				System.out.println("How much would you like to withdraw?");
				scanner.nextLine();
				try {

					money = scanner.nextFloat();

				} catch (InputMismatchException e) {

					System.out.println("Please input a valid option!");
					scanner.reset();
					scanner = new Scanner(System.in);
					continue;

				}
				current.withdraw(money);
				for (int i = 0; i < bank.getAccounts().size(); i++) {

					if (bank.getAccounts().get(i).getUsername().toString().equals(current.getUsername().toString())) {

						bank.getAccounts().set(i, current);

					}

				}
				saveLoad.update(current);
				break;

			case 5:
				System.out.println("Please enter the account number you want to transfer money to?");
				scanner.nextLine();
				int acctNum = 0;
				try {

					acctNum = scanner.nextInt();

				} catch (InputMismatchException e) {

					System.out.println("Account does not exist!");
					break;

				}
				
				UserAccount account = new UserAccount();
				
				for (int i = 0; i < bank.getAccounts().size(); i++) {

					if (bank.getAccounts().get(i).accountNum == acctNum) {

						account = bank.getAccounts().get(i);

					}

				}
				System.out.println("How much do you want to transfer to " + account.accountHolders + "?");
				scanner.nextLine();
				try {

					money = scanner.nextFloat();

				} catch (InputMismatchException e) {

					System.out.println("Please input a valid option!");
					scanner.reset();
					scanner = new Scanner(System.in);
					continue;

				}
				current.transfer(account, money);
				for (int i = 0; i < bank.getAccounts().size(); i++) {

					if (bank.getAccounts().get(i).getUsername().toString().equals(current.getUsername().toString())) {

						bank.getAccounts().set(i, current);

					}

				}
				for (int i = 0; i < bank.getAccounts().size(); i++) {

					if (bank.getAccounts().get(i).getUsername().toString().equals(account.getUsername().toString())) {

						bank.getAccounts().set(i, account);

					}

				}
				saveLoad.update(current);
				saveLoad.update(account);
				break;
				
			case 6:
				System.out.println("You have successfully logged out!");
				loggedIn = false;
				break;

			case 0:
				System.out.println("Thank you for Banking with us!");
				System.exit(0);

			}

		}

	}

}
