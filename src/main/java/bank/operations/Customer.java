package bank.operations;

public class Customer {

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
