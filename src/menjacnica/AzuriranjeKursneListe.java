package menjacnica;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import jsonrates.JsonRatesAPIKomunikacija;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;


public class AzuriranjeKursneListe {

	private final String putanjaDoFajlaKursnaLista = "data/kursnaLista.json";
	
	public LinkedList<Valuta> ucitajValute() {
		
		LinkedList<Valuta> kursevi = new LinkedList<>();
		
			
				try {
					FileReader in = new FileReader(putanjaDoFajlaKursnaLista);
					
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					JsonObject kurs = gson.fromJson(in, JsonObject.class);
					JsonArray  kurseviArray = kurs.get("valute").getAsJsonArray();
					
					
					for (int i = 0; i < kurseviArray.size(); i++) {
							JsonObject json = (JsonObject) kurseviArray.get(i);				
							Valuta n = new Valuta();
							n.setNaziv(json.get("naziv").getAsString());
							n.setKurs(json.get("kurs").getAsDouble());								
		
							kursevi.add(n);
						}
				} catch (JsonSyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonIOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
		return kursevi;
	}
	
	
	public void upisiValute(LinkedList<Valuta> lista, GregorianCalendar datum) {
		
		JsonObject jso = new JsonObject();
		jso.addProperty("datum", datum.get(datum.DAY_OF_MONTH)+"."+
				((datum.get(datum.MONTH))+1)+"."+Integer.toString(datum.get(datum.YEAR)));
		
		
		JsonArray valuteArray = new JsonArray();
		
		for (int i = 0; i < lista.size(); i++) {
			Valuta val = lista.get(i);
			JsonObject vJson = new JsonObject();
			vJson.addProperty("naziv", val.getNaziv());
			vJson.addProperty("kurs", val.getKurs());
			
			valuteArray.add(vJson);
		}
		
		
		jso.add("valute", valuteArray);
		
			
				try {
					PrintWriter out = new PrintWriter(new BufferedWriter(
							new FileWriter(putanjaDoFajlaKursnaLista)));
					
					Gson gson = new GsonBuilder().setPrettyPrinting().create();

					out.println(gson.toJson(jso));
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	
	public void azurirajValute() {
		LinkedList<Valuta> valute = ucitajValute();
		String[] val = new String[valute.size()];
		
		for (int i = 0; i < val.length; i++) {
			val[i] = valute.get(i).getNaziv();
		}
		
		LinkedList<Valuta> valuteAzurirano = new JsonRatesAPIKomunikacija().vratiIznosKurseva(val);
		
		upisiValute(valuteAzurirano, new GregorianCalendar());
		
	}
	
	
}


