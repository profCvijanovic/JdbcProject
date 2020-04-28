package metode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	//zatvaranje prepared statement-a
	public static void zatvoriPreparedStatement(PreparedStatement pst) {
		if(pst != null) {
			try {
				pst.close();
				System.out.println("Prepared statement je zatvoren!!!");
			} catch (SQLException e) {
				System.out.println("Prepared statement nije zatvoren!!!");
				e.printStackTrace();
			}
		}
	}
	
	//metoda za ubacivanje adresa
	public static void ubaciAdresu(String drzava, String grad, String ulica) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		try {
			konekcija = uspostaviKonekciju();
			System.out.println("Konekcija uspostavljena!!!");
			//insert query for prepared statement
			String query = "INSERT INTO adrese VALUES(null,?,?,?)";
			// create prepared statement
			pst = konekcija.prepareStatement(query);
			// nas pst setuje parametre (znakove pitanja)
			pst.setString(1,drzava);
			pst.setString(2,grad);
			pst.setString(3,ulica);
			// pst izvrsi naredjenje!
			pst.execute();
			System.out.println("Uspesno ste uneli podatak u bazu!");
		} catch (SQLException e) {
			System.out.println("Nema konekcije!!!");
			e.printStackTrace();
		}finally {
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(konekcija);	
		}	
	}
	
	
	
	
	public static boolean promeniUlicu(String novaUlica, int idAdrese) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		
		try {
			konekcija = uspostaviKonekciju();
			System.out.println("Imamo konekciju!");
			
			String pismo = "UPDATE adrese SET ulica = ? WHERE id_adrese= ?";
			pst = konekcija.prepareStatement(pismo);
			pst.setString(1, novaUlica);
			pst.setInt(2, idAdrese);
			pst.executeUpdate();
			System.out.println("Ulica je uspesno izmenjena.");
			
			return true;
		} catch (SQLException e) {
			System.out.println("Nesto nevalja.");
			return false;
		}finally {
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(konekcija);
		}	
	}
	
	
	
	
	
	
	

}
