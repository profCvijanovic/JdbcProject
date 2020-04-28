package jdbc;

import metode.JdbcMetode;

public class GlavnaKlasa {

	public static void main(String[] args) {
		
		/*String drzava= "Republika Srpska";
		String grad = "Trebinje";
		String ulica= "Duciceva";*/
		
		//JdbcMetode.ubaciAdresu(drzava, grad, ulica);
		
		String novaUlica = "BB8";
		int idAdrese= 4;
		
		JdbcMetode.promeniUlicu(novaUlica, idAdrese);
		
		
		
		
		
		
	}

}
