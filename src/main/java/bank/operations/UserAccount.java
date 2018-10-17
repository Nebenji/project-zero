package bank.operations;

import java.io.Serializable;
import java.util.ArrayList;

public class UserAccount extends Account implements Serializable {

//-- Fields	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private StringBuilder type;
	private Account account;
	private StringBuilder username;
	private StringBuilder password;

//-- Constructors
	
	public UserAccount() {
		
		super();
		
	}
	
	
	public UserAccount(int accountNum, StringBuilder username, StringBuilder password,
			ArrayList<StringBuilder> accountHolders, float accountBal, boolean approved) {
			
		 super();
		 super.makeAccount(accountNum, username, password, accountHolders, accountBal, approved);
		 this.username = username;
		 this.password = password;
		 
	}
	
//-- Methods
	
	@Override
	public void checkBal() {
		
		System.out.println("Your current account balance is $" + this.accountBal);
		
	}
	
	@Override
	public void desposit(float money) {
		
		this.accountBal += money;
		System.out.println("You deposited $" + money + " in your account!");
		
	}
	
	@Override
	public void transfer(Account account, float money) {
		
		this.accountBal -= money;
		account.accountBal += money;
		System.out.println("You tranferred $" + money + " to " + account.accountHolders + "!");
		
	}
	
	@Override
	public void withdraw(float money) {
		
		this.accountBal -= money;
		System.out.println("You withdrew $" + money + " from your account!");
		
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public StringBuilder getType() {
		return type;
	}


	public void setType(StringBuilder type) {
		this.type = type;
	}


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public StringBuilder getUsername() {
		return username;
	}

	public void setUsername(StringBuilder username) {
		this.username = username;
	}

	public StringBuilder getPassword() {
		return password;
	}

	public void setPassword(StringBuilder password) {
		this.password = password;
	}

}