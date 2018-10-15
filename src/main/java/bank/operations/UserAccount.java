package bank.operations;

public class UserAccount extends Account {

//-- Fields	
	
	private Customer customer;
	private Account account;
	private StringBuilder username;
	private StringBuilder password;

//-- Methods	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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