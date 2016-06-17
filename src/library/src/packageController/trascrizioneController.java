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
import packageView.acquisizioneView;
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
	public immagine vistaActionimmagine(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		immagine immagine = (immagine)new immagineDAO().retrieve(args);
		return immagine;
	}
	
	public trascrizione vistaActiontrascrizione(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		trascrizione trascrizione = (trascrizione)new trascrizioneDAO().retrieve(args);
		return trascrizione;
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
	
	public boolean confermaAction(trascrizione trascrizione, utente utente){
	
			
			boolean exist = new trascrizioneView().esistePagina(trascrizione.getNumero_pagina(), trascrizione.getTitolo_opera(), utente); 
			
			if(exist){
				new trascrizioneView().errorMessage("trascrizione già effettuata per questa pagina");
				return false;
			}	
					
			
			ArrayList<Object> trascrizioneArgs = new ArrayList<Object>();
			trascrizioneArgs.add(trascrizione);
			boolean success = new trascrizioneDAO().insert(trascrizioneArgs); 
			
			if(success){
				new trascrizioneView().infoMessage("trascrizione effettuata con successo");
				return true; 
			}
			else {
				new trascrizioneView().errorMessage("trascrizione fallita");
				return false; 
			}
			
		} 

	public boolean tutteTrascritteAction(String titolo_opera, utente utente){
		
		ArrayList<Object> args = new ArrayList<Object>();  
		args.add(titolo_opera); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
		int numero_pagine = opera.getNumero_pagine(); 
		
		args.clear();
		args.add(titolo_opera); 
		args.add(numero_pagine); 
		args.add(utente); 
		
		boolean pubblicazione = new trascrizioneDAO().controllaValidate(args); 
			
		return pubblicazione; 
	}
	
	public boolean esistePaginaAction(int numero_pagina, String titolo_opera, utente utente){
	ArrayList<Object> trascrizioneArgs = new ArrayList<Object>();
	trascrizione trascrizione;
	trascrizioneArgs.add(numero_pagina);
	trascrizioneArgs.add(titolo_opera);
	trascrizioneArgs.add(utente);
	trascrizione = (trascrizione)new trascrizioneDAO().retrieve(trascrizioneArgs);
	if(!trascrizione.getTesto().equals("<h2>Testo non disponibile</h2>")) return true;
	return false;
}
	
}
