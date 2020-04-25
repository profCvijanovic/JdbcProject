package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import metode.JdbcMetode;

public class GlavnaKlasa {

	public static void main(String[] args) {
		
		Connection konekcija = null;
		try {
			konekcija = JdbcMetode.uspostaviKonekciju();
			System.out.println("Konekcija uspostavljena!!!");
		} catch (SQLException e) {
			System.out.println("Nema konekcije!!!");
			e.printStackTrace();
		}finally {
			JdbcMetode.zatvoriKonekciju(konekcija);

		}

		
		
		
		
		
		
	}

}
