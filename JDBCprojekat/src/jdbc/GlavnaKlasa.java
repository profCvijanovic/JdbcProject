package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import metode.JdbcMetode;

public class GlavnaKlasa {

	public static void main(String[] args) {
		
		String drzava= "Srbija";
		String grad = "Novi Sad";
		String ulica= "Jove Zmaja 10";
		
		Connection konekcija = null;
		try {
			konekcija = JdbcMetode.uspostaviKonekciju();
			System.out.println("Konekcija uspostavljena!!!");
			//insert statement
			String query = "INSERT INTO adrese VALUES(null,'"+ drzava +
													"','"+ grad +
													"','"+ ulica +"')";
			Statement statement = konekcija.createStatement();
			statement.execute(query);
			System.out.println("Uspesno ste uneli podatak u bazu!");
		} catch (SQLException e) {
			System.out.println("Nema konekcije!!!");
			e.printStackTrace();
		}finally {
			JdbcMetode.zatvoriKonekciju(konekcija);

		}

		
		
		
		
		
		
	}

}
