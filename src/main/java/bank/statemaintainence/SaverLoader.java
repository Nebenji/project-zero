package bank.statemaintainence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bank.operations.Bank;
import bank.operations.UserAccount;

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
				System.out.println();
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
			System.out.println();
			System.out.println();
			return bank;

		} catch (IOException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return bank;

	}

	private UserAccount extractUser(ResultSet rs) throws SQLException {

		UserAccount user = new UserAccount();

		user.setId(rs.getInt("id"));
		user.setUsername(new StringBuilder(rs.getString("user_name")));
		user.setPassword(new StringBuilder(rs.getString("pass_word")));
		user.accountNum = (rs.getInt("account_number"));
		user.accountBal = (rs.getFloat("account_balance"));
		Array ar = rs.getArray("account_holders");
		String[] arr = (String[]) ar.getArray();
		ArrayList<StringBuilder> ua = new ArrayList<StringBuilder>();
		for (String i : arr) {

			ua.add(new StringBuilder(i));

		}
		user.accountHolders = ua;

		return user;

	}

	public void update(UserAccount user) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "UPDATE user_accounts SET user_name = ?, pass_word = ?, account_number = ?, account_balance = ?, account_holders = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getUsername().toString());
			ps.setString(2, user.getPassword().toString());
			ps.setInt(3, user.accountNum);
			ps.setFloat(4, user.accountBal);
			Object[] array = new Object[user.accountHolders.size()];
			array = user.accountHolders.toArray();
			ps.setArray(5, conn.createArrayOf("varchar", array));
			ps.setInt(6, user.getId());
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public UserAccount createUser(UserAccount user) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "INSERT INTO user_accounts (user_name, pass_word, account_number, account_balance, account_holders) VALUES (?, ?, ?, ?, ?) RETURNING id";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getUsername().toString());
			ps.setString(2, user.getPassword().toString());
			ps.setInt(3, user.accountNum);
			ps.setFloat(4, user.accountBal);
			Object[] array = new Object[user.accountHolders.size()];
			array = user.accountHolders.toArray();
			ps.setArray(5, conn.createArrayOf("varchar", array));

			// ResultSet starts before the first result, so we need to call next at least
			// once
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setId(rs.getInt("id"));
			return user;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;

		}

	}

	public ArrayList<UserAccount> getUserAccounts() {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM user_accounts";
			PreparedStatement statement = conn.prepareStatement(query);

			ResultSet rs = statement.executeQuery();
			ArrayList<UserAccount> users = new ArrayList<>();
			while (rs.next()) {
				UserAccount user = extractUser(rs);

				users.add(user);

			}

			return users;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;

		}

	}

}
