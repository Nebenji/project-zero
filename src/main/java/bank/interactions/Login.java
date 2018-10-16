package bank.interactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import bank.operations.Bank;
import bank.operations.Customer;
import bank.operations.UserAccount;

public class Login {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Bank bank = new Bank();
		File file = new File("./bank.ser");

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

			bank = (Bank) ois.readObject();

		} catch (IOException e) {

			e.printStackTrace();
			bank = new Bank();
			file = new File("bank.ser");

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

				oos.writeObject(bank);
				System.out.println("Bank saved!");

			} catch (IOException o) {

				o.printStackTrace();

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

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
				boolean exists = false;
				for (UserAccount i : accounts) {
					if (i.getUsername().equals(username)) {
						exists = true;
						break;
					}

				}
				if (exists) {
					int tries = 0;
					while (tries < 3) {
						// Ask for password
						System.out.println("Account found!");
						System.out.println("Please enter your password.");
						boolean match = false;
						StringBuilder password = new StringBuilder(scanner.nextLine());
						for (UserAccount i : accounts) {
							if (i.getPassword().equals(password)) {
								match = true;
								break;
							}

						}
						if (match) {
							// Switch to the account management screen
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
				System.out.println("Please Create a Username!");
				UserAccount create;
				boolean inUse = false;
				while (true) {
					// Creates username
					scanner.nextLine();
					StringBuilder userName = new StringBuilder(scanner.nextLine());
					ArrayList<UserAccount> accts = new ArrayList<UserAccount>();
							//bank.getAccounts();
//					for (UserAccount i : accts) {
//						
//						if(i.getAccount().username.equals(userName)) {
//							
//							System.out.println("Username already in use! Try again!");
//							inUse = true;
//							break;
//							
//						}
//
//					}
					if(inUse) {
						
						continue;
						
					}
					else if(!inUse) {
						
						StringBuilder passWord;
						// Creates password
						System.out.println("Choose a Password!");
						passWord = new StringBuilder(scanner.nextLine());
						ArrayList<StringBuilder> blah = new ArrayList<StringBuilder>();
						blah.add(new StringBuilder("Ben Lawson"));
						create = new UserAccount(100000, userName, passWord, blah, 5000000, true);
						create.setCustomer(new Customer());
						create.getCustomer().setFirstName(new StringBuilder("Ben"));
						create.getCustomer().setLastName(new StringBuilder("Lawson"));
						create.setType(new StringBuilder("Admin"));
						accts.add(create);
						bank.setAccounts(accts);
						file = new File("bank.ser");

						try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

							oos.writeObject(bank);
							System.out.println("Bank saved!");

						} catch (IOException o) {

							o.printStackTrace();

						}
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
