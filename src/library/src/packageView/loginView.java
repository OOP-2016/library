package packageView;

import packageGUI.loginPage;
import packageGUI.registrazionePage;

import javax.swing.JFrame;

import packageController.loginController;

/**
 * Classe VIew loginView
 */

public class loginView {
		
	/**
	 * Il metodo permette di istanziare la classe loginController
	 * 
	 * @param finestra La finestra loginPage chiamante
	 */
	public void istanziaControllerFinestra(loginPage finestra){
		
		finestra.dispose(); 	//chiusura finestra
		
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
	
}
