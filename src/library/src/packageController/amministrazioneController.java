package packageController;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import packageBusiness.utente;
import packageDAO.amministrazioneDAO;
import packageGUI.amministrazionePage;
import packageGUI.dialog;
import packageGUI.operaPage;
import packageView.amministrazioneView;
import packageView.operaView;

/**
 * classe amministrazioneController
 */
public class amministrazioneController {
	
	/**
	 * Il metodo esce dal programma 
	 * 
	 * @param finestra Finestra da chiudere 
	 */
	public void exitAction(amministrazionePage finestra){
		new amministrazioneView().dispose(finestra);
	}
	
	/**
	 * Il metodo richiama istanzia la loginPage
	 * 
	 * @param finestra Finestra da chiudere  
	 */
	public void logOutAction(amministrazionePage finestra){
		new amministrazioneView().dispose(finestra);
		new amministrazioneView().istanziaLoginPage();
	}
	
	/**
	 * Il metodo gestisce il click di "seleziona" su amministrazionePage 
	 * 
	 * @param listener tableListener che viene invocato ad ogni modifica della JTable
	 * @param columnNames ArrayList che contiene i nomi delle colonne della tabella
	 * @param tm DefaultTableModel che permette di gestire la JTable 
	 * @param tableName Stringa che rappresenta il nome della tabella 
	 */
	public void selezionaAction(tableListener listener, ArrayList<String> columnNames, DefaultTableModel tm, String tableName){
		
		
		boolean success = new amministrazioneDAO().seleziona(listener, columnNames, tm, tableName); 
		
		if(!success){
			new dialog().errorDialog("Errore");
			return; 
		}
		
	}
	
	/**
	 * Il metodo gestisce il click di "ricarica" su amministrazionePage 
	 * 
	 * @param listener tableListener che viene invocato ad ogni modifica della JTable
	 * @param columnNames ArrayList che contiene i nomi delle colonne della tabella
	 * @param tm DefaultTableModel che permette di gestire la JTable 
	 * @param tableName Stringa che rappresenta il nome della tabella 
	 */
	public void ricaricaAction(tableListener listener, ArrayList<String> columnNames, DefaultTableModel tm, String tableName){
		
		
		boolean success = new amministrazioneDAO().ricarica(listener, columnNames, tm, tableName); 
		
		if(!success){
			new dialog().errorDialog("Errore");
			return; 
		}
		
	}
	
	/**
	 * Il metodo gestisce il click di "delete" su amministrazionePage 
	 * 
	 * @param table JTable che contiene i dati del database 
	 * @param tableName Stringa che rappresenta il nome della tabella 
	 */
	public void deleteAction(JTable table, String tableName){
		boolean success = new amministrazioneDAO().delete(table, tableName); 
		
		if(!success){
			new dialog().errorDialog("Errore");
			return; 
		}
	}

}
