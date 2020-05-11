package jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import metode.JdbcMetode;
import model.OsobaPozicija;
import model.Osobe;

public class GlavnaKlasa {

	public static void main(String[] args) {
		
		/*  Ovo je deo za insert
		 * 
		 *  String drzava= "Republika Srpska";
			String grad = "Trebinje";
			String ulica= "Duciceva";
			
			JdbcMetode.ubaciAdresu(drzava, grad, ulica);
		*/
		
	
		
	/*	ovo je deo za update
	 * 
	 *  String novaUlica = "BB8";
		int idAdrese= 4;
		
		JdbcMetode.promeniUlicu(novaUlica, idAdrese);
	*/
		
		/*
		 * ako jednu adresu vraca
		JdbcMetode.ispisiAdresu(4);
		*/
		
/*		//ako vraca sve adrese
		JdbcMetode.ispisiSveAdrese();*/
		
		Osobe o = JdbcMetode.vratiOsobu(2);
		System.out.println(o.getFirstName() + " " + o.getLastName() + " "+ o.getPosition());
		
		OsobaPozicija op = JdbcMetode.vratiOsobuPoziciju(o.getId());
		System.out.println(op.getFirstName() + " " + op.getLastName() + " "+ op.getPosition());
		

		
	}

}
