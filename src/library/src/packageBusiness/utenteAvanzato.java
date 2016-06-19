package packageBusiness;

	/**
	 * Classi Business UtenteAvanzato
	 */

public class utenteAvanzato extends utente {
	
	private String nome;
	private String cognome; 
	private String password;
	
	/**
	 * Costruttore
	 * 
	 * @param email
	 * @param permessi
	 * @param noem
	 * @param cognome
	 * @param password
	 */
	
	public utenteAvanzato(String email, int permessi, String nome, String cognome, String password) {
		super(email, permessi);
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
	}
	
	/**
	 * ritorna nome utente
	 * @return nome
	 */
	
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * imposta nome utente
	 * @param nome
	 */
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * ritorna cognome utente
	 * @return cognome
	 */
	
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * imposta cognome utente
	 * @param cognome
	 */
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * ritorna password utente
	 * @return password
	 */
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * imposta password utente
	 * @param cognome
	 */
	
	public void setPassword(String password) {
		this.password = password;
	} 
	
	/**
	 * ritorna email utente
	 * @return stringa + email
	 * override
	 */
	

	@Override
	public String getEmail() {
		return "Ciao utente" + ": " + super.getEmail();
	}
	
	
}
