package packageView;

import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.registrazionePage;

import javax.swing.JFrame;

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
	public void istanziaControllerFinestra(loginPage finestra){
		
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
	
	public void accedi(String email, String password){
		new loginController().accediAction(email, password); 
	}
	
	/**
	 * Il metodo permette di istanziare la classe loginController che farà l'azione di accesso come utente base
	 */
	
	public void accediComeOspite(){
		new loginController().accediComeOspiteAction();
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
