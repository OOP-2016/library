package packageDAO;

import java.util.ArrayList;

/**
 * Interfaccia che mostra la firma dei metodi che devono implementare tutte
 * le classi DAO 
 */
public interface DAO {
	/**
	 * Metodo che inserisce parametri nel database 
	 * 
	 * @param args ArrayList contententi i parametri da inserire nel database 
	 * @return true se insert termina senza errori, false altrimenti 
	 */
	public boolean insert(ArrayList<Object> args);
	
	/**
	 * Metodo che prende dati dal database
	 * 
	 * @param args ArrayList contententi i parametri che servono a costruire la query per il database
	 * @return oggetto preso dal database 
	 */
	public Object retrieve(ArrayList<Object> args);
	
	/**
	 * Il metodo modifica una riga nel database
	 * 
	 * @param args args ArrayList contententi i parametri da inserire nel database 
	 * @return true se insert termina senza errori, false altrimenti 
	 */
	//public boolean update(ArrayList<Object> args);
		
	/**
	 * Il metodo cancella una riga dal database 
	 * 
	 * @param args args ArrayList contententi i parametri che servono a costruire la query per il database
	 * @return true se insert termina senza errori, false altrimenti 
	 */
	//public boolean delete(ArrayList<Object> args); 
		
}
