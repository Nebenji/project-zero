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

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Bank bank = new Bank();
		File file = new File("./bank.ser");
		SaverLoader saveLoad = new SaverLoader();

		bank = saveLoad.load(bank, file);

		while (true) {

			System.out.println("Welcome to the Bank!");
			System.out.println("What would you like to do?");
			System.out.println("1. Login");
			System.out.println("2. Create an account");
			System.out.println("0. Exit application");

			int option;
			try {

				option = scanner.nextInt();

			} catch (InputMismatchException e) {

				System.out.println("Please input a valid option!");
				scanner.reset();
				scanner = new Scanner(System.in);
				continue;

			}
			if (option != 1 & option != 2 & option != 0) {

				System.out.println("Please input a valid option!");
				continue;

			}
			switch (option) {

			case 1:
				// Goes to the Login Screen
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
					break;
				}

			case 2:
				// Goes to the Create Account Screen
				System.out.println("Welcome to the Create an Account Screen!");
				UserAccount create;
				boolean inUse;
				ArrayList<UserAccount> accts = bank.getAccounts();
				StringBuilder userName;
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

					} else if (!inUse) {

						StringBuilder passWord;
						// Creates password
						System.out.println("Choose a Password!");
						passWord = new StringBuilder(scanner.nextLine());
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
							switch (option) {
								case 1:
									System.out.println("Enter First Account Holder's First Name");
									create.setCustomer(new Customer());
									create.getCustomer().setFirstName(new StringBuilder(scanner.nextLine()));
									scanner.nextLine();
									System.out.println("Enter First Account Holder's Last Name");
									create.getCustomer().setLastName(new StringBuilder(scanner.nextLine()));
									holders.add(new StringBuilder(create.getCustomer().getFirstName().toString() + " " + 
											create.getCustomer().getLastName().toString()));
									String name = "";
									System.out.println("Enter Second Account Holder's First Name");
									name = scanner.nextLine();
									scanner.nextLine();
									System.out.println("Enter Second Account Holder's Last Name");
									name = name + " " + scanner.nextLine();
									holders.add(new StringBuilder(name));
									done = true;
									break;
									
								case 2:
									System.out.println("Enter Account Holder's First Name");
									create.setCustomer(new Customer());
									create.getCustomer().setFirstName(new StringBuilder(scanner.nextLine()));
									scanner.nextLine();
									System.out.println("Enter First Account Holder's Last Name");
									create.getCustomer().setLastName(new StringBuilder(scanner.nextLine()));
									holders.add(new StringBuilder(create.getCustomer().getFirstName().toString() + " " + 
																	create.getCustomer().getLastName().toString()));
									done = true;
									break;
									
							}
							
						}

						create.accountNum = 1000000 + accts.size();
						create.setUsername(userName); 
						create.setPassword(passWord);
						create.accountBal = 0;
						create.approved = false;
						accts.add(create);
						bank.setAccounts(accts);
						file = new File("bank.ser");
						saveLoad.save(bank, file);
						saveLoad.load(bank, file);
						break;

					}

				}

				break;

			case 0:
				System.out.println("Thank you for Banking with us!");
				System.exit(0);

			}

		}

	}

}
