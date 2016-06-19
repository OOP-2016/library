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
import packageController.trascrizioneController;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.revisione_aPage;
import packageGUI.ricercaPage;

/**
 * Classe View revisione_aView
 */
public class revisione_aView {
		
	/**
	 * Il metodo istanzia il controller revisione_aController
	 * 
	 * @param finestra Finestra revisione_aPage da chiudere 
	 */
	public void exit(revisione_aPage finestra){
		new revisione_aController().exitAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia il controller revisione_aController
	 * 
	 * @param finestra Finestra revisione_aPage da chiudere 
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
	 * Il metodo permette di invocare una nuova finestra di ricercaPage
	 */
	public void istanziaRicercaPage(utente user){
	
		ricercaPage finestra = new ricercaPage(user);
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
	/**
	 * Il metodo istanzia la classe dialog che si occuperà di chiudere la finestra
	 * 
	 * @param finestra Finestra revisione_aPage da chiudere 
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
	 * Il metodo istanzia il controller revisione_aController
	 * 
	 * @param finestra Finestra revisione_aPage da chiudere 
	 * @param user Utente che ha effettuato l'accesso nel sistema 
	 */
	public void indietro(revisione_aPage finestra, utente user){
		new revisione_aController().indietroAction(finestra, user);
	}
	
	/**
	 * Il metodo istanzia revisione_aController, ottentendo un'immagine che utilizzera per riempire la JLabel immagine
	 * 
	 * @param immagine Riquadro dove verrà visualizzata l'immagine
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
	
	/**
	 * il metodo carica la prima pagina di un'oera
	 * 
	 * @param titolo titolo dell'opera da visualizzare
	 * @param avanti bottone che permette di passare alla pagina successiva
	 * @param page contiene il numero dell'ultima pagine dell'opera
	 * @param page2 permette di scegliere la pagina da visualizzare
	 * @param npagina contiene la pagina attuale 
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
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
	 * il metodo permette di passare alla pagina successiva
	 * 
	 * @param titolo titolo dell'opera da visualizzare
	 * @param page contiene il numero dell'ultima pagine dell'opera
	 * @param page2 permette di scegliere la pagina da visualizzare
	 * @param npagina contiene la pagina attuale
	 * @param avanti bottone che permette di passare alla pagina successiva
	 * @param jbutton indietro bottone che permette di passare alla pagina precedente 
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int clickAvanti(String titolo, JLabel page, JTextField page2,int npagina, JButton avanti, JButton indietro, utente utente){
		opera opera = new revisione_aController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine();  
		
		indietro.setEnabled(true); //abilito bottone indietro
		
		npagina+=1; 
		if(pageMax == npagina) avanti.setEnabled(false);
		
		page.setText(" / " + pageMax); 
		page2.setText(""+npagina);
		
		return npagina; 
	}
	
	/**
	 * il metodo permette di ottenere i metadati di un'acquisizione istanziando revisione_aController
	 * 
	 * @param TF1 textfield su cui verranno visualizzati i metadati
	 * @param TF2 textfield su cui verrano visualizzati i metadati
	 * @param TF3 textfield su cui verrano visualizzati i metadati
	 * @param titolo_opera titolo dell'opera in considerazione
	 * @param numero_pagina numero della pagina corrente
	 */
	public void metadati(JTextField TF1, JTextField TF2, JTextField TF3, String titolo_opera, int numero_pagina){
		String[] metadati = new revisione_aController().metadatiAction(titolo_opera, numero_pagina);
		TF1.setText(metadati[0]);
		TF2.setText(metadati[1]);
		TF3.setText(metadati[2]);
	}
	
	/**
	 * il metodo permette di passare alla pagina precedente
	 * 
	 * @param titolo titolo dell'opera da visualizzare
	 * @param page contiene il numero dell'ultima pagine dell'opera
	 * @param page2 permette di scegliere la pagina da visualizzare
	 * @param npagina contiene la pagina attuale
	 * @param avanti bottone che permette di passare alla pagina successiva
	 * @param jbutton indietro bottone che permette di passare alla pagina precedente 
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int clickIndietro(String titolo, JLabel page, JTextField page2,int npagina, JButton avanti, JButton indietro, utente utente){

		avanti.setEnabled(true); //abilita bottone avanti
		
		npagina-=1; 
		
		opera opera = new revisione_aController().getOpera(titolo, utente); 
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
	 * il metodo ottiene un'immagine istanziando revisione_aController
	 * 
	 * @param titolo_opera titolo dell'opera in considerazione
	 * @param numero_pagina numero della pagina in revisione
	 * @return ritorna un valore di tipo Stringa
	 */
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
	
	/**
	 * il metodo istanzia revisione_aController
	 * 
	 * @param titolo_opera titolo dell'opera in considerazione
	 * @param numero_pagina numero della pagina in revisione
	 * @param validation valore che permette di sapere se l'acquisizione è stata già validata
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo booleano
	 */
	public boolean conferma(String titolo_opera, int numero_pagina, boolean validation, utente utente){
		return new revisione_aController().confermaAction(titolo_opera, numero_pagina, validation, utente); 
	}
	
	/**
	 * il metodo istanzia revisione_aController
	 * 
	 * @param titolo_opera titolo dell'opera in considerazione
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo booleano
	 */
	public boolean validaOpera(String titolo_opera, utente utente){
		return new revisione_aController().validaOperaAction(titolo_opera, utente); 
	}
	
	/**
	 * metodo che permette di conoscere il numero di pagine di un'opera
	 * 
	 * @param titolo titolo dell'opera in considerazione
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int getPageMax(String titolo, utente utente){
		opera opera = new revisione_aController().getOpera(titolo, utente); 
		return opera.getNumero_pagine(); 
	}
	
}
