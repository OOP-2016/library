package packageView;

import javax.swing.JFrame;

import packageController.loginController;
import packageController.registrazioneController;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.registrazionePage;

/**
 * Classe View registrazioneView
 */

public class registrazioneView {
	
	/**
	 * Il metodo istanzia la classe Controller registrazioneController
	 * 
	 * @param nome Stringa che rappresenta il nome dell'utente
	 * @param cognome Stringa che rappresenta il cognome dell'utente
	 * @param email Stringa che rappresenta l' email dell'utente
	 * @param password Stringa che rappresenta la password dell'utente
	 * @param ripetiPassword Stringa che rappresenta la conferma della password dell'utente
	 * @param finestra Finestra registrazionePage da chiudere
	 */
	public void confermaRegistrazione(String nome, String cognome, String email, String password, String ripetiPassword, registrazionePage finestra){
		new registrazioneController().confermaRegistrazioneAction(nome, cognome, email, password, ripetiPassword, finestra); 
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
	 * Il metodo permette di istanziare la classe registrazioneController
	 * 
	 * @param finestra La finestra registrazionePage chiamante
	 */
	public void indietro(registrazionePage finestra){
		
		new dialog().disposeDialog(finestra);//chiusura finestra
		new registrazioneController().istanziaLoginAction(); //istanziazione controller
		
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
	 * Il metodo istanzia la classe dialog che si occuperà di chiudere la finestra
	 * 
	 * @param finestra Finestra registrazionePage da chiudere 
	 */
	public void dispose(registrazionePage finestra){
		new dialog().disposeDialog(finestra);
	}
	
	
}
