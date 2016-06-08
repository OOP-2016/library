package packageView;

import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.registrazionePage;
import packageGUI.ricercaPage;

import javax.swing.JFrame;

import packageBusiness.utente;
import packageController.loginController;

/**
 * Classe View loginView
 */

public class loginView {
		
	/**
	 * Il metodo permette di istanziare la classe loginController
	 * 
	 * @param finestra La finestra loginPage chiamante
	 */
	public void registrati(loginPage finestra){
		
		new dialog().disposeDialog(finestra); 	//chiusura finestra
		new loginController().instanziaRegistrazioneAction(); //istanziazione controller
 
	}
		
	/**
	 * Il metodo permette di invocare una nuova finestra di registrazionePage
	 */
	public void istanziaRegistrazionePage(){

		registrazionePage finestra = new registrazionePage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
	/**
	 * Il metodo permette di istanziare la classe loginController che farà l'azione di accesso 
	 * 
	 * @param email Stringa che rappresenta l'email dell'utente
	 * @param password Stringa che rappresenta la password dell'utente
	 */
	
	public void accedi(String email, String password, loginPage finestra){
		new loginController().accediAction(email, password, finestra); 
	}
	
	/**
	 * Il metodo permette di istanziare la classe loginController che farà l'azione di accesso come utente base
	 * @param finestra Finestra loginPage da chiudere 
	 */
	
	public void accediComeOspite(loginPage finestra){
		new loginController().accediComeOspiteAction(finestra);
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
	
	/**
	 * Il metodo istanzia la classe ricercaPage 
	 * 
	 * @param user l'utente che esegue l'accesso, il parametro gestisce così le sessioni
	 */
	public void istanziaRicercaPage(utente user){
		ricercaPage finestra = new ricercaPage(user);
		finestra.setVisible(true);
		finestra.setResizable(false);
	}
	
	/**
	 * Il metodo istanzia la classe dialog che si occuperà di chiudere la finestra
	 * 
	 * @param finestra Finestra loginPage da chiudere 
	 */
	public void dispose(loginPage finestra){
		new dialog().disposeDialog(finestra);
	}
	
	
}
