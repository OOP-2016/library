package packageView;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.trascrizione;
import packageBusiness.utente;
import packageController.operaController;
import packageController.revisione_aController;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.revisione_aPage;
import packageGUI.ricercaPage;

/**
 * Classe View operaView
 */
public class revisione_aView {
		
	/**
	 * Il metodo istanzia il controller operaController
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void exit(revisione_aPage finestra){
		new revisione_aController().exitAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia il controller operaController
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOut(revisione_aPage finestra){
		new revisione_aController().logOutAction(finestra); 
	}
	
	/**
	 * Il metodo permette di invocare una nuova finestra di loginPage
	 */
	public void istanziaLoginPage(){

		loginPage finestra = new loginPage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
	/**
	 * Il metodo permette di invocare una nuova finestra di loginPage
	 */
	public void istanziaRicercaPage(utente user){
	
		ricercaPage finestra = new ricercaPage(user);
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
	/**
	 * Il metodo istanzia la classe dialog che si occuperà di chiudere la finestra
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void dispose(revisione_aPage finestra){
		new dialog().disposeDialog(finestra);
	}
	
	/**
	 * Il metodo richiama la classe AlertDialog che crea un MessageDialog di informazione 
	 * 
	 * @param info Stringa informativa
	 */
	public void infoMessage(String info){
		new dialog().infoDialog(info);
	}
	
	/**
	 * Il metodo richiama la classe AlertDialog che crea un MessageDialog di errore 
	 * 
	 * @param error Stringa di errore 
	 */
	public void errorMessage(String error){
		new dialog().errorDialog(error);
	}
	
	/**
	 * Il metodo istanzia il controller operaController
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 * @param user Utente che ha effettuato l'accesso nel sistema 
	 */
	public void indietro(revisione_aPage finestra, utente user){
		new revisione_aController().indietroAction(finestra, user);
	}
	
	/**
	 * Il metodo istanzia operaController e setta operaPage in modo
	 * da accogliere l'immagine e la trascrizione da visualizzare all'utente
	 * 
	 * @param immagine Riquadro dove verrà visualizzata l'immagine
	 * @param trascrizione Riquadro dove verrà visualizzata la trascrizione 
	 * @param titolo Stringa che rappresenta il titolo dell'opera 
	 * @param npagina intero che rappresenta il corrente numero di pagina
	 */
	public void vista(JLabel immagine, String titolo_opera, int numero_pagina){
		immagine immagine_inter = new revisione_aController().vistaAction(titolo_opera, numero_pagina);
		BufferedImage immagine_def = immagine_inter.getImmagine();
		if(immagine_def == null){
			immagine.setIcon(null);
			return;
		}
		Image im = null; 
		im = immagine_def.getScaledInstance(395, 569,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		
		//new dialog().infoDialog(immagine.getWidth() + "x" + immagine.getHeight());
		
		immagine.setIcon(new ImageIcon(im));

	}
	
	public int firstPage(String titolo, JButton avanti, JLabel page, int npagina, utente utente){
		opera opera = new operaController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine();  
		page.setText(npagina + " / " + pageMax);   				 		
		if(pageMax == 1) avanti.setEnabled(false);  
		return npagina; 
	}
	
	public int clickAvanti(String titolo, JLabel page, int npagina, JButton avanti, JButton indietro, utente utente){
		opera opera = new revisione_aController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine();  
		
		indietro.setEnabled(true); //abilito bottone indietro
		
		npagina+=1; 
		if(pageMax == npagina) avanti.setEnabled(false);
		
		page.setText(npagina + " / " + pageMax); 
		
		return npagina; 
	}
	
	public void metadati(JTextField TF1, JTextField TF2, JTextField TF3, String titolo_opera, int numero_pagina){
		String[] metadati = new revisione_aController().metadatiAction(titolo_opera, numero_pagina);
		TF1.setText(metadati[0]);
		TF2.setText(metadati[1]);
		TF3.setText(metadati[2]);
	}
	public int clickIndietro(String titolo, JLabel page, int npagina, JButton avanti, JButton indietro, utente utente){

		avanti.setEnabled(true); //abilita bottone avanti
		
		npagina-=1; 
		
		opera opera = new revisione_aController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine(); 
		
		
		page.setText(npagina + " / " + pageMax); 
		
		if(npagina == 1){
			indietro.setEnabled(false); //disabilito bottone indietro 
			return npagina; 
		}
		
		return npagina; 
		
	}
	
	public String getValidazione (String titolo_opera, int numero_pagina) {
		
		immagine immagine = new revisione_aController().vistaAction(titolo_opera, numero_pagina);
		String risposta;
		
		if(immagine.getImmagine() == null){
			risposta = "ACQUISIZIONE RIFIUTATA O NON PRESENTE";
			return risposta;
		}
		else if(immagine.isValidata()) {
			risposta = "ACQUISIZIONE VALIDATA IN PRECEDENZA";
			return risposta;
		}
		return "ACQUISIZIONE DISPONIBILE PER LA VALIDAZIONE";
		
	}
	
	public boolean conferma(String titolo_opera, int numero_pagina, boolean validation, utente utente){
		return new revisione_aController().confermaAction(titolo_opera, numero_pagina, validation, utente); 
	}
	
	public boolean validaOpera(String titolo_opera, utente utente){
		return new revisione_aController().validaOperaAction(titolo_opera, utente); 
	}
	
}
