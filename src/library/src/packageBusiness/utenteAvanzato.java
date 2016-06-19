package packageBusiness;

public class utenteAvanzato extends utente {
	
	private String nome;
	private String cognome; 
	private String password;
	 
	
	public utenteAvanzato(String email, int permessi, String nome, String cognome, String password) {
		super(email, permessi);
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 

	@Override
	public String getEmail() {
		return "Ciao utente" + ": " + super.getEmail();
	}
	
	
}
