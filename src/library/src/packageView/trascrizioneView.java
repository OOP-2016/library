package packageView;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.trascrizione;
import packageBusiness.utente;
import packageController.acquisizioneController;
import packageController.operaController;
import packageController.trascrizioneController;
import packageGUI.acquisizionePage;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.ricercaPage;
import packageGUI.trascrizionePage;

/**
 * Classe View trascrizioneView
 */
public class trascrizioneView {
		
	/**
	 * Il metodo istanzia il controller trascrizioneController
	 * 
	 * @param finestra Finestra trascrizionePage da chiudere 
	 */
	public void exit(trascrizionePage finestra){
		new trascrizioneController().exitAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia il controller trascrizioneController
	 * 
	 * @param finestra Finestra trascrizionePage da chiudere 
	 */
	public void logOut(trascrizionePage finestra){
		new trascrizioneController().logOutAction(finestra); 
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
	 * @param finestra Finestra trascrizionePage da chiudere 
	 */
	public void dispose(trascrizionePage finestra){
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
	 * Il metodo istanzia il controller trascrizioneController
	 * 
	 * @param finestra Finestra trascrizionePage da chiudere 
	 * @param user Utente che ha effettuato l'accesso nel sistema 
	 */
	public void indietro(trascrizionePage finestra, utente user){
		new trascrizioneController().indietroAction(finestra, user);
	}
	
	/**
	 * Il metodo istanzia trascrizioneController
	 * 
	 * @param immagine Riquadro dove verrà visualizzata l'immagine
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param numero_pagina intero che rappresenta il corrente numero di pagina
	 * @param utente utente che ha effettuato l'accesso
	 */
	public void vistaimmagine(JLabel immagine, String titolo_opera, int numero_pagina, utente utente){
		immagine immagine_inter = new trascrizioneController().vistaActionimmagine(titolo_opera, numero_pagina, utente);
		
		BufferedImage immagine_def = immagine_inter.getImmagine();
		Image im = null; 
		im = immagine_def.getScaledInstance(395, 569,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		
		//new dialog().infoDialog(immagine.getWidth() + "x" + immagine.getHeight());
		
		immagine.setIcon(new ImageIcon(im));

	}
	
	/**
	 * Il metodo istanzia trascrizioneController e funge da editor per il trascrittore
	 * 
	 * @param trascrizione Riquadro dove verrà visualizzata la trascrizione
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param numero_pagina intero che rappresenta il corrente numero di pagina
	 * @param utente utente che ha effettuato l'accesso
	 */
	public void vistatrascrizione(JTextPane trascrizione, JTextField data, String titolo_opera, int numero_pagina, utente utente){
		trascrizione trascrizione_def = new trascrizioneController().vistaActiontrascrizione(titolo_opera, numero_pagina, utente);
		
		String trascrizionetei = trascrizione_def.getTesto();
		String data_scrittura = trascrizione_def.getData_scrittura(); 
		
		StringBuilder stringa = new StringBuilder();
		stringa.append(trascrizionetei); 
		trascrizione.setText(stringa.toString());
		
		data.setText(data_scrittura);
	}
	
	/**
	 * metodo che permette di visualizzare la prima pagina di un'opera
	 * 
	 * @param titolo titolo dell'opera in considerazione
	 * @param avanti bottone che permette di passare alla pagina successiva
	 * @param page contiene il numero dell'ultima pagina di un'opera
	 * @param currentpage permette di scegliere quale pagina visualizzare
	 * @param npagina numero di pagina attuale
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int firstPage(String titolo, JButton avanti, JLabel page, JTextField currentpage, int npagina, utente utente){
		opera opera = new trascrizioneController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine();  
		page.setText(" / " + pageMax);  
		currentpage.setText(String.format("%d", npagina));
		if(pageMax == 1) avanti.setEnabled(false);  
		return npagina; 
	}
	
	/**
	 * metodo che permette di visualizzare la pagina successiva di un'opera
	 * 
	 * @param titolo titolo dell'opera in considerazione
	 * @param page contiene il numero dell'ultima pagina di un'opera
	 * @param currentpage permette di scegliere quale pagina visualizzare
	 * @param npagina numero di pagina attuale
	 * @param avanti bottone che permette di passare alla pagina successiva
	 * @param indietro bottone che permette di passare alla pagine precedente
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int clickAvanti(String titolo, JLabel page, JTextField currentpage, int npagina, JButton avanti, JButton indietro, utente utente){
		opera opera = new trascrizioneController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine();  
		
		indietro.setEnabled(true); //abilito bottone indietro
		
		npagina+=1; 
		if(pageMax == npagina) avanti.setEnabled(false);
		
		page.setText(" / " + pageMax);  
		currentpage.setText(String.format("%d", npagina));
		
		return npagina; 
	}
	
	/**
	 * metodo che permette di visualizzare la pagina precedente di un'opera
	 * 
	 * @param titolo titolo dell'opera in considerazione
	 * @param page contiene il numero dell'ultima pagina di un'opera
	 * @param currentpage permette di scegliere quale pagina visualizzare
	 * @param npagina numero di pagina attuale
	 * @param avanti bottone che permette di passare alla pagina successiva
	 * @param indietro bottone che permette di passare alla pagine precedente
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int clickIndietro(String titolo, JLabel page, JTextField currentpage, int npagina, JButton avanti, JButton indietro, utente utente){

		avanti.setEnabled(true); //abilita bottone avanti
		
		npagina-=1; 
		
		opera opera = new trascrizioneController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine();  
		
		
		page.setText(" / " + pageMax);  
		currentpage.setText(String.format("%d", npagina)); 
		
		if(npagina == 1){
			indietro.setEnabled(false); //disabilito bottone indietro 
			return npagina; 
		}
		
		return npagina; 
		
	}
	
	/**
	 * metodo che permette di conoscere il numero di pagine di un'opera
	 * 
	 * @param titolo titolo dell'opera in considerazione
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int getPageMax(String titolo, utente utente){
		opera opera = new trascrizioneController().getOpera(titolo, utente); 
		return opera.getNumero_pagine(); 
	}
	
	/**
	 * metodo che istanzia trascrizione controller
	 * 
	 * @param data_scrittura data in cui è stata effettuata la trascrizione
	 * @param TEItext trascrizione effettiva
	 * @param numero_pagina numero di pagina della trascrizione
	 * @param titolo titolo dell'opera in considerazione
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo boolean
	 */
	public boolean conferma(String data_scrittura, String TEItext, int numero_pagina, String titolo, utente utente){
		boolean success;
		if(data_scrittura.length()==0||TEItext.length()==0){
			new dialog().errorDialog("Campo data o trsacrizione mancante");
			return false; 
		}
		
		trascrizione trascrizione = new trascrizione(TEItext, titolo, numero_pagina, data_scrittura, utente.getEmail(), false);	
		success = new trascrizioneController().confermaAction(trascrizione, utente);
	    
		return success;
	}
	
	/** metodo che istanzia trascrizione controller
	 * 
	 * @param numero_pagina numero di pagina corrente
	 * @param titolo_opera titolo dell'opera in considerazione
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo booleano
	 */
	public boolean esistePagina(int numero_pagina, String titolo_opera, utente utente){
		boolean p = new trascrizioneController().esistePaginaAction(numero_pagina, titolo_opera, utente);
		return p;
	}
	
	/**
	 * metodo che istanzia trascrizioneController e, in seguito, istanzia una pagina di ricerca
	 * 
	 * @param titolo_opera titolo dell'opera in considerazione
	 * @param utente utente che ha effettuato l'accesso
	 * @param finestra finestra da chiuedere
	 * @return ritorna un valore di tipo booleano
	 */
	public boolean tutteTrascritte(String titolo_opera, utente utente, JFrame finestra){
		boolean tutteTrascritte = new trascrizioneController().tutteTrascritteAction(titolo_opera, utente); 
		
		if(tutteTrascritte){
			new dialog().infoDialog("Tutte le trascrizioni di quest'opera sono state trascritte");
			if(finestra instanceof trascrizionePage){
				new dialog().disposeDialog(finestra);
				new trascrizioneView().istanziaRicercaPage(utente);
			}
		}
		
		return tutteTrascritte; 
	}
	
}
