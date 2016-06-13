package packageController;

import java.util.ArrayList;

import javax.swing.JLabel;

import packageBusiness.opera;
import packageBusiness.pagina;
import packageBusiness.utente;
import packageDAO.operaDAO;
import packageDAO.paginaDAO;
import packageDAO.utenteDAO;
import packageGUI.acquisizionePage;
import packageGUI.dialog;
import packageGUI.operaPage;
import packageView.acquisizioneView;
import packageView.operaView;

public class acquisizioneController {
	
public boolean esistePaginaAction(int numeroPagina, String titolo){
	ArrayList<Object> pagina = new ArrayList<Object>();
	pagina oggettoPagina;
	pagina.add(numeroPagina);
	pagina.add(titolo);
	oggettoPagina = (pagina)new paginaDAO().retrieve(pagina);
	if(oggettoPagina.getImmagine().getImmagine() != null) return true;
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

public opera getOpera(String titolo){
	ArrayList<Object> args = new ArrayList<Object>(); 
	args.add(titolo); 
	
	opera opera = (opera)new operaDAO().retrieve(args); 
		
	return opera; 
}

public void indietroAction(acquisizionePage finestra, utente user){
	new acquisizioneView().dispose(finestra);
	new acquisizioneView().istanziaRicercaPage(user);
}

public void confermaAction(String risoluzione, String datascatto, String npagina, opera opera, pagina pagina){
	
	try {
		int numero_pagina = Integer.parseInt(npagina); 
		
		if(numero_pagina > opera.getNumero_pagine() || numero_pagina < 1){
			new acquisizioneView().errorMessage("pagina non valida");
			return;
		}
		
		boolean exist = new acquisizioneView().esistePagina(numero_pagina, opera.getTitolo()); 
		
		if(exist){
			new acquisizioneView().errorMessage("acquisizione già effettuata per questa pagina");
			return;
		}	
				
		pagina.setNumero(numero_pagina);
		pagina.setimmagine_validata(false);
		pagina.setOpera(opera.getTitolo());
		
		ArrayList<Object> paginaArgs = new ArrayList<Object>();
		paginaArgs.add(pagina);
		boolean success = new paginaDAO().insert(paginaArgs); 
		
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
