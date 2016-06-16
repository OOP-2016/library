package packageController;

import java.util.ArrayList;

import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.trascrizione;
import packageBusiness.utente;
import packageDAO.immagineDAO;
import packageDAO.operaDAO;
import packageDAO.trascrizioneDAO;
import packageGUI.operaPage;
import packageGUI.trascrizionePage;
import packageView.operaView;
import packageView.trascrizioneView;


/**
 * Classe controller operaController 
 */
public class trascrizioneController {

	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void exitAction(trascrizionePage finestra){
		new trascrizioneView().dispose(finestra);
	}
	
	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOutAction(trascrizionePage finestra){
		new trascrizioneView().dispose(finestra);
		new trascrizioneView().istanziaLoginPage();
	}
	
	/**
	 * 
	 * @param finestra
	 * @param user
	 */
	public void indietroAction(trascrizionePage finestra, utente user){
		new trascrizioneView().dispose(finestra);
		new trascrizioneView().istanziaRicercaPage(user);
	}
	
	/**
	 * 
	 * @param titolo_opera
	 * @param numero_pagina
	 * @param utente
	 * @return
	 */
	public immagine vistaAction(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		immagine immagine = (immagine)new immagineDAO().retrieve(args);
		return immagine;
	}
	
	/**
	 * 
	 * @param titolo
	 * @param utente
	 * @return
	 */
	public opera getOpera(String titolo, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
 		
		return opera; 
	}
	
}
