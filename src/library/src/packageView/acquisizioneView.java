package packageView;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.utente;
import packageController.acquisizioneController;
import packageController.operaController;
import packageController.revisione_aController;
import packageGUI.acquisizionePage;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.ricercaPage;

/**
 * classe acquiisizioneView
 */
public class acquisizioneView {
	
	/**
	 *  il metodo chiama il acquiizioneController, passandogli il numero della pagina e il titolo dell'opera in considerazione
	 *  
	 * @param numero_pagina numero de3lla pagina in acquisizione
	 * @param titolo_opera titolo dell'opera di cui stiamo effettuando l'acquisizione
	 * @return ritorna un valore di tipo boolean
	 */
public boolean esistePagina(int numero_pagina, String titolo_opera){
	boolean p = new acquisizioneController().esistePaginaAction(numero_pagina, titolo_opera);
	return p;
}

/**
 * il metodo chiama il controller passandogli le variabili che permetteranno di inserire un'acquisizione nel database
 * 
 * @param risoluzione variabile che rappresenta la risoluzione dell'acquisizione effettuata
 * @param data_scatto variabile che contiene la data in cui è stata effettuata l'acquisizione
 * @param numero_pagina variabile che contiene il numero della pagina acquisita
 * @param opera oggetto che contiene l'opera presa in considerazione
 * @param immagine variabile che contiene l'immagine acquisita
 * @param acquisitore oggetto che contiene l'utente che ha effettuato l'acquisizione
 * @exception Exception in caso di fallimento della conversione, verrà stampato un messaggio di errore
 * @return il metodo ritorna un boolean in caso di successo delle operazioni effettuate, false altrimenti
 */
public boolean conferma(String risoluzione, String data_scatto, String numero_pagina, opera opera, BufferedImage immagine, String acquisitore){
    boolean success;
	if(risoluzione.length()==0||data_scatto.length()==0||numero_pagina.length()==0||immagine==null){
		new dialog().errorDialog("Campi o immagine mancanti");
		return false; 
	}
	
	try {
    	
		int num_pagina = Integer.parseInt(numero_pagina);
		immagine campo_immagine = new immagine(immagine, num_pagina, opera.getTitolo(), data_scatto, risoluzione, false, acquisitore);	
		success = new acquisizioneController().confermaAction(risoluzione,data_scatto, numero_pagina, opera, campo_immagine);
    
    } catch(Exception e){
			new dialog().errorDialog("Errori nei campi");
			return false; 
	}
	return success;
}

/**
 * il metodo chiama un open file dialog
 * 
 * @param labelImmagine label in cui verrà inserita l'anteprima dell'imagine
 * @param finestra finestra da cui proviene la chiamata del metodo in questione
 * @return il metodo ritorna l'immagine scelta tramite l'open file dialog
 */
public BufferedImage carica(JLabel labelImmagine, Component finestra){
	
	BufferedImage immagine = new dialog().fileChooser(labelImmagine , finestra); 
	return immagine; 
}

/**
 * Il metodo istanzia acquisizioneController
 * 
 * @param finestra Finestra di acquisizione da chiudere
 */
public void exit(acquisizionePage finestra){
	new acquisizioneController().exitAction(finestra); 
}

/**
 * Il metodo istanzia acquisizioneController
 * 
 * @param finestra Finestra di acquisizione da chiudere quando viene effettuato il logout
 */
public void logOut(acquisizionePage finestra){
	new acquisizioneController().logOutAction(finestra); 
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
 * Il metodo istanzia la classe dialog che si occuperà di chiudere la finestra
 * 
 * @param finestra Finestra di acquisizione da chiudere
 */
public void dispose(acquisizionePage finestra){
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
 * Il metodo istanzia il controller acquisizioneController
 * 
 * @param finestra Finestra acquisizionePage da chiudere 
 * @param user Utente che ha effettuato l'accesso nel sistema 
 */
public void indietro(acquisizionePage finestra, utente user){
	new acquisizioneController().indietroAction(finestra, user);
}

/**
 * Il metodo permette di invocare una nuova finestra di loginPage
 * 
 * @param utente utente che ha effettuato l'accesso nel sistema
 */
public void istanziaRicercaPage(utente user){

	ricercaPage finestra = new ricercaPage(user);
	finestra.setVisible(true);
	finestra.setResizable(false);
	
}

/**
 * 
 * Il metodo chiama acquisizioneController passandogli l'opera da controllare, l'utente che ha effettuato l'accesso e la finestra da cui proviene la chiamata
 * 
 * @param titolo_opera titolo dell'opera da controllare
 * @param utente utente che ha effettuato l'accesso nel sistema
 * @param finestra finestra da chiudere
 * @return ritorna un valore di tipo booleano
 */
public boolean tutteAcquisite(String titolo_opera, utente utente, JFrame finestra){
	boolean tutteAcquisite = new acquisizioneController().tutteAcquisiteAction(titolo_opera, utente); 
	
	if(tutteAcquisite){
		new dialog().infoDialog("Tutte le immagini di quest'opera sono state acquisite");
		if(finestra instanceof acquisizionePage){
			new dialog().disposeDialog(finestra);
			new acquisizioneView().istanziaRicercaPage(utente);
		}
	}
	
	return tutteAcquisite; 
}

/**
 * il metodo istanzia acquisizioneController
 * 
 * @param titolo_opera titolo dell'opera in considerazione
 * @return ritorna un valore di tipo intero
 */
public int paginaDaAcquisire(String titolo_opera){
	return new acquisizioneController().paginaDaAcquisireAction(titolo_opera); 
}

}
