package packageBusiness;

public class opera {
	private String titolo;
	private String autore;
	private int anno_pubblicazione;
	private int numero_pagine;
	private boolean pubblicata;
	
	public opera(String titolo, String autore, int anno_pubblicazione, int numero_pagine, boolean pubblicata) {
		this.titolo = titolo;
		this.autore = autore;
		this.anno_pubblicazione = anno_pubblicazione;
		this.numero_pagine = numero_pagine;
		this.pubblicata=pubblicata;
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getAutore() {
		return this.autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}
	
	public int getAnno_pubblicazione() {
		return this.anno_pubblicazione;
	}
	
	public void setAnno_pubblicazione(int anno_pubblicazione) {
		this.anno_pubblicazione = anno_pubblicazione;
	}
	
	public int getNumero_pagine() {
		return this.numero_pagine;
	}
	
	public void setNumero_pagine(int numero_pagine) {
		this.numero_pagine = numero_pagine;
	}
	
	public boolean getPubblicata() {
		return this.pubblicata;
	}
	
	public void setPubblicata(boolean pubblicata) {
		this.pubblicata = pubblicata;
	}
}
