package packageController;

import packageGUI.registrazionePage;
import packageView.loginView;
import packageView.registrazioneView;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import packageDAO.utenteDAO;

/**
 * Classe controller registrazioneController
 */

public class registrazioneController {

	private String nome;
	private String cognome; 
	private String email; 
	private String password; 
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + 
												"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * Il metodo attua una serie di controlli sui dati immessi dall'utente e se vanno a buon fine 
	 * aggiorna le sue variabili d'istanza, altrimenti un verrà creato un MessageDialog di errore
	 * 
	 * @param nome Stringa che rappresenta il nome dell'utente
	 * @param cognome Stringa che rappresenta il cognome dell'utente
	 * @param email Stringa che rappresenta l'email dell'utente
	 * @param password Stringa che rappresenta la password dell'utente
	 * @param ripetiPassword Stringa che rappresenta la conferma della password dell'utente
	 * @param finestra Finestra registrazionePage da chiudere
	 */
		public void confermaRegistrazioneAction(String nome, String cognome, String email, String password, String ripetiPassword, registrazionePage finestra){
		
			/**
			 * Controllo campi vuoti 
			 */
			if(nome.length() == 0||cognome.length() == 0||email.length() == 0||password.length() == 0||ripetiPassword.length() == 0){
			new registrazioneView().errorMessage("Campi vuoti"); 
			return; 
		}
		
		/**
		 * Controllo pattern email
		 */
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email); 
		
		if(!(matcher.matches())){
			new registrazioneView().errorMessage("email non valida");
			return; 
		}
		
		/**
		 * Controllo match password/ripetiPassword
		 */
		if(!(password.equals(ripetiPassword))){
			new registrazioneView().errorMessage("I campi \"PASSWORD\" e \"RIPETI PASSWORD\" non coincidono"); 
			return; 
		}
		
		/**
		 * Controlli superati 
		 */
		this.nome = nome; 
		this.cognome = cognome; 
		this.email = email; 
		this.password = password;
		
		
		ArrayList<Object> datiRegistrazione = new ArrayList<Object>();
		
		datiRegistrazione.add(this.nome);
		datiRegistrazione.add(this.cognome);
		datiRegistrazione.add(this.email);
		datiRegistrazione.add(this.password);
		
		/* passo i dati al DAO */
		boolean success = new utenteDAO().insert(datiRegistrazione);
		/* dialog di successo o errore */ 
		if(success) 
			new registrazioneView().infoMessage("registrazione avvenuta");
		else
			new registrazioneView().errorMessage("registrazione fallita");
		
		/* dispose finestra */
		new registrazioneView().dispose(finestra);
		/* riapro la login page */
		new registrazioneView().istanziaLoginPage();
	}
	
	/**
	 * Il metodo istanzia la classe registrazioneView 
	 */
	public void istanziaLoginAction() {
		
		new registrazioneView().istanziaLoginPage();
		
	}
	
}
