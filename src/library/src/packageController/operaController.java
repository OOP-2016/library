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
import packageView.operaView;


/**
 * Classe controller operaController 
 */
public class operaController {

	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void exitAction(operaPage finestra){
		new operaView().dispose(finestra);
	}
	
	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOutAction(operaPage finestra){
		new operaView().dispose(finestra);
		new operaView().istanziaLoginPage();
	}
	
	public void indietroAction(operaPage finestra, utente user){
		new operaView().dispose(finestra);
		new operaView().istanziaRicercaPage(user);
	}
	
	public Object[] vistaAction(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		immagine immagine = (immagine)new immagineDAO().retrieve(args);
	    trascrizione trascrizione = (trascrizione)new trascrizioneDAO().retrieve(args);
		Object[] pagina = new Object[2];
		pagina[0] = immagine;
		pagina[1] = trascrizione;
		return pagina;
	}
	
	public opera getOpera(String titolo, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
 		
		return opera; 
	}
	
}
