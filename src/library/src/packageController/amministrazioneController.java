package packageController;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import packageBusiness.utente;
import packageDAO.amministrazioneDAO;
import packageGUI.amministrazionePage;
import packageGUI.dialog;
import packageGUI.operaPage;
import packageView.amministrazioneView;
import packageView.operaView;

public class amministrazioneController {
	
	public void exitAction(amministrazionePage finestra){
		new amministrazioneView().dispose(finestra);
	}
	
	public void logOutAction(amministrazionePage finestra){
		new amministrazioneView().dispose(finestra);
		new amministrazioneView().istanziaLoginPage();
	}
	
	public void selezionaAction(tableListener listener, ArrayList<String> columnNames, DefaultTableModel tm, String tableName){
		
		
		boolean success = new amministrazioneDAO().seleziona(listener, columnNames, tm, tableName); 
		
		if(!success){
			new dialog().errorDialog("Errore");
			return; 
		}
		
	}
	
	public void ricaricaAction(tableListener listener, ArrayList<String> columnNames, DefaultTableModel tm, String tableName){
		
		
		boolean success = new amministrazioneDAO().ricarica(listener, columnNames, tm, tableName); 
		
		if(!success){
			new dialog().errorDialog("Errore");
			return; 
		}
		
	}
	
	public void deleteAction(JTable table, String tableName){
		boolean success = new amministrazioneDAO().delete(table, tableName); 
		
		if(!success){
			new dialog().errorDialog("Errore");
			return; 
		}
	}

}
