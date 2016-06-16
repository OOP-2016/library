package packageBusiness;

import java.awt.image.BufferedImage;
import java.sql.Date;

/**
 * Classe Business immagine
 */
public class immagine {
	private BufferedImage immagine; 
	private int numero_pagina;
	private String titolo_opera;
	private String data_scatto; 
	private String risoluzione; 
	private boolean validata;
	private String acquisitore;
	
	/**
	 * 
	 * @param immagine
	 * @param numero_pagina
	 * @param titolo_opera
	 * @param data_scatto
	 * @param risoluzione
	 * @param validata
	 * @param acquisitore
	 */
	public immagine(BufferedImage immagine, int numero_pagina, String titolo_opera, String data_scatto, String risoluzione, boolean validata,
			String acquisitore) {
		this.immagine = immagine;
		this.numero_pagina = numero_pagina;
		this.titolo_opera = titolo_opera;
		this.data_scatto = data_scatto;
		this.risoluzione = risoluzione;
		this.validata = validata;
		this.acquisitore = acquisitore;
	}

	/**
	 * 
	 * @return
	 */
	public BufferedImage getImmagine() {
		return immagine;
	}

	/**
	 * 
	 * @param immagine
	 */
	public void setImmagine(BufferedImage immagine) {
		this.immagine = immagine;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumero_pagina() {
		return numero_pagina;
	}

	/**
	 * 
	 * @param numero_pagina
	 */
	public void setNumero_pagina(int numero_pagina) {
		this.numero_pagina = numero_pagina;
	}

	/**
	 * 
	 * @return
	 */
	public String getTitolo_opera() {
		return titolo_opera;
	}

	/**
	 * 
	 * @param titolo_opera
	 */
	public void setTitolo_opera(String titolo_opera) {
		this.titolo_opera = titolo_opera;
	}

	/**
	 * 
	 * @return
	 */
	public String getData_scatto() {
		return data_scatto;
	}

	/**
	 * 
	 * @param data_scatto
	 */
	public void setData_scatto(String data_scatto) {
		this.data_scatto = data_scatto;
	}

	/**
	 * 
	 * @return
	 */
	public String getRisoluzione() {
		return risoluzione;
	}

	/**
	 * 
	 * @param risoluzione
	 */
	public void setRisoluzione(String risoluzione) {
		this.risoluzione = risoluzione;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isValidata() {
		return validata;
	}

	/**
	 * 
	 * @param validata
	 */
	public void setValidata(boolean validata) {
		this.validata = validata;
	}

	/**
	 * 
	 * @return
	 */
	public String getAcquisitore() {
		return acquisitore;
	}

	/**
	 * 
	 * @param acquisitore
	 */
	public void setAcquisitore(String acquisitore) {
		this.acquisitore = acquisitore;
	}
	
}