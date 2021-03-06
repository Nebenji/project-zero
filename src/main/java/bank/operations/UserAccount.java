package bank.operations;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserAccount extends Account implements Serializable {

//-- Fields	

	private static Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private int id;
	private Customer customer;
	private StringBuilder type;
	private Account account;
	private StringBuilder username;
	private StringBuilder password;
	public int accountNum;
	public float accountBal;
	public ArrayList<StringBuilder> accountHolders;

//-- Constructors

	public UserAccount() {

		super();
		super.makeAccount(0, null, null, null, 0f, false);

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

		System.out.printf("Your current account balance is $" + "%.2f" + "\n", this.accountBal);
		logger.traceExit();

	}

	@Override
	public void desposit(float money) {

		logger.traceEntry("Deposited ${}", money);
		if (money < 0) {

			System.out.println("Please enter a valid amount!");

		} else {

			this.accountBal += money;
			System.out.printf("You deposited $" + "%.2f" + " into your account!%n", money);
			logger.traceExit();

		}

	}

	public void transfer(UserAccount account, float money) {

		logger.traceEntry("Transferred ${} to {}", money, account.accountHolders);
		if (this.accountBal <= 0) {

			System.out.println("Insufficient Funds in Account!");

		} else if (money < 0) {

			System.out.println("Please enter a valid amount!");

		} else if (this.accountBal < money) {

			System.out.println("Insufficient Funds in Account!");

		} else {
		
		this.accountBal -= money;
		account.accountBal += money;
		System.out.printf("You tranferred $" + "%.2f" + " to " + account.accountHolders + "!%n", money);
		logger.traceExit();
		
		}

	}

	@Override
	public void withdraw(float money) {

		logger.traceEntry("Withdrew ${}", money);
		if (this.accountBal <= 0) {

			System.out.println("Insufficient Funds in Account!");

		} else if (money < 0) {

			System.out.println("Please enter a valid amount!");

		} else if (this.accountBal < money) {

			System.out.println("Insufficient Funds in Account!");

		} else {

			this.accountBal -= money;
			System.out.printf("You withdrew $" + "%.2f" + " from your account!%n", money);
			logger.traceExit();

		}

	}
	
	public int getId() {
		
		return id;
		
	}
	
	public void setId(int id) {
		
		this.id = id;
		
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
	
	@Override
	public String toString() {
		return "UserAccount [Account Holders=" + this.accountHolders + ", account=" + this.accountNum
				+ ", username=" + username + ", password=" + "********" + "]";
	}

}