package packageBusiness;

/**
 * La classe utente rappresenta qualsiasi utente che interagisce con 
 * il sistema
 */
public class utente {

	private final String email; 
	private int permessi;
	
	/**
	 * Costruttore per creare un utente base 
	 */
	public utente(){
		this.email = null; 
		this.permessi = 0; 
	}
	
	/**
	 * Costruttore per creare utenti avanzati con diversi permessi
	 * 
	 * @param email Stringa che spacifica l'email dell'utente 
	 * @param permessi Intero che specifica i permessi dell'utente nel sistema 
	 */
	public utente(String email, int permessi){
		this.email = email; 
		this.permessi = permessi; 
	}
	
	/**
	 * Il metodo ritorna l'email dell'utente
	 * 
	 * @return Stringa che spacifica l'email dell'utente 
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Il metodo ritorna i permessi dell'utente
	 * 
	 * @return Intero che specifica i permessi dell'utente nel sistema 
	 */
	public int getPermessi() {
		return permessi;
	}
	
	public void setPermessi(int permessi) {
		this.permessi = permessi;
	}
	
}
