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
	
	/**
	 * Il metodo ha il compito di istanziare ricercaPage e di chiudere operaPage
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 * @param user utente che ha effettuato l'accesso al sistema 
	 */
	public void indietroAction(operaPage finestra, utente user){
		new operaView().dispose(finestra);
		new operaView().istanziaRicercaPage(user);
	}
	
	/**
	 * Il metodo ritorna l'immagine e la trascrizione di una pagina 
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo di un opera
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return
	 */
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
	
	/**
	 * Il metodo ritorna l'opera richiesta
	 * 
	 * @param titolo Stringa che rappresenta il titolo di un opera
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return opera richiesta 
	 */
	public opera getOpera(String titolo, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
 		
		return opera; 
	}
	
}
