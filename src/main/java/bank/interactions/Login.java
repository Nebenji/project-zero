package bank.interactions;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import bank.operations.Bank;
import bank.operations.Customer;
import bank.operations.UserAccount;
import bank.statemaintainence.SaverLoader;

public class Login {

	public void login(File file, Bank bank, SaverLoader saveLoad, Scanner scanner, int option) {
		
		System.out.println("Welcome to the Login Screen!");
		System.out.println("Please input your username.");
		scanner.nextLine();
		StringBuilder username = new StringBuilder(scanner.nextLine());
		ArrayList<UserAccount> accounts = bank.getAccounts();
		UserAccount current = new UserAccount();
		boolean exists = false;
		for (UserAccount i : accounts) {
			StringBuilder log = i.getUsername();

			if (log.toString().equals(username.toString())) {
				exists = true;
				System.out.println("Account found!");
				break;
			}

		}
		if (exists) {
			int tries = 0;
			while (tries < 3) {
				// Ask for password
				System.out.println("Please enter your password.");
				boolean match = false;
				StringBuilder password = new StringBuilder(scanner.nextLine());
				for (UserAccount i : accounts) {
					StringBuilder pass = i.getPassword();

					if (pass.toString().equals(password.toString())) {
						match = true;
						current = i;
						break;
					}

				}
				if (match) {
					// Switch to the account management screen
					while(true) {
						
						System.out.println("Welcome " + current.getCustomer().getFirstName() 
								+ "! What would you like to do?" );
						System.out.println("1. Check Account Balance");
						System.out.println("2. Deposit");
						System.out.println("3. Withdraw");
						System.out.println("4. Transfer");
						System.out.println("0. Exit");
						try {

							option = scanner.nextInt();

						} catch (InputMismatchException e) {

							System.out.println("Please input a valid option!");
							scanner.reset();
							scanner = new Scanner(System.in);
							continue;

						}
						if (option != 1 & option != 2 & option != 3 & option != 4 & option != 0) {

							System.out.println("Please input a valid option!");
							continue;

						}
						switch(option) {
						
							case 1:
								current.checkBal();
								break;
							case 2:
								
								System.out.println("How much would you like to deposit?");
								float money;
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
								for(int i = 0; i < bank.getAccounts().size(); i++) {
									
									if(bank.getAccounts().get(i).getUsername().toString().equals(current.getUsername().toString())) {
										
										bank.getAccounts().set(i, current);
										
									}
									
								}
								file = new File("bank.ser");
								saveLoad.save(bank, file);
								break;
								
							case 3:
							
								System.out.println("How much would you like to withdraw?");
								float cash;
								scanner.nextLine();
								try {

									cash = scanner.nextFloat();

								} catch (InputMismatchException e) {

									System.out.println("Please input a valid option!");
									scanner.reset();
									scanner = new Scanner(System.in);
									continue;

								}
								current.withdraw(cash);
								for(int i = 0; i < bank.getAccounts().size(); i++) {
									
									if(bank.getAccounts().get(i).getUsername().toString().equals(current.getUsername().toString())) {
										
										bank.getAccounts().set(i, current);
										
									}
									
								}
								file = new File("bank.ser");
								saveLoad.save(bank, file);
								break;
								
							case 4:
								System.out.println("Not Implimented Yet! Sorry for the Inconvience!");
								break;
								
							case 0:
								System.out.println("Thank you for Banking with us!");
								System.exit(0);
							
						}
						
						
					}
					
					
				} else {
					tries++;
					System.out.println("Wrong Password");
				}
			}
			System.out.println("Contact Customer Service or Try Logging in again!");
			System.out.println("Goodbye!");
			System.exit(0);

		} else {
			// Account does not exist
			System.out.println("Account not found!");
			System.out.println("Create an account or Try logging in again!");

		}
		
	}

}
