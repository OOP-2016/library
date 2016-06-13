package packageBusiness;

import java.awt.image.BufferedImage;
import java.sql.Date;

public class immagine {
	private BufferedImage immagine; 
	private int numero_pagina;
	private String titolo_opera;
	private String data_scatto; 
	private String risoluzione; 
	private boolean validata;
	private String acquisitore;
	
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

	public BufferedImage getImmagine() {
		return immagine;
	}

	public void setImmagine(BufferedImage immagine) {
		this.immagine = immagine;
	}

	public int getNumero_pagina() {
		return numero_pagina;
	}

	public void setNumero_pagina(int numero_pagina) {
		this.numero_pagina = numero_pagina;
	}

	public String getTitolo_opera() {
		return titolo_opera;
	}

	public void setTitolo_opera(String titolo_opera) {
		this.titolo_opera = titolo_opera;
	}

	public String getData_scatto() {
		return data_scatto;
	}

	public void setData_scatto(String data_scatto) {
		this.data_scatto = data_scatto;
	}

	public String getRisoluzione() {
		return risoluzione;
	}

	public void setRisoluzione(String risoluzione) {
		this.risoluzione = risoluzione;
	}

	public boolean isValidata() {
		return validata;
	}

	public void setValidata(boolean validata) {
		this.validata = validata;
	}

	public String getAcquisitore() {
		return acquisitore;
	}

	public void setAcquisitore(String acquisitore) {
		this.acquisitore = acquisitore;
	}
	
}