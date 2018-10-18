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
