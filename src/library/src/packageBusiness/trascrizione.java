package packageBusiness;

/**
 * Classe Business Trascrizione 
 * La classe rappresenta una qualsiasi trascrizione
 */
public class trascrizione {
	
	private String testo;
	 private String titolo_opera;
	 private int numero_pagina;
	 private String data_scrittura;
	 private String trascrittore;
	 private boolean validata;
	 
	 /**
	  * Construttore che prende in input tutti i dati che una trascrizione può contenere 
	  * 
	  * @param testo Stringa in TEI 
	  * @param titolo_opera Stringa che rappresenta il titolo dell'opera a cui la trascrizione fa riferimento
	  * @param numero_pagina Intero che rappresenta la pagina dell'opera a cui la trascrizione fa riferimento
	  * @param data_scrittura Stringa immessa dal trascrittore che mostra la data in cui la trascrizione è stata inserita nel sistema 
	  * @param trascrittore Stringa che mostra l'email del trascrittore 
	  * @param validata Booleano che mostra se la trascrizione è stata validata oppure no
	  */
 public trascrizione(String testo, String titolo_opera, int numero_pagina, String data_scrittura,
			String trascrittore, boolean validata) {
		this.testo = testo;
		this.titolo_opera = titolo_opera;
		this.numero_pagina = numero_pagina;
		this.data_scrittura = data_scrittura;
		this.trascrittore = trascrittore;
		this.validata = validata;
	}

 /**
  * Il metodo ritorna il Testo TEI della trascrizione
  * 
  * @return Stringa TEI
  */
public String getTesto() {
	return testo;
}

/**
 * Il metodo setta il Testo TEI della trascrizione
 * 
 * @param testo Stringa TEI
 */
public void setTesto(String testo) {
	this.testo = testo;
}

/**
 * Il metodo ritorna il titolo dell'opera a cui la trascrizione fa riferimento
 * 
 * @return Stringa che rappresenta il titolo dell'opera 
 */
public String getTitolo_opera() {
	return titolo_opera;
}

/**
 * Il metodo setta il titolo dell'opera a cui la trascrizione fa riferimento
 * 
 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
 */
public void setTitolo_opera(String titolo_opera) {
	this.titolo_opera = titolo_opera;
}

/**
 * Il metodo ritorna il numero di pagina dell'opera a cui la trascrizione fa riferimento
 * 
 * @return Intero che rappresenta il numero di pagina nell'opera 
 */
public int getNumero_pagina() {
	return numero_pagina;
}

/**
 * Il metodo setta il numero di pagina dell'opera a cui la trascrizione fa riferimento
 * 
 * @param numero_pagina Intero che rappresenta il numero di pagina nell'opera 
 */
public void setNumero_pagina(int numero_pagina) {
	this.numero_pagina = numero_pagina;
}

/**
 * Il metodo ritorna la data in cui la trascrizione è stata scritta
 * 
 * @return Stringa immessa dal trascrittore che mostra la data in cui la trascrizione è stata inserita nel sistema
 */
public String getData_scrittura() {
	return data_scrittura;
}

/**
 * Il metodo setta la data in cui la trascrizione è stata scritta
 * 
 * @param data_scrittura Stringa immessa dal trascrittore che mostra la data in cui la trascrizione è stata inserita nel sistema
 */
public void setData_scrittura(String data_scrittura) {
	this.data_scrittura = data_scrittura;
}

/**
 * Il metodo ritorna l'email del trascrittore 
 * 
 * @return Stringa che mostra l'email del trascrittore 
 */
public String getTrascrittore() {
	return trascrittore;
}

/**
 * Il metodo setta l'email del trascrittore 
 * 
 * @param trascrittore Stringa che mostra l'email del trascrittore 
 */
public void setTrascrittore(String trascrittore) {
	this.trascrittore = trascrittore;
}

/**
 * Il metodo ritorna il booleano che mostra se la trascrizione è stata validata o meno
 * 
 * @return Booleano che mostra se la trascrizione è stata validata oppure no
 */
public boolean isValidata() {
	return validata;
}

/**
 * Il metodo ritorna il booleano che mostra se la trascrizione è stata validata o meno
 * 
 * @param validata Booleano che mostra se la trascrizione è stata validata oppure no
 */
public void setValidata(boolean validata) {
	this.validata = validata;
}
}
