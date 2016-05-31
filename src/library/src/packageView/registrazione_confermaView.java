package packageView;

import packageController.registrazione_confermaController;
import packageGUI.AlertDialog;

public class registrazione_confermaView {

	/**
	 * 
	 */
	public registrazione_confermaView(){
		
	}
	
	/**
	 * Il metodo istanzia la classe Controller registrazione_confermaView
	 * 
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param password
	 * @param ripetiPassword
	 */
	public registrazione_confermaView(String nome, String cognome, String email, String password, String ripetiPassword){
		new registrazione_confermaController(nome, cognome, email, password, ripetiPassword); 
	}
	
	/**
	 * Il metodo richiama la classe AlertDialog che crea un MessageDialog di errore per campi vuoti
	 */
	public void campiVuotiError(){
		new AlertDialog().errorDialog("Campi vuoti");
	}
	
	/**
	 * Il metodo richiama la classe AlertDialog che crea un MessageDialog di errore 
	 */
	public void passwordError(){
		new AlertDialog().errorDialog("I campi \"PASSWORD\" e \"RIPETI PASSWORD\" non coincidono");
	}
	
}
