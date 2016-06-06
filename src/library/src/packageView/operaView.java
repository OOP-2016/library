package packageView;

import packageBusiness.utente;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;

public class operaView {
	
	
	public void istanziaOperaPage(utente user){

		operaPage finestra = new operaPage(user);
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
