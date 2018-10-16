package bank.operations;

import java.io.Serializable;
import java.util.ArrayList;

public class Bank extends UserAccount implements Serializable {

//-- Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<UserAccount> accounts;
	
//-- Methods	
	
	@Override
	public void checkBal() {
		
		System.out.println("Your current account balance is $" + this.getAccount().accountBal);
		
	}
	
	@Override
	public void desposit(float money) {
		
		this.getAccount().accountBal += money;
		System.out.println("You deposited $" + money + " in your account!");
		
	}
	
	@Override
	public void transfer(Account account, float money) {
		
		this.getAccount().accountBal -= money;
		account.accountBal += money;
		System.out.println("You tranferred $" + money + " to " + account.accountHolders + "!");
		
	}
	
	@Override
	public void withdraw(float money) {
		
		this.getAccount().accountBal -= money;
		System.out.println("You withdrew $" + money + " from your account!");
		
	}
	
	public void createAccount(int accountNum, StringBuilder username, StringBuilder password,
			ArrayList<StringBuilder> accountHolders, float accountBal, boolean approved) {
		
		UserAccount newAcct = new UserAccount(accountNum, username, password, accountHolders, accountBal, approved);
		this.accounts.add(newAcct);
		
	}
	
	public ArrayList<UserAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<UserAccount> accounts) {
		this.accounts = accounts;
	}
	
}
