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
	
	/**
	 * 
	 * @param titolo_opera
	 * @param numero_pagina
	 * @return
	 */
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
	
	/**
	 * 
	 * @param finestra
	 * @param user
	 */
	public void indietroAction(revisione_aPage finestra, utente user){
		new revisione_aView().dispose(finestra);
		new revisione_aView().istanziaRicercaPage(user);
	}

	/**
	 * 
	 * @param titolo_opera
	 * @param numero_pagina
	 * @return
	 */
	public immagine vistaAction(String titolo_opera, int numero_pagina){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
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
	
	/**
	 * 
	 * @param titolo_opera
	 * @param numero_pagina
	 * @param validation
	 * @param utente
	 * @return
	 */
	public boolean confermaAction(String titolo_opera, int numero_pagina, boolean validation, utente utente){
		
		boolean success; 
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente.getEmail()); 
		
		if(validation){
			success = new immagineDAO().update(args); 
		} else {
			success = new immagineDAO().delete(args); 
		}
		
		if(success) 
			new revisione_aView().infoMessage("Conferma avvenuta");
		else 
			new revisione_aView().errorMessage("Conferma non avvenuta");; 
		
		return success; 
	}
	
	/**
	 * 
	 * @param titolo_opera
	 * @param utente
	 * @return
	 */
	public boolean validaOperaAction(String titolo_opera, utente utente){
		
		boolean success = false; 
		
		ArrayList<Object> args = new ArrayList<Object>();  
		args.add(titolo_opera); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
		int numero_pagine = opera.getNumero_pagine(); 
		
		args.clear();
		args.add(titolo_opera); 
		args.add(numero_pagine); 
		args.add(utente); 
		
		boolean pubblicazione = new immagineDAO().controllaValidate(args); 
		
		if(pubblicazione)
			success = new operaDAO().pubblica(args); 
		
		return success; 
	}
	
}
