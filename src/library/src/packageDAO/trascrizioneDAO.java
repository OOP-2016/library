package packageDAO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.mysql.jdbc.Blob;
import packageBusiness.immagine;
import packageBusiness.trascrizione;
import packageBusiness.utente;
import packageGUI.dialog;

public class trascrizioneDAO {
	
/**
 * Metodo che inserisce parametri nel database 
 * 
 * @param args ArrayList contententi i parametri da inserire nel database 
 * @return true se insert termina senza errori, false altrimenti 
 */
@SuppressWarnings("finally")
public boolean insert(ArrayList<Object> args){
		
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		
		trascrizione arg = (trascrizione)args.get(0);
		
		int numero_pagina = arg.getNumero_pagina(); 
		String titolo_opera = arg.getTitolo_opera(); 
		String testo = arg.getTesto(); 
        String trascrittore = arg.getTrascrittore();
        String data_scrittura = arg.getData_scrittura();
		
		try{
		
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		
		preparedStatement = connect.prepareStatement("INSERT INTO library.trascrizione(numero_pagina,titolo_opera,testo,validata,data_scrittura,trascrittore) VALUES (?,?,?,?,?,?)");
		preparedStatement.setInt(1, numero_pagina);
		preparedStatement.setString(2,titolo_opera);
		preparedStatement.setString(3,testo);
		preparedStatement.setBoolean(4,false);
		preparedStatement.setString(5,data_scrittura);
		preparedStatement.setString(6,trascrittore);		
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
	public Object retrieve(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		
		int numero_pagina = (int)args.get(0); 
		String titolo_opera = (String)args.get(1); 
		utente utente = (utente)args.get(2); 
		
		trascrizione trascrizione = null; 
		String testo = null;
        String data_scrittura = null;
        String trascrittore = null;
        boolean validata = false;
        
        String query; 
        String sanitizedQuery; 
        
	try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			Statement = connect.createStatement();
			query = "SELECT * FROM library.trascrizione WHERE titolo_opera='" ; 
			sanitizedQuery = String.format("%s", titolo_opera);  
			sanitizedQuery = sanitizedQuery.replaceAll("'", "''"); 
			query+=sanitizedQuery; 
			query+= String.format("' AND numero_pagina=%d", numero_pagina);
					
			resultSet = Statement.executeQuery(query);
			
			while(resultSet.next()){
				 
				validata = resultSet.getBoolean("validata"); 
				
				/**
				 * Se l'utente è un revisore trascrizioni, viene salvato il testo anche se non validato
				 */
				if(utente.getPermessi()==0||utente.getPermessi()==1||utente.getPermessi()==2||utente.getPermessi()==3){
				
					if(validata){
					testo = resultSet.getString("testo"); 
					} else {
						testo = "<h2>Testo non disponibile</h2>"; 
					}  
				
				} else {
					testo = resultSet.getString("testo"); 
				}
				
				trascrittore = resultSet.getString("trascrittore");
				data_scrittura = resultSet.getString("data_scrittura");
				
			}
			
			if(testo == null)
				testo = "<h2>Testo non disponibile</h2>"; 
			
			trascrizione = new trascrizione (testo, titolo_opera, numero_pagina,data_scrittura, trascrittore, validata);
			
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
							return trascrizione;
							
							}
						
						catch(SQLException e){
							
							new dialog().errorDialog("Errore Database: "+e.getMessage());
							return null;
							}
						
				      }
	}
	
	/**
	 * Il metodo modifica una riga nel database
	 * 
	 * @param args args ArrayList contententi i parametri da inserire nel database 
	 * @return true se insert termina senza errori, false altrimenti 
	 */
	@SuppressWarnings("finally")
	public boolean update(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		boolean success=true;
		
		int numero_pagina = (int)args.get(0); 
		String titolo_opera = (String)args.get(1);
		String email_utente = (String)args.get(2); 
		String query; 
		String sanitizedQuery; 
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		
		Statement = connect.createStatement(); 
		query = "UPDATE library.trascrizione SET validata=1, revisore='";  
		query+= String.format("%s' WHERE titolo_opera='", email_utente); 
		sanitizedQuery = String.format("%s", titolo_opera); 
		sanitizedQuery = sanitizedQuery.replaceAll("'", "''"); 
		query+=sanitizedQuery; 
		query+= String.format("' AND numero_pagina=%d", numero_pagina); 
		
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

	/**
	 * Il metodo cancella una riga dal database 
	 * 
	 * @param args args ArrayList contententi i parametri che servono a costruire la query per il database
	 * @return true se insert termina senza errori, false altrimenti 
	 */
	@SuppressWarnings("finally")
	public boolean delete(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		boolean success=true;
		
		int numero_pagina = (int)args.get(0); 
		String titolo_opera = (String)args.get(1);
		String query; 
		String sanitizedQuery; 
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		
		Statement = connect.createStatement(); 
		query = "DELETE FROM library.trascrizione WHERE titolo_opera='";  
		sanitizedQuery = String.format("%s", titolo_opera); 
		sanitizedQuery = sanitizedQuery.replaceAll("'", "''"); 
		query+=sanitizedQuery; 
		query+= String.format("' AND numero_pagina=%d", numero_pagina); 
		
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

	@SuppressWarnings("finally")
	public boolean controllaValidate(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		boolean tutteValidate = false; 
		
		String titolo_opera = (String)args.get(0); 
		int numero_pagine = (int)args.get(1); 
		utente utente = (utente)args.get(2); 
		
		boolean validata = false;
		int paginePresenti = 0; 
		
		String query; 
		String sanitizedQuery; 
		
	try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			Statement = connect.createStatement();
			query = "SELECT * FROM library.trascrizione WHERE titolo_opera='"; 
			sanitizedQuery = String.format("%s", titolo_opera); 
			sanitizedQuery = sanitizedQuery.replaceAll("'", "''"); 
			query+= sanitizedQuery; 
			query+= "'"; 
			resultSet = Statement.executeQuery(query);
			
			while(resultSet.next()){
				
				
				if((!(utente.getPermessi()==2))&&(!(utente.getPermessi()==4))){
				
					paginePresenti++; 
					validata = resultSet.getBoolean("validata"); 
					
					if(!validata){
						tutteValidate = false; 
						break; 
					}
					
					if(paginePresenti == numero_pagine)
						tutteValidate = true; 
					
			} else {
				paginePresenti++; 
				
				if(paginePresenti == numero_pagine)
					tutteValidate = true; 
			}
				
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
							return tutteValidate;
							
							}
						
						catch(SQLException e){
							
							new dialog().errorDialog("Errore Database: "+e.getMessage());
							return false;
							}
						
				      }
	}
	
}
