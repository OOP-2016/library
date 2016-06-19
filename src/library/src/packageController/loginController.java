package packageController;
import java.util.ArrayList;

import packageBusiness.utente;
import packageDAO.utenteDAO;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageView.loginView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Classe controller loginController
 */

public class loginController {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + 
												"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * Il metodo istanzia la classe loginView che ha il compito di istanziare la registrazionePage
	 */
	public void instanziaRegistrazioneAction(){
		
		new loginView().istanziaRegistrazionePage();
		
	}
	
	/**
	 * Il metodo fa accedere l'utente al sistema, richiamando il database e facendo un confronto dei dati. 
	 * Se il confronto va a buon fine, un infoDialog viene istanziato, altrimenti viene istanziato 
	 * un ErrorDialog
	 * 
	 * @param email Stringa che rappresenta l'email dell'utente 
	 * @param password Stringa che rappresenta la password dell'utente
	 * @param finestra finestra loginPage da chiudere 
	 */
	public void accediAction(String email, String password, loginPage finestra){
		
		if(email.length() == 0||password.length() == 0){
			new loginView().errorMessage("Campi vuoti");
			return;
		}
		
		/**
		 * Controllo pattern email
		 */
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email); 
		
		if(!(matcher.matches())){
			new loginView().errorMessage("email non valida");
			return; 
		}
		
		
		/**
		 * Controlli superati
		 */
		ArrayList<Object> datiUtente = new ArrayList<Object>();
		
		datiUtente.add(email);
		datiUtente.add(password);
		
		utente utente = (utente)new utenteDAO().retrieve(datiUtente);
		
			if(utente == null) 
				new loginView().errorMessage("login fallito");
			else{
			    new loginView().infoMessage("login riuscito");
			    
			    /**
			     * Scelta
			     */
			    if(utente.getPermessi() > 1){
			    	
			    	int choice = new loginView().modalit‡Accesso(utente);
			    	
			    		if(choice == -1){
			    			new loginView().errorMessage("Non essere indifferente! SCEGLI");
			    			return; 
			    		} else {
			    			utente.setPermessi(choice);
			    		}
			    	
			    } //fine Scelta
			    
			    
			    new loginView().dispose(finestra);
			    if(utente.getPermessi() != 6)
			    	new loginView().istanziaRicercaPage(utente);
			    else new loginView().istanziaAmministrazionePage(utente);
			    }
		
	}
	
	/**
	 * Il metodo permette all'utente di accedere come utente base
	 * 
	 * @param finestra Finestra loginPage da chiudere
	 */
	public void accediComeOspiteAction(loginPage finestra){
		
			new loginView().infoMessage("Accesso come ospite avvenuto");
			new loginView().dispose(finestra);
			new loginView().istanziaRicercaPage(new utente());
		
	}
	
}	