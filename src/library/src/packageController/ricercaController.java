package packageController;

import packageBusiness.utente;
import packageGUI.ricercaPage;
import packageView.ricercaView;

/**
 * Classe Controller ricercaController 
 */
public class ricercaController {
	
	public void apriOperaAction(ricercaPage finestra, utente user, String title){
		new ricercaView().dispose(finestra);
		new ricercaView().istanziaOperaPage(user);
	}

	/**
	 * Il metodo richiama la classe ricercaView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void exitAction(ricercaPage finestra){
		new ricercaView().dispose(finestra);
	}
	
	/**
	 * Il metodo richiama la classe ricercaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void logOutAction(ricercaPage finestra){
		new ricercaView().dispose(finestra);
		new ricercaView().istanziaLoginPage();
	}
	
}
