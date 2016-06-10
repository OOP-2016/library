package packageBusiness;

public class pagina {

	private int numero; 
	private String opera; 
	private immagine immagine; 
	private String trascrizione;
	
	public pagina(int numero, String opera, immagine immagine, String trascrizione) {
		
		this.numero = numero;
		this.opera = opera;
		this.immagine = immagine;
		this.trascrizione = trascrizione;
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
	
	public String getTrascrizione() {
		return this.trascrizione;
	}
	
	public void setTrascrizione(String trascrizione) {
		this.trascrizione = trascrizione;
	} 
	
	
}
