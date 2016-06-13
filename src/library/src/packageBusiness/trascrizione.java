package packageBusiness;

public class trascrizione {
	
	private String testo;
	 private String titolo_opera;
	 private int numero_pagina;
	 private String data_scrittura;
	 private String trascrittore;
	 private boolean validata;
	 
 public trascrizione(String testo, String titolo_opera, int numero_pagina, String data_scrittura,
			String trascrittore, boolean validata) {
		this.testo = testo;
		this.titolo_opera = titolo_opera;
		this.numero_pagina = numero_pagina;
		this.data_scrittura = data_scrittura;
		this.trascrittore = trascrittore;
		this.validata = validata;
	}

public String getTesto() {
	return testo;
}

public void setTesto(String testo) {
	this.testo = testo;
}

public String getTitolo_opera() {
	return titolo_opera;
}

public void setTitolo_opera(String titolo_opera) {
	this.titolo_opera = titolo_opera;
}

public int getNumero_pagina() {
	return numero_pagina;
}

public void setNumero_pagina(int numero_pagina) {
	this.numero_pagina = numero_pagina;
}

public String getData_scrittura() {
	return data_scrittura;
}

public void setData_scrittura(String data_scrittura) {
	this.data_scrittura = data_scrittura;
}

public String getTrascrittore() {
	return trascrittore;
}

public void setTrascrittore(String trascrittore) {
	this.trascrittore = trascrittore;
}

public boolean isValidata() {
	return validata;
}

public void setValidata(boolean validata) {
	this.validata = validata;
}
}
