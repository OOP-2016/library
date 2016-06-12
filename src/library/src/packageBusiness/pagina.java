package packageBusiness;

public class pagina {

	private int numero; 
	private String opera; 
	private immagine immagine; 
	private boolean immagine_validata; 
	private String trascrizione;
	private boolean trascrizione_validata; 
	
	public pagina(int numero, String opera, immagine immagine, boolean immagine_validata, String trascrizione, boolean trascrizione_validata) {
		
		this.numero = numero;
		this.opera = opera;
		this.immagine = immagine;
		this.immagine_validata = immagine_validata; 
		this.trascrizione = trascrizione;
		this.trascrizione_validata = trascrizione_validata; 
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getOpera() {
		return this.opera;
	}
	
	public void setOpera(String opera) {
		this.opera = opera;
	}
	
	public immagine getImmagine() {
		return this.immagine;
	}
	
	public void setImmagine(immagine immagine) {
		this.immagine = immagine;
	}
	
	public boolean getimmagine_validata() {
		return this.immagine_validata;
	}
	
	public void setimmagine_validata(boolean immagine_validata) {
		this.immagine_validata = immagine_validata;
	}
	
	public String getTrascrizione() {
		return this.trascrizione;
	}
	
	public void setTrascrizione(String trascrizione) {
		this.trascrizione = trascrizione;
	} 
	
	public boolean gettrascrizione_validata() {
		return this.trascrizione_validata;
	}
	
	public void settrascrizione_validata(boolean trascrizione_validata) {
		this.trascrizione_validata = trascrizione_validata;
	}
	
	
}
