package packageView;

import packageGUI.loginPage;
import packageGUI.registrazionePage;

import javax.swing.JFrame;

import packageController.registrazioneController;

public class registrazioneView {
		
	/**
	 * Il metodo permette di istanziare la classe registrazioneController
	 * 
	 * @param finestra La finestra chiamante
	 */
	public void istanziaController(Object finestra){
		
		JFrame window = null; 
		if(finestra instanceof JFrame)
			window = (JFrame)finestra;
		else throw new RuntimeException(); 
		
		window.dispose(); 	//chiusura finestra
		
		

		//istanziazione registrazioneController
		if(finestra instanceof loginPage)
			new registrazioneController().instanziaRegistrazioneAction();
		else if(finestra instanceof registrazionePage)
			new registrazioneController().istanziaLoginAction();
		
		else 
			throw new RuntimeException(); 
		
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
	 * Il metodo permette di invocare una nuova finestra di loginPage
	 */
	public void istanziaLoginPage(){

		loginPage finestra = new loginPage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
}
