package packageView;

import packageGUI.amministrazionePage;
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
	 * Il metodo permette di istanziare le classi loginController e dialog
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
	 * Il metodo permette di istanziare la classe loginController che far� l'azione di accesso 
	 * 
	 * @param email Stringa che rappresenta l'email dell'utente
	 * @param password Stringa che rappresenta la password dell'utente
	 * @param finestra finestra da chiudere
	 */	
	public void accedi(String email, String password, loginPage finestra){
		new loginController().accediAction(email, password, finestra); 
	}
	
	/**
	 * Il metodo permette di istanziare la classe loginController che far� l'azione di accesso come utente base
	 * 
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
	 * @param user l'utente che esegue l'accesso, il parametro gestisce cos� le sessioni
	 */
	public void istanziaRicercaPage(utente user){
		ricercaPage finestra = new ricercaPage(user);
		finestra.setVisible(true);
		finestra.setResizable(false);
	}
	
	/**
	 * Il metodo istanzia la classe dialog che si occuper� di chiudere la finestra
	 * 
	 * @param finestra Finestra loginPage da chiudere 
	 */
	public void dispose(loginPage finestra){
		new dialog().disposeDialog(finestra);
	}
	
	/**
	 * il metodo istanzia la classe dialog
	 * 
	 * @param utente utente che ha effettuato l'accesso
	 * @return il metodo ritorna un un valore di tipo intero
	 */
	public int modalit�Accesso(utente utente){
		int choice = new dialog().modalit�AccessoForm(utente);
		return choice; 
	}
	
	/**
	 * il metoto istanzia amministrazionePage
	 * 
	 * @param user utente che ha effettuato l'accesso come amministratore
	 */
	public void istanziaAmministrazionePage(utente user){
		amministrazionePage finestra = new amministrazionePage(user);
		finestra.setVisible(true);
		finestra.setResizable(false);
	}
}
