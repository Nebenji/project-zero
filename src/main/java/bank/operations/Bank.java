package bank.operations;

import java.util.ArrayList;

public class Bank extends UserAccount {

//-- Fields
	
	private ArrayList<UserAccount> accounts;
	
//-- Methods	
	
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
		
		UserAccount newAcct = new UserAccount();
		//newAcct.acc
		//Account account = new Account(accountNum, username, password, accountHolders, accountBal, approved);
		//newAcct.setAccount(account);
		this.accounts.add(newAcct);
		
	}
	
	public ArrayList<UserAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<UserAccount> accounts) {
		this.accounts = accounts;
	}
	
}
