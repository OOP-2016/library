package packageDAO;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import packageBusiness.utente;
import packageBusiness.utenteAvanzato;
import packageGUI.dialog;
/**
 * La classe utenteDAO che implementa l'interfaccia DAO ha il compito
 * di interfacciarsi con il database
 */
public class utenteDAO implements DAO {
	
	@SuppressWarnings("finally")
	/**
	 * Metodo che inserisce parametri nel database 
	 * 
	 * @param args ArrayList contententi i parametri da inserire nel database 
	 * @exception SQLException In caso di fallimento di una delle operazioni di query sul database 
	 * @exception ClassNotFoundException In caso di fallimento del driver SQL
	 * @exception Exception In caso di fallimento generale di una delle operazioni sul database 
	 * @return true se insert termina senza errori, false altrimenti 
	 */
	public boolean insert(ArrayList<Object> args){
		
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		
		try{
			
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		preparedStatement = connect.prepareStatement("INSERT INTO library.utente(nome,cognome,email,password,permessi) VALUES (?,?,?,?,?)");
		preparedStatement.setString(1,(String)args.get(0));
		preparedStatement.setString(2,(String)args.get(1));
		preparedStatement.setString(3,(String)args.get(2));
		preparedStatement.setString(4,(String)args.get(3));
		preparedStatement.setInt(5, 1);
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
	
	@SuppressWarnings("finally")
	
	/**
	 * Metodo che prende dati dal database
	 * 
	 * @param args ArrayList contententi i parametri che servono a costruire la query per il database
	 * @exception SQLException In caso di fallimento di una delle operazioni di query sul database 
	 * @exception ClassNotFoundException In caso di fallimento del driver SQL
	 * @exception Exception In caso di fallimento generale di una delle operazioni sul database 
	 * @return oggetto preso dal database 
	 */
	public Object retrieve(ArrayList<Object> args){
		
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		utente utente = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM library.utente");
				
			while(resultSet.next()){
			
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			int permessi = resultSet.getInt("permessi");
			
			String nomeutente = resultSet.getString("nome");
			String cognomeutente = resultSet.getString("cognome");
			String passwordutente = resultSet.getString("password");
			
			if(email.equals((String)args.get(0)) && password.equals((String)args.get(1))){
				/**
				 * Dynamic binding
				 */
				utente = new utenteAvanzato(email, permessi, nomeutente, cognomeutente, passwordutente);
				
				connect.close();
				Statement.close();
				resultSet.close();
				
				return utente; 
			}
			else{
				utente = null;
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
							return utente;
							
							}
						
						catch(SQLException e){
							
							new dialog().errorDialog("Errore Database: "+e.getMessage());
							return null;
							}
						
				      }
				}
	}
