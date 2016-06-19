package packageDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import packageController.tableListener;
import packageGUI.dialog;

public class amministrazioneDAO {

	public boolean seleziona(tableListener listener, ArrayList<String> columnNames, DefaultTableModel tm, String tableName){
		
		Connection connect = null;
		Statement statement = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			  
			columnNames.clear();
		    statement = connect.createStatement();
		    ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
		    
		    tm.removeTableModelListener(listener);
		    // get columns info
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int columnCount = rsmd.getColumnCount();
		    
		    // for changing column and row model
		   
		    // clear existing columns 
		    tm.setColumnCount(0);

		    // add specified columns to table
		    for (int i = 1; i <= columnCount; i++ ) {
		        tm.addColumn(rsmd.getColumnName(i));
		        columnNames.add(rsmd.getColumnName(i)); 
		    }   

		    // clear existing rows
		    tm.setRowCount(0);

		    // add rows to table
		    while (rs.next()) {
		        String[] a = new String[columnCount];
		        for(int i = 0; i < columnCount; i++) {
		            a[i] = rs.getString(i+1);
		        }
		    tm.addRow(a);
		    }
		    tm.fireTableDataChanged();
			tm.fireTableStructureChanged();
			tm.addTableModelListener(listener);
			
			listener.array = columnNames; 
			listener.tableName = tableName; 
		    // Close ResultSet and Statement
			
		    rs.close();
		    statement.close();
		    connect.close();
		    
		    return true; 
		    
		} catch (Exception ex) { 
		    new dialog().errorDialog("Error" + ex.getMessage());
		    ex.printStackTrace();
		    return false;
		}
		
		
		
	}
	
public boolean ricarica(tableListener listener, ArrayList<String> columnNames, DefaultTableModel tm, String tableName){
		
		Connection connect = null;
		Statement statement = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			  
			columnNames.clear();
		    statement = connect.createStatement();
		    ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
		    
		    tm.removeTableModelListener(listener);
		    // get columns info
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int columnCount = rsmd.getColumnCount();
		    
		    // for changing column and row model
		   
		    // clear existing columns 
		    tm.setColumnCount(0);

		    // add specified columns to table
		    for (int i = 1; i <= columnCount; i++ ) {
		        tm.addColumn(rsmd.getColumnName(i));
		        columnNames.add(rsmd.getColumnName(i)); 
		    }   

		    // clear existing rows
		    tm.setRowCount(0);

		    // add rows to table
		    while (rs.next()) {
		        String[] a = new String[columnCount];
		        for(int i = 0; i < columnCount; i++) {
		            a[i] = rs.getString(i+1);
		        }
		    tm.addRow(a);
		    }
		    tm.fireTableDataChanged();
			tm.fireTableStructureChanged();
			tm.addTableModelListener(listener);
			
			listener.array = columnNames; 
			listener.tableName = tableName; 
		    // Close ResultSet and Statement
			
		    rs.close();
		    statement.close();
		    connect.close();
		    
		    return true; 
		    
		} catch (Exception ex) { 
		    new dialog().errorDialog("Error" + ex.getMessage());
		    ex.printStackTrace();
		    return false;
		}
		
		
		
	}

	@SuppressWarnings("finally")
	public boolean delete(JTable table, String tableName){
		int row = table.getSelectedRow(); 
        String id = (String)table.getValueAt(row, 0);
        int key = Integer.parseInt(id);
        Connection connect = null;
		  Statement Statement = null;
		  boolean success=true;
		  
		  String query; 
		  
		  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		  
		  Statement = connect.createStatement(); 
		 
		  
		  query = "DELETE FROM library." + tableName + " WHERE id=" + key;  
		  
		  
		  Statement.executeUpdate(query);
		  
		  return true; 
		  }
		   catch(SQLException g){
		   success=false;
		   new dialog().errorDialog("Errore Database: " + g.getMessage());
		   }
		   catch(ClassNotFoundException d){
		   success=false;
		   new dialog().errorDialog("Errore Database: " + d.getMessage());
		   }
		   catch(Exception f){
		   success=false;
		   new dialog().errorDialog("Errore generico:" + f.getMessage());
		   } finally{
				
				try{
					
					if(connect!=null) connect.close();
					if(Statement!=null) Statement.close();
					return success;
					
					}
				
				catch(SQLException e){
					
					new dialog().errorDialog("Errore Database: "+e.getMessage());
					return false;
					}
				
		      }
		  
	}
	
}
