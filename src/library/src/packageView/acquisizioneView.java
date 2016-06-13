package packageView;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.utente;
import packageController.acquisizioneController;
import packageController.operaController;
import packageGUI.acquisizionePage;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.ricercaPage;

public class acquisizioneView {
	
public boolean esistePagina(int numero_pagina, String titolo_opera){
	boolean p = new acquisizioneController().esistePaginaAction(numero_pagina, titolo_opera);
	return p;
}

public void conferma(String risoluzione, String data_scatto, String numero_pagina, opera opera, BufferedImage immagine, String acquisitore){
    int num_pagina = Integer.parseInt(numero_pagina);
	immagine campo_immagine = new immagine(immagine, num_pagina, opera.getTitolo(), data_scatto, risoluzione, false, acquisitore);	
	new acquisizioneController().confermaAction(risoluzione,data_scatto, numero_pagina, opera, campo_immagine);
		
}

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

}
