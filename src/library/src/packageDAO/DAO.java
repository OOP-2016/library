package packageDAO;

import java.sql.SQLException;
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
	 * @exception SQLException In caso di fallimento di una delle operazioni di query sul database 
	 * @exception ClassNotFoundException In caso di fallimento del driver SQL
	 * @exception Exception In caso di fallimento generale di una delle operazioni sul database 
	 * @return true se insert termina senza errori, false altrimenti 
	 */
	public boolean insert(ArrayList<Object> args);
	
	/**
	 * Metodo che prende dati dal database
	 * 
	 * @param args ArrayList contententi i parametri che servono a costruire la query per il database
	 * @exception SQLException In caso di fallimento di una delle operazioni di query sul database 
	 * @exception ClassNotFoundException In caso di fallimento del driver SQL
	 * @exception Exception In caso di fallimento generale di una delle operazioni sul database 
	 * @return oggetto preso dal database 
	 */
	public Object retrieve(ArrayList<Object> args);
		
}
