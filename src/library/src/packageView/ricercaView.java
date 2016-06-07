package packageView;

import packageBusiness.utente;
import packageController.ricercaController;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.ricercaPage;

/**
 * Classe View ricercaView
 */
public class ricercaView {

	/**
	 * Il metodo istanzia il controller ricercaController
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void exit(ricercaPage finestra){
		new ricercaController().exitAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia la classe dialog che si occuperà di chiudere la finestra
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void dispose(ricercaPage finestra){
		new dialog().disposeDialog(finestra);
	}
	
	/**
	 * Il metodo istanzia il controller ricercaController
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void logOut(ricercaPage finestra){
		new ricercaController().logOutAction(finestra); 
	}
	
	/**
	 * Il metodo permette di invocare una nuova finestra di loginPage
	 */
	public void istanziaLoginPage(){

		loginPage finestra = new loginPage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
	/**
	 * Il metodo permette di invocare una nuova finestra di operaPage
	 * 
	 * @param user Utente che ha effettuato l'accesso, il parametro ha il compito di gestire la sessione
	 */
	public void istanziaOperaPage(utente user){
		operaPage finestra = new operaPage(user);
		finestra.setVisible(true);
		finestra.setResizable(false);
	}

	/**
	 * Il metodo permette di invocare il controller ricercaController
	 * 
	 * @param finestra Finestra ricercaPage chiamante
	 * @param user Utente che ha effettuato l'accesso
	 * @param title Stringa che rappresenta il titolo del manoscritto 
	 */
	public void apriOpera(ricercaPage finestra, utente user, String title){
		new ricercaController().apriOperaAction(finestra, user, title);
	}
	
	/**
	 * Il metodo richiama la classe AlertDialog che crea un MessageDialog di informazione 
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
	
}
