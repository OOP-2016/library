package packageController;

import packageBusiness.utente;
import packageGUI.amministrazionePage;
import packageGUI.operaPage;
import packageView.amministrazioneView;
import packageView.operaView;

public class amministrazioneController {
	
	public void exitAction(amministrazionePage finestra){
		new amministrazioneView().dispose(finestra);
	}
	
	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOutAction(amministrazionePage finestra){
		new amministrazioneView().dispose(finestra);
		new amministrazioneView().istanziaLoginPage();
	}
	
	/**
	 * 
	 * @param finestra
	 * @param user
	 */
	public void indietroAction(amministrazionePage finestra, utente user){
		new amministrazioneView().dispose(finestra);
		new amministrazioneView().istanziaRicercaPage(user);
	}

}
