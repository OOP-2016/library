package packageDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import packageBusiness.opera;
import packageBusiness.utente;
import packageGUI.dialog;

public class operaDAO implements DAO {
	
	/**
	 * Metodo che inserisce parametri nel database 
	 * 
	 * @param args ArrayList contententi i parametri da inserire nel database 
	 * @return true se insert termina senza errori, false altrimenti 
	 */
	@SuppressWarnings("finally")
	public boolean insert(ArrayList<Object> args) {
		
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		
		try{
			
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		preparedStatement = connect.prepareStatement("INSERT INTO library.opera(titolo,autore,anno_pubblicazione,numero_pagine,pubblicata) VALUES (?,?,?,?,?)");
		preparedStatement.setString(1,(String)args.get(0));
		preparedStatement.setString(2,(String)args.get(1));
		preparedStatement.setInt(3,(int)args.get(2));
		preparedStatement.setInt(4,(int)args.get(3));
		preparedStatement.setBoolean(5, false);
		preparedStatement.executeUpdate();
		}
			catch(SQLException e){
			success=false;
			new dialog().errorDialog("Errore Database: " + e.getMessage());
			}
			catch(ClassNotFoundException e){
			success=false;
			new dialog().errorDialog("Errore Database: " + e.getMessage());
			}
			catch(Exception e){
			success=false;
			new dialog().errorDialog("Errore generico:" + e.getMessage());
			}
				finally{
					try{
						if(connect!=null) connect.close();
						if(preparedStatement!=null) preparedStatement.close();
						return success;
						}
					catch(SQLException e){
						new dialog().errorDialog("Errore Database: "+ e.getMessage());
						return false;
						}
					    }
	}
	
	/**
	 * Metodo che prende dati dal database
	 * 
	 * @param args ArrayList contententi i parametri che servono a costruire la query per il database
	 * @return oggetto preso dal database 
	 */
	@SuppressWarnings("finally")
	public Object retrieve(ArrayList<Object> args) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		opera opera = null;
		String titolo = (String)args.get(0); 
		utente utente = (utente)args.get(1); 
		String autore = null; 
		int anno_pubblicazione = 0; 
		int numero_pagine = 0; 
		boolean pubblicata = false; 
		
	try{
			String query; 
			String sanitizedQuery; 
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			Statement = connect.createStatement();
			
			if(utente.getPermessi() == 1||utente.getPermessi()==0){
				query = "SELECT * FROM library.opera WHERE titolo='"; 
				sanitizedQuery = String.format("%s", titolo); 
				sanitizedQuery = sanitizedQuery.replaceAll("'", "''"); 
				query+= sanitizedQuery; 
				query+= "' AND pubblicata=1"; 
			}
			else if(utente.getPermessi() == 2||utente.getPermessi()==3) {
				query = "SELECT * FROM library.opera WHERE titolo='"; 
				sanitizedQuery = String.format("%s", titolo); 
				sanitizedQuery = sanitizedQuery.replaceAll("'", "''"); 
				query+= sanitizedQuery; 
				query+= "' AND pubblicata=0"; 			
			}
			else {
				query = "SELECT * FROM library.opera WHERE titolo='"; 
				sanitizedQuery = String.format("%s", titolo); 
				sanitizedQuery = sanitizedQuery.replaceAll("'", "''"); 
				query+= sanitizedQuery; 
				query+= "'"; 	
			}
			
			resultSet = Statement.executeQuery(query);
			
			while(resultSet.next()){
				
			autore = resultSet.getString("autore");
			anno_pubblicazione = resultSet.getInt("anno_pubblicazione");
			numero_pagine = resultSet.getInt("numero_pagine");
			pubblicata = resultSet.getBoolean("pubblicata");
			}
			
			opera = new opera(titolo, autore, anno_pubblicazione, numero_pagine, pubblicata); 
			
		}
				catch(SQLException e){
				new dialog().errorDialog("Errore Database: " + e.getMessage());
				}
				catch(ClassNotFoundException e){
				new dialog().errorDialog("Errore Database: " + e.getMessage());
				}
				catch(Exception e){
				new dialog().errorDialog("Errore generico:" + e.getMessage());
				}
					finally{
						
						try{
							
							if(connect!=null) connect.close();
							if(Statement!=null) Statement.close();
							if(resultSet!=null) resultSet.close();
							return opera;
							
							}
						
						catch(SQLException e){
							
							new dialog().errorDialog("Errore Database: "+e.getMessage());
							return null;
							}
						
				      }
	}
	
	@SuppressWarnings("finally")
	public ArrayList<String> retrieveTitoli(String filtro, utente utente) {
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		ArrayList<String> titoli = new ArrayList<String>();
		String query;
		String sanitizeQuery; 
		
	try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			
			if(utente.getPermessi() == 1||utente.getPermessi() == 0){
				
				if(filtro.length()==1) {
					if(filtro.equals("-")){
						query = "SELECT titolo, autore FROM library.opera WHERE pubblicata=1";
					}
					else {
					query = "SELECT titolo, autore FROM library.opera WHERE titolo LIKE '";
					sanitizeQuery = String.format("%s", filtro);
					sanitizeQuery = sanitizeQuery.replace("'", "''"); 
					query+=sanitizeQuery; 
					query += "%' AND pubblicata=1";
					}
				}
				else {
				query = "SELECT titolo, autore FROM library.opera WHERE titolo LIKE '%";
				sanitizeQuery = String.format("%s", filtro);
				sanitizeQuery = sanitizeQuery.replace("'", "''"); 
				query+=sanitizeQuery;
				query += "%' AND pubblicata=1"; 
				}
				
			} else if(utente.getPermessi() == 2||utente.getPermessi()==3){
			
			if(filtro.length()==1) {
				if(filtro.equals("-")){
					query = "SELECT titolo, autore FROM library.opera WHERE pubblicata=0";
				}
				else {
				query = "SELECT titolo, autore FROM library.opera WHERE titolo LIKE '";
				sanitizeQuery = String.format("%s", filtro);
				sanitizeQuery = sanitizeQuery.replace("'", "''"); 
				query+=sanitizeQuery;
				query += "%' AND pubblicata=0";
				}
			}
			else {
			query = "SELECT titolo, autore FROM library.opera WHERE titolo LIKE '%";
			sanitizeQuery = String.format("%s", filtro);
			sanitizeQuery = sanitizeQuery.replace("'", "''"); 
			query+=sanitizeQuery;
			query += "%' AND pubblicata=0"; 
			}
			} else {
				if(filtro.length()==1) {
					if(filtro.equals("-")){
						query = "SELECT titolo, autore FROM library.opera";
					}
					else {
					query = "SELECT titolo, autore FROM library.opera WHERE titolo LIKE '";
					sanitizeQuery = String.format("%s", filtro);
					sanitizeQuery = sanitizeQuery.replace("'", "''"); 
					query+=sanitizeQuery;
					query += "%'";
					}
				}
				else {
				query = "SELECT titolo, autore FROM library.opera WHERE titolo LIKE '%";
				sanitizeQuery = String.format("%s", filtro);
				sanitizeQuery = sanitizeQuery.replace("'", "''"); 
				query+=sanitizeQuery;
				query += "%'"; 
				}
			}
			
			//new dialog().infoDialog(query);
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery(query);
			
			while(resultSet.next()){
				
				String title = resultSet.getString("titolo"); 
				String autore = resultSet.getString("autore");
				
				titoli.add(title + " - " + autore);
			}
		}
				catch(SQLException e){
				new dialog().errorDialog("Errore Database: " + e.getMessage());
				}
				catch(ClassNotFoundException e){
				new dialog().errorDialog("Errore Database: " + e.getMessage());
				}
				catch(Exception e){
				new dialog().errorDialog("Errore generico:" + e.getMessage());
				}
					finally{
						
						try{
							
							if(connect!=null) connect.close();
							if(Statement!=null) Statement.close();
							if(resultSet!=null) resultSet.close();
							return titoli;
							
							}
						
						catch(SQLException e){
							
							new dialog().errorDialog("Errore Database: "+e.getMessage());
							return null;
							}
		
	}
}
	
	@SuppressWarnings("finally")
	public boolean pubblica(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		boolean success=true;
		
		String titolo_opera = (String)args.get(0); 
		String query; 
		String sanitizedQuery; 
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		
		Statement = connect.createStatement(); 
		query = "UPDATE library.opera SET pubblicata=1 WHERE titolo='";   
		sanitizedQuery = String.format("%s", titolo_opera); 
		sanitizedQuery = sanitizedQuery.replaceAll("'", "''"); 
		query+=sanitizedQuery; 
		query+= "'" ; 
		
		Statement.executeUpdate(query);
		
		}
			catch(SQLException e){
			success=false;
			new dialog().errorDialog("Errore Database: " + e.getMessage());
			}
			catch(ClassNotFoundException e){
			success=false;
			new dialog().errorDialog("Errore Database: " + e.getMessage());
			}
			catch(Exception e){
			success=false;
			new dialog().errorDialog("Errore generico:" + e.getMessage());
			}
				finally{
					try{
						if(connect!=null) connect.close();
						if(Statement!=null) Statement.close();
						return success;
						}
					catch(SQLException e){
						new dialog().errorDialog("Errore Database: "+ e.getMessage());
						return false;
						}
					    }
	}
	
	
}
