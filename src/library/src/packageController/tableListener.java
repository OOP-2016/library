package packageController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import packageGUI.dialog;

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
	        
	        
	        Connection connect = null;
			Statement Statement = null;
			
			 
			String query; 
			String sanitizedQuery; 
			String sanitizedColumn; 
			
			try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			
			Statement = connect.createStatement(); 
			
			query = "UPDATE library." + tableName + " SET " + columnName;  
			if(columnName.equals("id")||columnName.equals("numero_pagina")||columnName.equals("validata")||columnName.equals("anno_pubblicazione")||columnName.equals("numero_pagine")||columnName.equals("pubblicata")||columnName.equals("permessi")){
				
				query+="="; 
				sanitizedColumn = String.format("%s", data);
				query+=sanitizedColumn; 
				
			} else {
				query+="='"; 
				sanitizedColumn = String.format("%s", data);
				query+=sanitizedColumn;
				query+="'"; 
			}
			
			
			
			query+=" WHERE id="; 
			query+=String.format("%d", key); 
			
			Statement.executeUpdate(query);
			
			}
				catch(SQLException j){

				new dialog().errorDialog("Errore Database: " + j.getMessage());
				}
				catch(ClassNotFoundException h){
				new dialog().errorDialog("Errore Database: " + h.getMessage());
				}
				catch(Exception l){
				new dialog().errorDialog("Errore generico:" + l.getMessage());
				}
	        
	
	    }
}
