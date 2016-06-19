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
import packageController.revisione_tController;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.ricercaPage;

/**
 * Classe View operaView
 */
public class operaView {
		
	/**
	 * Il metodo istanzia il controller operaController
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void exit(operaPage finestra){
		new operaController().exitAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia il controller operaController
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOut(operaPage finestra){
		new operaController().logOutAction(finestra); 
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
	 * 
	 * @param utente utente che ha effettuato l'accesso
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
	public void dispose(operaPage finestra){
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
	public void indietro(operaPage finestra, utente user){
		new operaController().indietroAction(finestra, user);
	}
	
	/**
	 * Il metodo istanzia operaController e setta operaPage in modo
	 * da accogliere l'immagine e la trascrizione da visualizzare all'utente
	 * 
	 * @param immagine Riquadro dove verrà visualizzata l'immagine
	 * @param trascrizione Riquadro dove verrà visualizzata la trascrizione 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param numero_pagina intero che rappresenta il corrente numero di pagina
	 * @param utente utente che ha effetuato l'accesso nel sistema
	 */
	public void vista(JLabel immagine, JTextPane trascrizione, String titolo_opera, int numero_pagina, utente utente){
		Object[] pagina = new operaController().vistaAction(titolo_opera, numero_pagina, utente);
		immagine immagine_inter = (immagine)pagina[0];
		trascrizione trascrizione_def = (trascrizione)pagina[1];
		BufferedImage immagine_def = immagine_inter.getImmagine();
		Image im = null; 
		im = immagine_def.getScaledInstance(395, 569,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		
		//new dialog().infoDialog(immagine.getWidth() + "x" + immagine.getHeight());
		
		immagine.setIcon(new ImageIcon(im));

		StringBuilder stringa = new StringBuilder();
		stringa.append(trascrizione_def.getTesto()); 
		trascrizione.setEditable(false);
		trascrizione.setContentType("text/html");
		trascrizione.setText(stringa.toString());
		
		
	}
	
	/**
	 * il metodo apre la prima pagina di un'opera 
	 * 
	 * @param titolo titolo dell'opera da aprire
	 * @param avanti contiene il bottone che permette di cambiare pagina
	 * @param page contiene il valore dell'ultima pagina dell'opera
	 * @param page2 permette di inserire la pagina da visualizzare
	 * @param npagina numero della pagina corrente
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
	 * @param titolo titolo dell'opera presa in considerazione
	 * @param page numero dell'ultima pagina dell'opera
	 * @param page2 permette di scegliere la pagina da visualizzare
	 * @param npagina numero di pagina corrente
	 * @param avanti contiene il bottone che permette di passare alla pagina successiva
	 * @param indietro contiene il bottone che permette di passare alla pagina precedente
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int clickAvanti(String titolo, JLabel page, JTextField page2, int npagina, JButton avanti, JButton indietro, utente utente){
		opera opera = new operaController().getOpera(titolo, utente); 
		int pageMax = opera.getNumero_pagine();  
		
		indietro.setEnabled(true); //abilito bottone indietro
		
		npagina+=1; 
		if(pageMax == npagina) avanti.setEnabled(false);
		
		page.setText(" / " + pageMax); 
		page2.setText(""+npagina);
		
		return npagina; 
	}
	
	/**
	 * il metodo permette di passare alla pagina precedente
	 * 
	 * @param titolo titolo dell'opera presa in considerazione
	 * @param page numero dell'ultima pagina dell'opera
	 * @param page2 permette di scegliere la pagina da visualizzare
	 * @param npagina numero di pagina corrente
	 * @param avanti contiene il bottone che permette di passare alla pagina successiva
	 * @param indietro contiene il bottone che permette di passare alla pagina precedente
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int clickIndietro(String titolo, JLabel page, JTextField page2, int npagina, JButton avanti, JButton indietro, utente utente){

		avanti.setEnabled(true); //abilita bottone avanti
		
		npagina-=1; 
		
		opera opera = new operaController().getOpera(titolo, utente); 
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
	 * il metodo permette di conoscere il numero di pagine di un'opera
	 * 
	 * @param titolo titolo dell'opera di cui si vuole conoscere il numero di pagine
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un valore di tipo intero
	 */
	public int getPageMax(String titolo, utente utente){
		opera opera = new operaController().getOpera(titolo, utente); 
		return opera.getNumero_pagine(); 
	}
	
}
