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

public class acquisizioneController {
	
	/**
	 * 
	 * @param numero_pagina
	 * @param titolo_opera
	 * @return
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
 * Il metodo richiama la classe operaView con il compito di chiudere la finestra 
 * 
 * @param finestra Finestra operaPage da chiudere 
 */
public void exitAction(acquisizionePage finestra){
	new acquisizioneView().dispose(finestra);
}

/**
 * Il metodo richiama la classe operaView con il compito di chiudere la finestra e istanziare 
 * la finestra di loginPage
 * 
 * @param finestra Finestra operaPage da chiudere 
 */
public void logOutAction(acquisizionePage finestra){
	new acquisizioneView().dispose(finestra);
	new acquisizioneView().istanziaLoginPage();
}

/**
 * 
 * @param titolo_opera
 * @param utente
 * @return
 */
public opera getOpera(String titolo_opera, utente utente){
	ArrayList<Object> args = new ArrayList<Object>(); 
	args.add(titolo_opera); 
	args.add(utente); 
	
	opera opera = (opera)new operaDAO().retrieve(args); 
		
	return opera; 
}

/**
 * 
 * @param finestra
 * @param user
 */
public void indietroAction(acquisizionePage finestra, utente user){
	new acquisizioneView().dispose(finestra);
	new acquisizioneView().istanziaRicercaPage(user);
}

/**
 * 
 * @param risoluzione
 * @param data_scatto
 * @param numero_pagina
 * @param opera
 * @param immagine
 * @return
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
			new acquisizioneView().infoMessage("acquisizione fallita");
			return false; 
		}
		
	} 
	catch(Exception e){
		new acquisizioneView().errorMessage("Errori nei campi"); 
		return false; 
	}

}

/**
 * 
 * @param titolo_opera
 * @param utente
 * @return
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
 * 
 * @param titolo_opera
 * @return
 */
public int paginaDaAcquisireAction(String titolo_opera){
	
	ArrayList<Object> args = new ArrayList<Object>();  
	args.add(titolo_opera); 
	int paginaDaAcquisire = new immagineDAO().paginaDaAcquisire(args); 
	
	return paginaDaAcquisire; 
}

}
