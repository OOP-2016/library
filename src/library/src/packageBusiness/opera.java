package packageBusiness;

/**
 * Classe Business Opera
 */
public class opera {
	private String titolo;
	private String autore;
	private int anno_pubblicazione;
	private int numero_pagine;
	private boolean pubblicata;
	
	/**
	 * 
	 * 
	 * @param titolo
	 * @param autore
	 * @param anno_pubblicazione
	 * @param numero_pagine
	 * @param pubblicata
	 */
	public opera(String titolo, String autore, int anno_pubblicazione, int numero_pagine, boolean pubblicata) {
		this.titolo = titolo;
		this.autore = autore;
		this.anno_pubblicazione = anno_pubblicazione;
		this.numero_pagine = numero_pagine;
		this.pubblicata=pubblicata;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTitolo() {
		return this.titolo;
	}
	
	/**
	 * 
	 * @param titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAutore() {
		return this.autore;
	}

	/**
	 * 
	 * @param autore
	 */
	public void setAutore(String autore) {
		this.autore = autore;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getAnno_pubblicazione() {
		return this.anno_pubblicazione;
	}
	
	/**
	 * 
	 * @param anno_pubblicazione
	 */
	public void setAnno_pubblicazione(int anno_pubblicazione) {
		this.anno_pubblicazione = anno_pubblicazione;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumero_pagine() {
		return this.numero_pagine;
	}
	
	/**
	 * 
	 * @param numero_pagine
	 */
	public void setNumero_pagine(int numero_pagine) {
		this.numero_pagine = numero_pagine;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getPubblicata() {
		return this.pubblicata;
	}
	
	/**
	 * 
	 * @param pubblicata
	 */
	public void setPubblicata(boolean pubblicata) {
		this.pubblicata = pubblicata;
	}
}
