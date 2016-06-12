package packageBusiness;

import java.awt.image.BufferedImage;
import java.sql.Date;

public class immagine {
	
	private BufferedImage immagine; 
	private String dataScatto; 
	private String risoluzione; 

	public immagine(BufferedImage immagine, String dataScatto, String risoluzione) {
		
		this.immagine = immagine;
		this.dataScatto = dataScatto;
		this.risoluzione = risoluzione;
	}
	

	public BufferedImage getImmagine() {
		return this.immagine;
	}
	
	public void setImmagine(BufferedImage immagine) {
		this.immagine = immagine;
	}
	
	public String getDataScatto() {
		return this.dataScatto;
	}
	
	public void setDataScatto(String dataScatto) {
		this.dataScatto = dataScatto;
	}
	
	public String getRisoluzione() {
		return this.risoluzione;
	}
	
	public void setRisoluzione(String risoluzione) {
		this.risoluzione = risoluzione;
	}
	
}
