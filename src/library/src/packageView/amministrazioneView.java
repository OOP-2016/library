package packageView;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import packageBusiness.utente;
import packageController.amministrazioneController;
import packageController.operaController;
import packageController.tableListener;
import packageGUI.aggiungiOperaForm;
import packageGUI.amministrazionePage;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.registrazionePage;
import packageGUI.ricercaPage;

/**
 * classe amministrazioneView
 */
public class amministrazioneView {
	
	/**
	 * Il metodo istanzia amministrazioneController 
	 * 
	 * @param finestra finestra da chiudere
	 */
	public void exit(amministrazionePage finestra){
		new amministrazioneController().exitAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia amministrazioneController 
	 * 
	 * @param finestra finestra da chiudere
	 */
	public void logOut(amministrazionePage finestra){
		new amministrazioneController().logOutAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia dialog
	 * 
	 * @param finestra finestra da chiudere
	 */
	public void dispose(amministrazionePage finestra){
		new dialog().disposeDialog(finestra);
	}
	
	/**
	 * Il metodo richiama la classe dialog che crea un MessageDialog di informazione 
	 * 
	 * @param info Stringa informativa
	 */
	public void infoMessage(String info){
		new dialog().infoDialog(info);
	}
	
	/**
	 * Il metodo richiama la classe AlertDialog che crea un MessageDialog di errore 
	 * 
	 * @param error Stringa di errore 
	 */
	public void errorMessage(String error){
		new dialog().errorDialog(error);
	}
	
	/**
	 * Il metodo istanzia una pagina di login
	 */
	public void istanziaLoginPage(){

		loginPage finestra = new loginPage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
	/**
	 * Il metodo istanzia una pagina di registrazione
	 */
	public void istanziaRegistrazionePage(){

		registrazionePage finestra = new registrazionePage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		finestra.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}
	
	/**
	 * Il metodo istanzia aggiungiOperaForm 
	 * 
	 * @param finestra finestra da chiudere
	 * @exception Exception in caso di fallimento di creazione di istanza
	 */
	public void apriAggiungiOperaForm(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aggiungiOperaForm frame = new aggiungiOperaForm();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/**
		 * Il metodo istanzia ricercaPage
		 * 
		 * @param utente utente che ha effettuato l'accesso
		 */	
	}
	public void istanziaRicercaPage(utente user){
		ricercaPage finestra = new ricercaPage(user);
		finestra.setVisible(true);
		finestra.setResizable(false);
		finestra.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}
	
	/**
	 * Il metodo istanzia amministrazioneController 
	 * 
	 * @param listener gestisce i cambiamenti sulla JTable collegata al database
	 * @param columnNames contiene i nomi delle colonna di una singola tabella
	 * @param tm tabella contenente i dati di una tabella corrispondente del database
	 * @param tableName nome della tabella presa in considerazione
	 */
	public void seleziona(tableListener listener, ArrayList<String> columnNames, DefaultTableModel tm, String tableName){
		new amministrazioneController().selezionaAction(listener, columnNames, tm, tableName); 
	}
	
	/**
	 * Il metodo istanzia amministrazioneController 
	 * 
	 * @param listener gestisce i cambiamenti sulla JTable collegata al database
	 * @param columnNames contiene i nomi delle colonna di una singola tabella
	 * @param tm tabella contenente i dati di una tabella corrispondente del database
	 * @param tableName nome della tabella presa in considerazione
	 */
	public void ricarica(tableListener listener, ArrayList<String> columnNames, DefaultTableModel tm, String tableName){
		new amministrazioneController().ricaricaAction(listener, columnNames, tm, tableName); 
	}
	
	/**
	 * Il metodo istanzia amministrazioneController 
	 * 
	 * @param table tabella contenente i dati di una tabella corrispondente del database
	 * @param tableName nome della tabella presa in considerazione
	 */
	public void delete(JTable table, String tableName){
		new amministrazioneController().deleteAction(table, tableName); 
	}
}
