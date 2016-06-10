package packageView;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import packageBusiness.pagina;
import packageBusiness.utente;
import packageController.operaController;
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
	
	public void indietro(operaPage finestra, utente user){
		new operaController().indietroAction(finestra, user);
	}
	
	public void vista(JLabel immagine, JTextPane trascrizione, String titolo, int npagina){
		pagina pagina = new operaController().vistaAction(titolo, npagina); 
		BufferedImage img = pagina.getImmagine().getImmagine(); 
		Image im = null; 
		im = img.getScaledInstance(405, 439,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		
		//JOptionPane.showMessageDialog(null, lblNewLabel.getWidth() + "x" + lblNewLabel.getHeight());
		
		immagine.setIcon(new ImageIcon(im));

		StringBuilder stringa = new StringBuilder();
		stringa.append(pagina.getTrascrizione()); 
		trascrizione.setEditable(false);
		trascrizione.setContentType("text/html");
		trascrizione.setText(stringa.toString());
		
		
	}

}
