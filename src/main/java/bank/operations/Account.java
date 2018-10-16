package bank.operations;

import java.util.ArrayList;

public abstract class Account {

// -- Fields	
	
	// The number associated with an account
	public int accountNum;

	// The username associated with an account
	public StringBuilder username;

	// The password for an account
	public StringBuilder password;

	// The names of the people associated with an account
	public ArrayList<StringBuilder> accountHolders;

	// The current balance held within an account
	public float accountBal;

	// Establishes whether an account is active and available for use
	public boolean approved;

	
//-- Methods

	// Fills in a complete account with all its fields
	public void makeAccount(int accountNum, StringBuilder username, StringBuilder password,
			ArrayList<StringBuilder> accountHolders, float accountBal, boolean approved) {

		this.accountNum = accountNum;
		this.username = username;
		this.password = password;
		this.accountHolders = accountHolders;
		this.accountBal = accountBal;
		this.approved = approved;
	}
	
	// Checks the current accounts balance
	public void checkBal() {

	}

	// Adds a specified amount of money to the current account
	public void desposit(float money) {

	}
	
	// Removes a specified amount of money from the current account and adds it to another account
	public void transfer(Account account, float money) {
		
	}

	// Removes a specified amount of money from the current account
	public void withdraw(float money) {

	}

	// Sets whether or not an account is approved by a bank employee or admin
	public void approval(StringBuilder approval) {

	}

}
