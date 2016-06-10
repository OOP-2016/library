package packageController;

import java.util.ArrayList;

import packageBusiness.pagina;
import packageBusiness.utente;
import packageDAO.paginaDAO;
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
	
	public void indietroAction(operaPage finestra, utente user){
		new operaView().dispose(finestra);
		new operaView().istanziaRicercaPage(user);
	}
	
	public pagina vistaAction(String titolo, int npagina){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(npagina); 
		args.add(titolo); 
		pagina pagina = (pagina)new paginaDAO().retrieve(args); 
		return pagina; 
	}
}
