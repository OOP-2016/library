package packageView;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.pagina;
import packageBusiness.utente;
import packageController.acquisizioneController;
import packageController.operaController;
import packageGUI.acquisizionePage;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.ricercaPage;

public class acquisizioneView {
	
public boolean esistePagina(int numeroPagina, String titolo){
	boolean p = new acquisizioneController().esistePaginaAction(numeroPagina, titolo);
	return p;
}

public void conferma(String risoluzione, String datascatto, String npagina, opera opera, BufferedImage immagine){

	immagine campoImmagine = new immagine(immagine,datascatto,risoluzione);	
	pagina pagina = new pagina(); 
	pagina.setImmagine(campoImmagine);
	
	new acquisizioneController().confermaAction(risoluzione,datascatto, npagina, opera, pagina);
		
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
