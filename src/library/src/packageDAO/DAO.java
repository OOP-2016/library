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
	 * @param args ArrayList contententi i parametri da inserire nel database 
	 * @return oggetto preso dal database 
	 */
	public Object retrieve(ArrayList<Object> args);
	
}
