package metode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OsobaPozicija;
import model.Osobe;

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
	
	//zatvaranje result set-a
		public static void zatvoriResultSet(ResultSet res) {
			if(res != null) {
				try {
					res.close();
					System.out.println("Result Set je zatvoren!!!");
				} catch (SQLException e) {
					System.out.println("Result Set nije zatvoren!!!");
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
	
	// metoda za update tabele adresa,menjamo ulicu
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
	
	//metoda koja ispisuje datu adresu
	public static void ispisiAdresu(int idAdrese) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		
		try {
			//uspostavljamo konekciju
			konekcija = uspostaviKonekciju();
			//pravimo pismo(upit)
			String pismo = "SELECT * \r\n" + 
					       "FROM adrese \r\n" + 
					       "WHERE id_adrese = ?";
			// zovemo postara pst
			pst = konekcija.prepareStatement(pismo);
			//setujemo parametre
			pst.setInt(1,idAdrese);
			//pst je odneo pismo u bazu, a baza je odgovor dala res-u
			res = pst.executeQuery();
			
			System.out.println("id****drzava*****grad****ulica");
			
			//prolazak kroz res
			while(res.next()) {
				int id= res.getInt("id_adrese"); //uzimamo id adrese iz kolone id_adrese
				String drzava= res.getString("drzava"); //uzimamo ime drzave iz kolone drzava
				String grad = res.getString("grad"); //uzimamo ime grada iz kolone grad
				String ulica = res.getString("ulica"); //uzimamo ime ulice iz kolone ulica
				
				System.out.println(id + "****" + drzava + "****" + grad + "****" + ulica);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			zatvoriResultSet(res);
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(konekcija);
		}
			
	}
	
	
	//metoda koja ispisuje sve adrese
		public static void ispisiSveAdrese() {
			
			Connection konekcija = null;
			PreparedStatement pst = null;
			ResultSet res = null;
			
			try {
				//uspostavljamo konekciju
				konekcija = uspostaviKonekciju();
				//pravimo pismo(upit)
				String pismo = "SELECT * \r\n" + 
						       "FROM adrese";
				
				// zovemo postara pst
				pst = konekcija.prepareStatement(pismo);
				
				// ne setujemo parametre jer ih nemamo!
			
				//pst je odneo pismo u bazu, a baza je odgovor dala res-u
				res = pst.executeQuery();
				
				System.out.println("id****drzava*****grad****ulica");
				
				//prolazak kroz res
				while(res.next()) {
					int id= res.getInt("id_adrese"); //uzimamo id adrese iz kolone id_adrese
					String drzava= res.getString("drzava"); //uzimamo ime drzave iz kolone drzava
					String grad = res.getString("grad"); //uzimamo ime grada iz kolone grad
					String ulica = res.getString("ulica"); //uzimamo ime ulice iz kolone ulica
					
					System.out.println(id + "****" + drzava + "****" + grad + "****" + ulica);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				zatvoriResultSet(res);
				zatvoriPreparedStatement(pst);
				zatvoriKonekciju(konekcija);
			}
				
		}
		
		// vraca Osobu na osnovu id
		public static Osobe vratiOsobu(int idOsobe) {
			
			Connection konekcija = null;
			PreparedStatement pst = null;
			ResultSet res = null;
			
			Osobe osoba = new Osobe();
			
			try {
				//uspostavi konekciju
				konekcija = uspostaviKonekciju();
				System.out.println("Konektovao sam se!");
				
				//pismo
				String pismo = "SELECT * \r\n" + 
						"FROM osobe \r\n" + 
						"WHERE id_osobe = ?";
				
				//pravimo pst
				pst = konekcija.prepareStatement(pismo);
				
				// setujemo parametre
				pst.setInt(1, idOsobe);
				
				// pravimo res
				res = pst.executeQuery();
				
				// prolazak kroz res
				while (res.next()) {
					//uzimam podatke iz reda
					int id_osobe = res.getInt("id_osobe");
					String ime = res.getString("ime");
					String prezime = res.getString("prezime");
					String jmbg = res.getString("jmbg");
					int pozicija = res.getInt("pozicija");
					
					//mapiranje
					osoba.setId(id_osobe);
					osoba.setFirstName(ime);
					osoba.setLastName(prezime);
					osoba.setPosition(pozicija);
				}
				
				return osoba;
				
			} catch (SQLException e) {
			
				return null;
				
			}finally {
				zatvoriResultSet(res);
				zatvoriPreparedStatement(pst);
				zatvoriKonekciju(konekcija);
			}
		}
			
		// vraca OsobaPozicija na osnovu id
		public static OsobaPozicija vratiOsobuPoziciju(int id) {
			
			Connection konekcija = null;
			PreparedStatement pst = null;
			ResultSet res = null;
			
			OsobaPozicija osobaPozicija = new OsobaPozicija();
			
			try {
				konekcija = uspostaviKonekciju();
				String pismo = "SELECT \r\n" + 
								"o.ime, \r\n" + 
								"o.prezime, \r\n" + 
								"p.naziv \r\n" + 
								"FROM osobe o \r\n" + 
								"INNER JOIN pozicije p ON o.pozicija = p.id_pozicije \r\n" + 
								"WHERE o.id_osobe = ?";
				pst = konekcija.prepareStatement(pismo);
				pst.setInt(1, id);
				
				res= pst.executeQuery();
				
				while(res.next()) {
					//uzimam podatke iz reda i mapiram
					osobaPozicija.setFirstName(res.getString("ime"));
					osobaPozicija.setLastName(res.getString("prezime"));
					osobaPozicija.setPosition(res.getString("naziv"));
				}
				
				return osobaPozicija;
				
			} catch (SQLException e) {
				return null;
			}finally {
				zatvoriResultSet(res);
				zatvoriPreparedStatement(pst);
				zatvoriKonekciju(konekcija);
			}	
		}
		
		
	// metod koji vraca sve osobe
	public static List<Osobe> vratiSveOsobe(){
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		
		List<Osobe> sveOsobe = new ArrayList<Osobe>();
		
		try {
			konekcija = uspostaviKonekciju();
			String pismo = "SELECT * \n" + 
						   "FROM osobe";
			pst = konekcija.prepareStatement(pismo);
			res = pst.executeQuery();
			
			while(res.next()) {
				
				//ovde mapira rezultate iz res-a u objekat Osobe
				Osobe osoba = new Osobe();
				osoba.setId(res.getInt("id_osobe"));
				osoba.setFirstName(res.getString("ime"));
				osoba.setLastName(res.getString("prezime"));
				osoba.setIdnr(res.getString("jmbg"));
				osoba.setPosition(res.getInt("pozicija"));
				//dodaje osobu u listu osoba
				sveOsobe.add(osoba);
			}
			return sveOsobe;
		} catch (SQLException e) {
			return null;
		}finally {
			zatvoriResultSet(res);
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(konekcija);		
		}
		
		
		
		
		
	}
		
		
	
	
	
	
	
	

}
