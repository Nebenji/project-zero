package bank.statemaintainence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import bank.operations.Bank;

public class SaverLoader {

	public Bank init(Bank bank, File file) {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

			bank = (Bank) ois.readObject();
			System.out.println("Bank Loaded!");
			return bank;

		} catch (IOException e) {

			e.printStackTrace();
			bank = new Bank();
			file = new File("bank.ser");

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

				oos.writeObject(bank);
				System.out.println("Bank saved!");
				return bank;

			} catch (IOException o) {

				o.printStackTrace();

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return bank;
		
	}
	
	public void save(Bank bank, File file) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

			oos.writeObject(bank);
			System.out.println("Bank saved!");

		} catch (IOException o) {

			o.printStackTrace();

		}
		
	}
	
	public Bank load(Bank bank, File file) {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

			bank = (Bank) ois.readObject();
			System.out.println("Bank Loaded!");
			return bank;

		} catch (IOException e) {

			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return bank;
		
	}
	
}
