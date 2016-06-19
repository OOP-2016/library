package packageController;

import java.util.ArrayList;

import javax.swing.JLabel;

import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.utente;
import packageDAO.immagineDAO;
import packageDAO.operaDAO;
import packageDAO.utenteDAO;
import packageGUI.acquisizionePage;
import packageGUI.dialog;
import packageGUI.operaPage;
import packageView.acquisizioneView;
import packageView.operaView;

/**
 * Classe acquisizioneController
 */
public class acquisizioneController {
	
	/**
	 * Il metodo ritorna true se la pagina numero_pagina in titolo_opera esiste 
	 * 
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera
	 * @return booleano
	 */
	public boolean esistePaginaAction(int numero_pagina, String titolo_opera){
	ArrayList<Object> immagine = new ArrayList<Object>();
	immagine immagine_obj;
	immagine.add(numero_pagina);
	immagine.add(titolo_opera);
	immagine_obj = (immagine)new immagineDAO().retrieve(immagine);
	if(immagine_obj.getImmagine()!= null) return true;
	return false;
}

/**
 * Il metodo richiama la classe acquisizioneView con il compito di chiudere la finestra 
 * 
 * @param finestra Finestra acquisizionePage da chiudere 
 */
public void exitAction(acquisizionePage finestra){
	new acquisizioneView().dispose(finestra);
}

/**
 * Il metodo richiama la classe acquisizioneView con il compito di chiudere la finestra e istanziare 
 * la finestra di loginPage
 * 
 * @param finestra Finestra acquisizionePage da chiudere 
 */
public void logOutAction(acquisizionePage finestra){
	new acquisizioneView().dispose(finestra);
	new acquisizioneView().istanziaLoginPage();
}

/**
 * Il metodo ritorna l'opera richiesta
 * 
 * @param titolo_opera Stringa che rappresenta il titolo dell'opera
 * @param utente utente che ha fatto l'accesso al sistema
 * @return opera richiesta
 */
public opera getOpera(String titolo_opera, utente utente){
	ArrayList<Object> args = new ArrayList<Object>(); 
	args.add(titolo_opera); 
	args.add(utente); 
	
	opera opera = (opera)new operaDAO().retrieve(args); 
		
	return opera; 
}

/**
 * Il metodo ha il compito di gestire il Click sul tasto "indietro"
 * 
 * @param finestra Finestra acquisizionePage da chiudere
 * @param user utente che ha fatto l'accesso al sistema
 */
public void indietroAction(acquisizionePage finestra, utente user){
	new acquisizioneView().dispose(finestra);
	new acquisizioneView().istanziaRicercaPage(user);
}


/**
 * Il metodo gestisce il click sul bottone "Conferma"
 * 
 * @param risoluzione Stringa che rappresenta la risoluzione dell'immagine immessa dall'utente
 * @param data_scatto Stringa che rappresenta la data immessa dall'utente
 * @param numero_pagina Intero che rappresenta il numero della pagina
 * @param opera Oggetto che contiene l'opera in considerazione
 * @param immagine Oggetto che contiene l'immagine di una pagina dell'opera
 * @exception Exception eccezione in caso di errore nei campi 
 * @return booleano che in caso di errore restituisce false, altrimenti ritorna true
 */
public boolean confermaAction(String risoluzione, String data_scatto, String numero_pagina, opera opera, immagine immagine){
	
	try {
		int num_pagina = Integer.parseInt(numero_pagina); 
		
		if(num_pagina > opera.getNumero_pagine() || num_pagina < 1){
			new acquisizioneView().errorMessage("pagina non valida");
			return false;
		}
		
		boolean exist = new acquisizioneView().esistePagina(num_pagina, opera.getTitolo()); 
		
		if(exist){
			new acquisizioneView().errorMessage("acquisizione già effettuata per questa pagina");
			return false;
		}	
				
		immagine.setNumero_pagina(num_pagina);
		immagine.setValidata(false);
		immagine.setTitolo_opera(opera.getTitolo());
		
		ArrayList<Object> immagineArgs = new ArrayList<Object>();
		immagineArgs.add(immagine);
		boolean success = new immagineDAO().insert(immagineArgs); 
		
		if(success){
			new acquisizioneView().infoMessage("acquisizione effettuata con successo\nOra è possibile caricare un'altra immagine");
			return true; 
		}
		else {
			new acquisizioneView().errorMessage("acquisizione fallita");
			return false; 
		}
		
	} 
	catch(Exception e){
		new acquisizioneView().errorMessage("Errori nei campi"); 
		return false; 
	}

}

/**
 * Il metodo ritorna true se tutte le immagini di quell'opera sono state acquisite
 * 
 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
 * @param utente utente che ha effettuato l'accesso al sistema 
 * @return booleano
 */
public boolean tutteAcquisiteAction(String titolo_opera, utente utente){
	
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
		
	return pubblicazione; 
}

/**
 * Il metodo ritorna l'intero che rappresenta la pagina da acquisire 
 * 
 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
 * @return intero che rappresenta la pagina da acquisire  
 */
public int paginaDaAcquisireAction(String titolo_opera){
	
	ArrayList<Object> args = new ArrayList<Object>();  
	args.add(titolo_opera); 
	int paginaDaAcquisire = new immagineDAO().paginaDaAcquisire(args); 
	
	return paginaDaAcquisire; 
}

}
