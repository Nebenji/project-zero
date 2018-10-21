package bank.interactions;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import bank.operations.Bank;
import bank.statemaintainence.SaverLoader;

public class BankLauncher {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Bank bank = new Bank();
		File file = new File("./bank.ser");
		SaverLoader saveLoad = new SaverLoader();

		bank = saveLoad.load(bank, file);

		while (true) {

			System.out.println("Welcome to the Bank!");
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("1. Login");
			System.out.println("2. Create an account");
			System.out.println("0. Exit application");

			int option;
			try {

				option = scanner.nextInt();

			} catch (InputMismatchException e) {

				System.out.println("Please input a valid option!");
				System.out.println();
				scanner.reset();
				scanner = new Scanner(System.in);
				continue;

			}
			if (option != 1 & option != 2 & option != 0) {

				System.out.println("Please input a valid option!");
				System.out.println();
				continue;

			}
			switch (option) {

			case 1:
				// Goes to the Login Screen
				Login login = new Login();
				login.login(file, bank, saveLoad, scanner, option);
				break;

			case 2:
				// Goes to the Create Account Screen
				CreateAccount make = new CreateAccount();
				make.createAccount(bank, file, scanner, saveLoad, option);
				break;

			case 0:
				// Exits bank
				System.out.println("Thank you for Banking with us!");
				System.exit(0);

			}

		}

	}
	
}
