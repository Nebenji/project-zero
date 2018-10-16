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
//		 this.account.accountNum = accountNum;
//		 this.account.accountBal = accountBal;
//		 this.account.username = username;
//		 this.username = username;
//		 this.account.password = password;
//		 this.password = password;
//		 this.account.accountHolders = accountHolders;
//		 this.account.approved = approved;
		 
	}
	
//-- Methods
	
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