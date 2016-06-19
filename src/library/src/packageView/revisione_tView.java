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
import packageController.revisione_tController;
import packageController.trascrizioneController;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.revisione_aPage;
import packageGUI.revisione_tPage;
import packageGUI.ricercaPage;

/**
 * Classe View operaView
 */
public class revisione_tView {
		
	/**
	 * Il metodo istanzia il controller operaController
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void exit(revisione_tPage finestra){
		new revisione_tController().exitAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia il controller operaController
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOut(revisione_tPage finestra){
		new revisione_tController().logOutAction(finestra); 
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
	 * Il metodo istanzia la classe dialog che si occuper� di chiudere la finestra
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void dispose(revisione_tPage finestra){
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
	public void indietro(revisione_tPage finestra, utente user){
		new revisione_tController().indietroAction(finestra, user);
	}
	
	/**
	 * Il metodo istanzia operaController e setta operaPage in modo
	 * da accogliere l'immagine e la trascrizione da visualizzare all'utente
	 * 
	 * @param immagine Riquadro dove verr� visualizzata l'immagine
	 * @param trascrizione Riquadro dove verr� visualizzata la trascrizione 
	 * @param titolo Stringa che rappresenta il titolo dell'opera 
	 * @param npagina intero che rappresenta il corrente numero di pagina
	 */
	public void vistatrascrizione(JTextPane trascrizione, String titolo_opera, int numero_pagina, utente utente){
		trascrizione trascrizione_inter = new revisione_tController().vistaActiontrascrizione(titolo_opera, numero_pagina, utente);
		String TEItext = trascrizione_inter.getTesto();

		trascrizione.setEditable(false);
		trascrizione.setContentType("text/html");
		trascrizione.setText(TEItext);
		
	}
	
	public void vistaimmagine(JLabel immagine, String titolo_opera, int numero_pagina, utente utente){
		immagine immagine_inter = new revisione_tController().vistaActionimmagine(titolo_opera, numero_pagina, utente);
		
		BufferedImage immagine_def = immagine_inter.getImmagine();
		Image im = null; 
		im = immagine_def.getScaledInstance(395, 569,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		
		//new dialog().infoDialog(immagine.getWidth() + "x" + immagine.getHeight());
		
		immagine.setIcon(new ImageIcon(im));

	}
	
	/**
	 * 
	 * @param titolo
	 * @param avanti
	 * @param page
	 * @param npagina
	 * @param utente
	 * @return
	 */
	public int firstPage(String titolo, JButton avanti, JLabel page, JTextField page2, int npagina, utente utente){
		opera opera = new operaController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine(); 
		page.setText(" / " + pageMax); 
		page2.setText(""+npagina);
		
		if(pageMax == 1) avanti.setEnabled(false);  
		return npagina; 
	}
	
	/**
	 * 
	 * @param titolo
	 * @param page
	 * @param npagina
	 * @param avanti
	 * @param indietro
	 * @param utente
	 * @return
	 */
	public int clickAvanti(String titolo, JLabel page, JTextField page2, int npagina, JButton avanti, JButton indietro, utente utente){
		opera opera = new revisione_tController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine();  
		
		indietro.setEnabled(true); //abilito bottone indietro
		
		npagina+=1; 
		if(pageMax == npagina) avanti.setEnabled(false);
		
		page.setText(" / " + pageMax); 
		page2.setText(""+npagina);
		
		return npagina; 
	}
	
	/**
	 * 
	 * @param TF1
	 * @param TF2
	 * @param titolo_opera
	 * @param numero_pagina
	 * @param utente
	 */
	public void metadati(JTextField TF1, JTextField TF2, String titolo_opera, int numero_pagina, utente utente){
		String[] metadati = new revisione_tController().metadatiAction(titolo_opera, numero_pagina, utente);
		TF1.setText(metadati[0]);
		TF2.setText(metadati[1]);
	}
	
	/**
	 * 
	 * @param titolo
	 * @param page
	 * @param npagina
	 * @param avanti
	 * @param indietro
	 * @param utente
	 * @return
	 */
	public int clickIndietro(String titolo, JLabel page, JTextField page2, int npagina, JButton avanti, JButton indietro, utente utente){

		avanti.setEnabled(true); //abilita bottone avanti
		
		npagina-=1; 
		
		opera opera = new revisione_tController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine(); 
		
		
		page.setText(" / " + pageMax); 
		page2.setText(""+npagina);
		
		if(npagina == 1){
			indietro.setEnabled(false); //disabilito bottone indietro 
			return npagina; 
		}
		
		return npagina; 
		
	}
	
	/**
	 * 
	 * @param titolo_opera
	 * @param numero_pagina
	 * @param utente
	 * @return
	 */
	public String getValidazione (String titolo_opera, int numero_pagina, utente utente) {
		
		trascrizione trascrizione = new revisione_tController().vistaActiontrascrizione(titolo_opera, numero_pagina, utente);
		String risposta;
		
		if(trascrizione.getTesto().equals("<h2>Testo non disponibile</h2>")){
			risposta = "TRASCRIZIONE RIFIUTATA O NON PRESENTE";
			return risposta;
		}
		else if(trascrizione.isValidata()) {
			risposta = "TRASCRIZIONE VALIDATA IN PRECEDENZA";
			return risposta;
		}
		return "TRASCRIZIONE DISPONIBILE PER LA VALIDAZIONE";
		
	}
	
	/**
	 * 
	 * @param titolo_opera
	 * @param numero_pagina
	 * @param validation
	 * @param utente
	 * @return
	 */
	public boolean conferma(String titolo_opera, int numero_pagina, boolean validation, utente utente){
		return new revisione_tController().confermaAction(titolo_opera, numero_pagina, validation, utente); 
	}
	
	/**
	 * 
	 * @param titolo_opera
	 * @param utente
	 * @return
	 */
	public boolean validaOpera(String titolo_opera, utente utente){
		return new revisione_tController().validaOperaAction(titolo_opera, utente); 
	}
	
	public int getPageMax(String titolo, utente utente){
		opera opera = new revisione_tController().getOpera(titolo, utente); 
		return opera.getNumero_pagine(); 
	}
	
}