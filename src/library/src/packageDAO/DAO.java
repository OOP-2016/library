package packageDAO;

import java.util.ArrayList;

/**
 * Interfaccia che mostra la firma dei metodi che devono implementare tutte
 * le classi DAO 
 */
public interface DAO {
	public boolean insert(ArrayList<Object> args);
	public Object retrieve(ArrayList<Object> args);
}
