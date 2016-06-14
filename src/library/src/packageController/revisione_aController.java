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
import packageGUI.revisione_aPage;
import packageView.operaView;
import packageView.revisione_aView;


/**
 * Classe controller operaController 
 */
public class revisione_aController {

	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void exitAction(revisione_aPage finestra){
		new revisione_aView().dispose(finestra);
	}
	public String[] metadatiAction(String titolo_opera, int numero_pagina){
		ArrayList<Object> args = new ArrayList<Object>();
		String[] metadati = new String[3];
		args.add(numero_pagina);
		args.add(titolo_opera);
		immagine immagine = (immagine)new immagineDAO().retrieve(args);
		if(immagine == null){
			metadati[0] = "";
			metadati[1] = "";
			metadati[2] = "";
			}
		else{
		metadati[0] = immagine.getAcquisitore();
		metadati[1] = immagine.getData_scatto();
		metadati[2] = immagine.getRisoluzione();
			}
		return metadati;
	}
	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOutAction(revisione_aPage finestra){
		new revisione_aView().dispose(finestra);
		new revisione_aView().istanziaLoginPage();
	}
	
	public void indietroAction(revisione_aPage finestra, utente user){
		new revisione_aView().dispose(finestra);
		new revisione_aView().istanziaRicercaPage(user);
	}
	
	public immagine vistaAction(String titolo_opera, int numero_pagina){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		immagine immagine = (immagine)new immagineDAO().retrieve(args);
		return immagine;
	}
	
	public opera getOpera(String titolo, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
 		
		return opera; 
	}
	
}
