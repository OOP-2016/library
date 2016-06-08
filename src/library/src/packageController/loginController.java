package packageController;
import java.util.ArrayList;

import packageBusiness.utente;
import packageDAO.utenteDAO;
import packageGUI.loginPage;
import packageView.loginView;

/**
 * Classe controller loginController
 */

public class loginController {
	
	/**
	 * Il metodo istanzia la classe loginView 
	 */
	public void instanziaRegistrazioneAction(){
		
		new loginView().istanziaRegistrazionePage();
		
	}
	
	/**
	 * Il metodo fa accedere l'utente al sistema, richiamando il database e facendo un confronto dei dati. 
	 * Se il confronto va a buon fine, un infoDialog viene istanziato, altrimenti viene istanziato 
	 * un ErrorDialog
	 * 
	 * @param email
	 * @param password
	 */
	public void accediAction(String email, String password, loginPage finestra){
		
		if(email.length() == 0||password.length() == 0){
			new loginView().errorMessage("Campi vuoti");
			return;
		}
		ArrayList<Object> datiUtente = new ArrayList<Object>();
		datiUtente.add(email);
		datiUtente.add(password);
		utente utente = (utente)new utenteDAO().retrieve(datiUtente);
			if(utente == null) new loginView().errorMessage("login fallito");
			else{
			    new loginView().infoMessage("login riuscito");
			    new loginView().dispose(finestra);
			    new loginView().istanziaRicercaPage(utente);
			    }
		
		
		
		
	}
	
	/**
	 * Il metodo permette all'utente di accedere come utente base
	 */
	public void accediComeOspiteAction(loginPage finestra){
		
		if(true /*Connessione a DB riuscita, con chiamata a DAO che restituisce true se tutto okay*/ ){
			new loginView().infoMessage("Accesso come \"Utente Base\" avvenuto");
			new loginView().dispose(finestra);
			new loginView().istanziaRicercaPage(new utente());
		}
		else
			new loginView().errorMessage("Errore in fase di accesso");
		
	}
	
}	