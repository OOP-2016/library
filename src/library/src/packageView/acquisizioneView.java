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

public class acquisizioneView {
	
	/**
	 * 
	 * @param numero_pagina
	 * @param titolo_opera
	 * @return
	 */
public boolean esistePagina(int numero_pagina, String titolo_opera){
	boolean p = new acquisizioneController().esistePaginaAction(numero_pagina, titolo_opera);
	return p;
}

/**
 * 
 * @param risoluzione
 * @param data_scatto
 * @param numero_pagina
 * @param opera
 * @param immagine
 * @param acquisitore
 * @return
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
 * 
 * @param labelImmagine
 * @param finestra
 * @return
 */
public BufferedImage carica(JLabel labelImmagine, Component finestra){
	
	BufferedImage immagine = new dialog().fileChooser(labelImmagine , finestra); 
	return immagine; 
}

/**
 * Il metodo istanzia il controller operaController
 * 
 * @param finestra Finestra operaPage da chiudere 
 */
public void exit(acquisizionePage finestra){
	new acquisizioneController().exitAction(finestra); 
}

/**
 * Il metodo istanzia il controller operaController
 * 
 * @param finestra Finestra operaPage da chiudere 
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
 * @param finestra Finestra operaPage da chiudere 
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
 * Il metodo istanzia il controller operaController
 * 
 * @param finestra Finestra operaPage da chiudere 
 * @param user Utente che ha effettuato l'accesso nel sistema 
 */
public void indietro(acquisizionePage finestra, utente user){
	new acquisizioneController().indietroAction(finestra, user);
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
 * 
 * @param titolo_opera
 * @param utente
 * @param finestra
 * @return
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
 * 
 * @param titolo_opera
 * @return
 */
public int paginaDaAcquisire(String titolo_opera){
	return new acquisizioneController().paginaDaAcquisireAction(titolo_opera); 
}

}
