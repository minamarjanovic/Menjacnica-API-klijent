package menjacnica;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class Main {
	
	public static void main(String[] args) {
		
		AzuriranjeKursneListe azuriranje = new AzuriranjeKursneListe();	
		
		LinkedList<Valuta> pocetna = new LinkedList<>();
		
		Valuta valuta1 = new Valuta();
		valuta1.setNaziv("EUR");
		valuta1.setKurs(121.3434);
		Valuta valuta2 = new Valuta();
		valuta2.setNaziv("USD");
		valuta2.setKurs(121.3434);
		Valuta valuta3 = new Valuta();
		valuta3.setNaziv("CAD");
		valuta3.setKurs(121.3434);
		Valuta valuta4 = new Valuta();
		valuta4.setNaziv("GBP");
		valuta4.setKurs(121.3434);
			pocetna.add(valuta1);
			pocetna.add(valuta2);
			pocetna.add(valuta3);
			pocetna.add(valuta4);
			
			GregorianCalendar datum = new GregorianCalendar();
			datum.set(datum.DAY_OF_MONTH, 5);
			datum.set(datum.MONTH, 4);
			datum.set(datum.YEAR, 2015);
		
		azuriranje.upisiValute(pocetna, datum);
		
		azuriranje.azurirajValute();
		
	}

}