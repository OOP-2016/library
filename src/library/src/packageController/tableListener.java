package packageController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import packageDAO.amministrazioneDAO;
import packageGUI.dialog;
import packageView.amministrazioneView;

public class tableListener implements TableModelListener{

	public ArrayList<String> array; 
	public String tableName; 
	
	 public void tableChanged(TableModelEvent e) {  
	        int row = e.getFirstRow(); 
	        int column = e.getColumn();  
	        String columnName = array.get(column); 
	        
	        DefaultTableModel model = (DefaultTableModel)e.getSource();  
	        String data = (String)model.getValueAt(row, column);  
	        String id = (String)model.getValueAt(row, 0);
	        int key = Integer.parseInt(id);
	        
	        boolean success =new amministrazioneDAO().update(tableName, columnName, data, key); 
	        
	        if(!success) new amministrazioneView().errorMessage("Errore");
	
	    }
}
