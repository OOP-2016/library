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

public opera getOpera(String titolo_opera){
	ArrayList<Object> args = new ArrayList<Object>(); 
	args.add(titolo_opera); 
	
	opera opera = (opera)new operaDAO().retrieve(args); 
		
	return opera; 
}

public void indietroAction(acquisizionePage finestra, utente user){
	new acquisizioneView().dispose(finestra);
	new acquisizioneView().istanziaRicercaPage(user);
}

public void confermaAction(String risoluzione, String data_scatto, String numero_pagina, opera opera, immagine immagine){
	
	try {
		int num_pagina = Integer.parseInt(numero_pagina); 
		
		if(num_pagina > opera.getNumero_pagine() || num_pagina < 1){
			new acquisizioneView().errorMessage("pagina non valida");
			return;
		}
		
		boolean exist = new acquisizioneView().esistePagina(num_pagina, opera.getTitolo()); 
		
		if(exist){
			new acquisizioneView().errorMessage("acquisizione già effettuata per questa pagina");
			return;
		}	
				
		immagine.setNumero_pagina(num_pagina);
		immagine.setValidata(false);
		immagine.setTitolo_opera(opera.getTitolo());
		
		ArrayList<Object> immagineArgs = new ArrayList<Object>();
		immagineArgs.add(immagine);
		boolean success = new immagineDAO().insert(immagineArgs); 
		
		if(success){
			new acquisizioneView().infoMessage("acquisizione effettuata con successo\nOra è possibile caricare un'altra immagine");
			return; 
		}
		else {
			new acquisizioneView().infoMessage("acquisizione fallita");
			return; 
		}
		
	} 
	catch(Exception e){
		new acquisizioneView().errorMessage("Errori nei campi"); 
		return; 
	}

}

}
