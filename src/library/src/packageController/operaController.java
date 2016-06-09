package packageController;

import packageGUI.operaPage;
import packageView.operaView;


/**
 * Classe controller operaController 
 */
public class operaController {

	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void exitAction(operaPage finestra){
		new operaView().dispose(finestra);
	}
	
	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOutAction(operaPage finestra){
		new operaView().dispose(finestra);
		new operaView().istanziaLoginPage();
	}
	
}