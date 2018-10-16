package bank.operations;

import java.io.Serializable;

public class Customer implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//-- Fields	
	
	// Customer's first name
	private StringBuilder firstName;
	// Customer's last name
	private StringBuilder lastName;
	
//-- Methods
	
	public StringBuilder getFirstName() {
		return firstName;
	}
	public void setFirstName(StringBuilder firstName) {
		this.firstName = firstName;
	}
	public StringBuilder getLastName() {
		return lastName;
	}
	public void setLastName(StringBuilder lastName) {
		this.lastName = lastName;
	}
		
}
