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
 * Classe controller revisione_tController 
 */
public class revisione_tController {

	/**
	 * Il metodo richiama la classe revisione_tView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra revisione_tPage da chiudere 
	 */
	public void exitAction(revisione_tPage finestra){
		new revisione_tView().dispose(finestra);
	}
	
	/**
	 * Il metodo ritorna un array di stringhe che rappresentano i metadati associati alla trascrizione
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return array di stringhe che rappresentano i metadati associati alla trascrizione
	 */
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
	 * Il metodo richiama la classe revisione_tView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra revisione_tPage da chiudere 
	 */
	public void logOutAction(revisione_tPage finestra){
		new revisione_tView().dispose(finestra);
		new revisione_tView().istanziaLoginPage();
	}
	
	/**
	 * Il metodo gestisce il click "Indietro"
	 * 
	 * @param finestra Finestra revisione_tPage da chiudere 
	 * @param user Utente che ha effettuato l'accesso al sistema 
	 */
	public void indietroAction(revisione_tPage finestra, utente user){
		new revisione_tView().dispose(finestra);
		new revisione_tView().istanziaRicercaPage(user);
	}
	
	/**
	 * Il metodo ritorna l'oggetto trascrizione richiesto 
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return l'oggetto trascrizione richiesta 
	 */
	public trascrizione vistaActiontrascrizione(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		trascrizione trascrizione = (trascrizione)new trascrizioneDAO().retrieve(args);
		return trascrizione;
	}
	
	/**
	 * Il metodo ritorna l'oggetto immagine richiesto 
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return l'oggetto immagine richiesta 
	 */
	public immagine vistaActionimmagine(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		immagine immagine = (immagine)new immagineDAO().retrieve(args);
		return immagine;
	}
	
	/**
	 * Il metodo ritorna l'oggetto opera richiesto 
	 * 
	 * @param titolo Stringa che rappresenta il titolo dell'opera 
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return l'oggetto opera richiesto 
	 */
	public opera getOpera(String titolo, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
 		
		return opera; 
	}
	
	/**
	 * Il metodo gestisce il click "Conferma" su revisione_tPage 
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param validation booleano che rappresenta la scelta del revisore 
	 * @param utente Utente che ha effettuato l'accesso al sistema
	 * @return booleano che mostra se l'operazione è andata a buon fine oppure no
	 */
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
	
	/**
	 * Il metodo gestisce la validazione dell'opera
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera
	 * @param utente Utente che ha effettuato l'accesso al sistema
	 * @return booleano che mostra se l'operazione è andata a buon fine oppure no
	 */
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
