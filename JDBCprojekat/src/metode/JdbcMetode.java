package metode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcMetode {
	
	private static final String url = "jdbc:mysql://localhost:3306/knjizare?useSSL=false";
	private static final String user= "root";
	private static final String password = "root";
	
	//otvaranje konekcije
	public static Connection uspostaviKonekciju() throws SQLException {	
		return DriverManager.getConnection(url, user, password);	
	}
	
	//zatvaranje konekcije
	public static void zatvoriKonekciju(Connection konekcija) {
		if(konekcija != null) {
			try {
				konekcija.close();
				System.out.println("Konekcija je zatvorena!!!");
			} catch (SQLException e) {
				System.out.println("Konekcija nije zatvorena!!!");
				e.printStackTrace();
			}
		}
	}
	
	
	
	

}
