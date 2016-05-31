package packageController;

import packageView.registrazione_confermaView;

public class registrazione_confermaController {

	private String nome;
	private String cognome; 
	private String email; 
	private String password; 
	
	/**
	 * Il costruttore attua una serie di controlli sui dati immessi dall'utente e se vanno a buon fine 
	 * aggiorna le sue variabili d'istanza, altrimenti un MessageDialog verrà creato richiamando la 
	 * classe AlertDialog
	 * 
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param password
	 * @param ripetiPassword
	 */
	public registrazione_confermaController(String nome, String cognome, String email, String password, String ripetiPassword){
		
		if(nome.length() == 0||cognome.length() == 0||email.length() == 0||password.length() == 0||ripetiPassword.length() == 0){
			new registrazione_confermaView().campiVuotiError(); 
			return; 
		}
		
		if(!(password.equals(ripetiPassword))){
			new registrazione_confermaView().passwordError(); 
			return; 
		}
		
		
		this.nome = nome; 
		this.cognome = cognome; 
		this.email = email; 
		this.password = password; 
		
	}
}
