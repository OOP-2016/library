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
import packageGUI.revisione_tPage;
import packageView.operaView;
import packageView.revisione_aView;
import packageView.revisione_tView;


/**
 * Classe controller operaController 
 */
public class revisione_tController {

	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void exitAction(revisione_tPage finestra){
		new revisione_tView().dispose(finestra);
	}
	public String[] metadatiAction(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>();
		String[] metadati = new String[2];
		args.add(numero_pagina);
		args.add(titolo_opera);
		args.add(utente);
		trascrizione trascrizione = (trascrizione)new trascrizioneDAO().retrieve(args);
		if(trascrizione == null){
			metadati[0] = "";
			metadati[1] = "";
			}
		else{
		metadati[0] = trascrizione.getTrascrittore();
		metadati[1] = trascrizione.getData_scrittura();
			}
		return metadati;
	}
	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra operaPage da chiudere 
	 */
	public void logOutAction(revisione_tPage finestra){
		new revisione_tView().dispose(finestra);
		new revisione_tView().istanziaLoginPage();
	}
	
	public void indietroAction(revisione_tPage finestra, utente user){
		new revisione_tView().dispose(finestra);
		new revisione_tView().istanziaRicercaPage(user);
	}
	
	public trascrizione vistaAction(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		trascrizione trascrizione = (trascrizione)new trascrizioneDAO().retrieve(args);
		return trascrizione;
	}
	
	public opera getOpera(String titolo, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
 		
		return opera; 
	}
	
	public boolean confermaAction(String titolo_opera, int numero_pagina, boolean validation, utente utente){
		
		boolean success; 
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente.getEmail()); 
		
		if(validation){
			success = new trascrizioneDAO().update(args); 
		} else {
			success = new trascrizioneDAO().delete(args); 
		}
		
		if(success) 
			new revisione_tView().infoMessage("Conferma avvenuta");
		else 
			new revisione_tView().errorMessage("Conferma non avvenuta");; 
		
		return success; 
	}
	
	public boolean validaOperaAction(String titolo_opera, utente utente){
		
		ArrayList<Object> args = new ArrayList<Object>();  
		args.add(titolo_opera); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
		int numero_pagine = opera.getNumero_pagine(); 
		
		args.clear();
		args.add(titolo_opera); 
		args.add(numero_pagine); 
		args.add(utente); 
		
		boolean tutteValidate = new trascrizioneDAO().controllaValidate(args); 
		
		return tutteValidate; 
	}
	
}
