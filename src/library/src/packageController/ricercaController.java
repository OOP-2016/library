package packageController;

import java.util.ArrayList;

import packageBusiness.opera;
import packageBusiness.utente;
import packageDAO.operaDAO;
import packageGUI.ricercaPage;
import packageView.operaView;
import packageView.ricercaView;

/**
 * Classe Controller ricercaController 
 */
public class ricercaController {
	
	/**
	 * Il metodo richiama la classe ricercaView con il compito di chiudere la finestra e di 
	 * istanziare operaPage
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 * @param user Utente che ha effettuato l'accesso al sistema 
	 * @param title Stringa che rappresenta il titolo dell'opera scelta
	 */
	public void apriOperaAction(ricercaPage finestra, utente utente, String titolo){
		if(titolo == null){
			new ricercaView().errorMessage("Selezionare un opera");
			return; 
		}
		new ricercaView().dispose(finestra);
		new ricercaView().istanziaOperaPage(utente, titolo);
	}

	/**
	 * Il metodo richiama la classe ricercaView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void exitAction(ricercaPage finestra){
		new ricercaView().dispose(finestra);
	}
	
	/**
	 * Il metodo richiama la classe ricercaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void logOutAction(ricercaPage finestra){
		new ricercaView().dispose(finestra);
		new ricercaView().istanziaLoginPage();
	}
	
	public ArrayList<String> cercaOperaAction(String filtro) {
		
		ArrayList<String> titoli = new operaDAO().retrieveTitoli(filtro);
		return titoli;
		
	}
	
	public boolean aggiungiOperaAction(String titolo, String autore, String annoPubblicazione, String numeroPagine){
		
		if(titolo.length() == 0||autore.length() == 0||annoPubblicazione.length() == 0||numeroPagine.length() == 0){
			new ricercaView().errorMessage("Campi mancanti");
			return false; 
		}
		
		int anno_pubblicazione; 
		int numero_pagine; 
		
		try {
		
		anno_pubblicazione = Integer.parseInt(annoPubblicazione); 
		numero_pagine = Integer.parseInt(numeroPagine); 
		
		} catch(Exception e){
			new ricercaView().errorMessage("Errore scrittura nei campi");
			return false; 
		}
		
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		args.add(autore); 
		args.add(anno_pubblicazione); 
		args.add(numero_pagine); 
		
		boolean success = new operaDAO().insert(args); 
		
		if(success){
			new ricercaView().infoMessage("Opera caricata");
			return true; 
		} else {
			new ricercaView().errorMessage("Opera non caricata");
			return false; 
		}
		
	}
	
	public opera getOpera(String titolo){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
 		
		return opera; 
	}
}
