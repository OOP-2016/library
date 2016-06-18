package packageView;

import java.awt.EventQueue;

import javax.swing.WindowConstants;

import packageBusiness.utente;
import packageController.amministrazioneController;
import packageController.operaController;
import packageGUI.aggiungiOperaForm;
import packageGUI.amministrazionePage;
import packageGUI.dialog;
import packageGUI.loginPage;
import packageGUI.operaPage;
import packageGUI.registrazionePage;
import packageGUI.ricercaPage;

public class amministrazioneView {
	
	public void exit(amministrazionePage finestra){
		new amministrazioneController().exitAction(finestra); 
	}
	
	public void logOut(amministrazionePage finestra){
		new amministrazioneController().logOutAction(finestra); 
	}
	
	public void indietro(amministrazionePage finestra, utente user){
		new amministrazioneController().indietroAction(finestra, user);
	}
	
	public void dispose(amministrazionePage finestra){
		new dialog().disposeDialog(finestra);
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
	
	public void istanziaLoginPage(){

		loginPage finestra = new loginPage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		
	}
	
	public void istanziaRegistrazionePage(){

		registrazionePage finestra = new registrazionePage();
		finestra.setVisible(true);
		finestra.setResizable(false);
		finestra.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}
	
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
	public void istanziaRicercaPage(utente user){
		ricercaPage finestra = new ricercaPage(user);
		finestra.setVisible(true);
		finestra.setResizable(false);
		finestra.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}
}
