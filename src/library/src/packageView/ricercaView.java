package packageView;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.WindowConstants;

import packageBusiness.opera;
import packageBusiness.utente;
import packageController.operaController;
import packageController.ricercaController;
import packageGUI.acquisizionePage;
import packageGUI.aggiungiOperaForm;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.revisione_aPage;
import packageGUI.revisione_tPage;
import packageGUI.ricercaPage;
import packageGUI.trascrizionePage;

/**
 * Classe View ricercaView
 */
public class ricercaView {

	/**
	 * Il metodo istanzia il controller ricercaController
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void exit(ricercaPage finestra){
		new ricercaController().exitAction(finestra); 
	}
	
	/**
	 * Il metodo istanzia la classe dialog che si occuperà di chiudere la finestra
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void dispose(ricercaPage finestra){
		new dialog().disposeDialog(finestra);
	}
	
	/**
	 * Il metodo istanzia il controller ricercaController
	 * 
	 * @param finestra Finestra ricercaPage da chiudere 
	 */
	public void logOut(ricercaPage finestra){
		new ricercaController().logOutAction(finestra); 
	}
	
	/**
	 * Il metodo permette di invocare una nuova finestra di loginPage
	 */
	public void istanziaLoginPage(){

		loginPage finestra = new loginPage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
	/**
	 * il metodo permette di invocare una nuova finestra di trascrizione
	 * 
	 * @param finestra finestrra da chiudere 
	 * @param utente utente che ha effettuato l'accesso
	 * @param titolo titolo dell'opera in considerazione
	 */
	public void istanziaTrascrizionePage(ricercaPage finestra, utente utente, String titolo){
		new dialog().disposeDialog(finestra);
		trascrizionePage trascrizionePage = new trascrizionePage(utente, titolo);
		trascrizionePage.setVisible(true);
		trascrizionePage.setResizable(false);
		
	}
	
	/**
	 * Il metodo permette di invocare una nuova finestra di operaPage
	 * 
	 * @param user Utente che ha effettuato l'accesso, il parametro ha il compito di gestire la sessione
	 * @param titolo titolo dell'opera in considerazione
	 */
	public void istanziaOperaPage(utente utente, String titolo){
		operaPage finestra = new operaPage(utente, titolo);
		finestra.setVisible(true);
		finestra.setResizable(false);
	}
	
	/**
	 * Il metodo permette di invocare una nuova finestra di revisione_aPage
	 * 
	 * @param finestra finestra da chiudere
	 * @param utente Utente che ha effettuato l'accesso, il parametro ha il compito di gestire la sessione
	 * @param titolo titolo dell'opera in considerazione
	 */
	public void istanziaRevisione_aPage(ricercaPage finestra, utente utente, String titolo){
		new dialog().disposeDialog(finestra);
		revisione_aPage revisione_aPage = new revisione_aPage(utente, titolo);
		revisione_aPage.setVisible(true);
		revisione_aPage.setResizable(false);
	}
	
	/**
	 * Il metodo permette di invocare una nuova finestra di revisione_tPage
	 * 
	 * @param finestra finestra da chiudere
	 * @param utente Utente che ha effettuato l'accesso, il parametro ha il compito di gestire la sessione
	 * @param titolo titolo dell'opera in considerazione
	 */
	public void istanziaRevisione_tPage(ricercaPage finestra, utente utente, String titolo){
		new dialog().disposeDialog(finestra);
		revisione_tPage revisione_tPage = new revisione_tPage(utente, titolo);
		revisione_tPage.setVisible(true);
		revisione_tPage.setResizable(false);
	}

	/**
	 * Il metodo permette di invocare il controller ricercaController
	 * 
	 * @param finestra Finestra ricercaPage chiamante
	 * @param utente Utente che ha effettuato l'accesso
	 * @param titolo Stringa che rappresenta il titolo del manoscritto 
	 */
	public void apriOpera(ricercaPage finestra, utente utente, String titolo){
		new ricercaController().apriOperaAction(finestra, utente, titolo);
	}
	
	/**
	 * Il metodo richiama la classe AlertDialog che crea un MessageDialog di informazione 
	 * 
	 * @param info Stringa informativa
	 */
	public void infoMessage(String info){
		new dialog().infoDialog(info);
	}
	
	/**
	 * Il metodo richiama la classe AlertDialog che crea un MessageDialog di errore 
	 * 
	 * @param error Stringa di errore 
	 */
	public void errorMessage(String error){
		new dialog().errorDialog(error);
	}
	
	/**
	 * metodo che istanzia ricercaController
	 * 
	 * @param filtro permette di filtrare la ricerca
	 * @param listModel lista delle opera
	 * @param utente utente che ha effettuato l'acesso
	 */
	public void cercaOpera(String filtro, DefaultListModel listModel, utente utente) {
		ArrayList<String> titoli=new ricercaController().cercaOperaAction(filtro, utente);
		for(int i=0;i<titoli.size();i++) listModel.addElement(titoli.get(i));
		}
	
	/**
	 * metodo che istanzia ricercaController
	 * 
	 * @param titolo titolo dell'opera in considerazione
	 * @param autore autore dell'opera
	 * @param anno_pubblicazione anno di pubblicazione dell'opera
	 * @param numero_pagine numero di pagine di un'opera
	 * @param finestra finestra da chiudere
	 */
	public void aggiungiOpera(String titolo, String autore, String anno_pubblicazione, String numero_pagine, aggiungiOperaForm finestra){
		boolean success = new ricercaController().aggiungiOperaAction(titolo, autore, anno_pubblicazione, numero_pagine);
		
		if(success){
			new dialog().disposeDialog(finestra);
		}
		
	}
	
	/**
	 * permette di aprire la form di aggiunta di un'opera
	 * 
	 * @exception Exception messaggio di errore in caso di fallimento nell'apertura della pagina
	 */
	public void apriAggiungiOperaForm(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aggiungiOperaForm frame = new aggiungiOperaForm();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * permette di istanziare una pagina di acquisizionePage
	 * 
	 * @param finestra finestra da chiudere
	 * @param utente utente che ha effettuato l'accesso
	 * @param opera opera presa in considerazione
	 */
	public void istanziaAcquisizionePage(ricercaPage finestra, utente utente, opera opera){
		new dialog().disposeDialog(finestra);
		acquisizionePage frame = new acquisizionePage(utente,opera);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	/**
	 * metodo che istanzia ricercaController
	 * 
	 * @param titolo titolo dell'opera in considerazione
	 * @param utente utente che ha effettuato l'accesso
	 * @return ritorna un oggetto di tipoopera
	 */
	public opera getOpera(String titolo, utente utente){
		return new ricercaController().getOpera(titolo, utente); 
	}
	
}
