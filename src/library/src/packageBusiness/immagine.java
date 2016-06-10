package packageBusiness;

import java.awt.image.BufferedImage;
import java.sql.Date;

public class immagine {
	
	private BufferedImage immagine; 
	private Date dataScatto; 
	private int altezza; 
	private int larghezza;

	public immagine(BufferedImage immagine, Date dataScatto, int altezza, int larghezza) {
		
		this.immagine = immagine;
		this.dataScatto = dataScatto;
		this.altezza = altezza;
		this.larghezza = larghezza;
	}
	

	public BufferedImage getImmagine() {
		return this.immagine;
	}
	
	public void setImmagine(BufferedImage immagine) {
		this.immagine = immagine;
	}
	
	public Date getDataScatto() {
		return this.dataScatto;
	}
	
	public void setDataScatto(Date dataScatto) {
		this.dataScatto = dataScatto;
	}
	
	public int getAltezza() {
		return this.altezza;
	}
	
	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}
	
	public int getLarghezza() {
		return this.larghezza;
	}
	
	public void setLarghezza(int larghezza) {
		this.larghezza = larghezza;
	} 
	
}
