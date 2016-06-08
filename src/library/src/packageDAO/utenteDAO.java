package packageDAO;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import packageBusiness.utente;
import packageGUI.dialog;
/**
 * La classe utenteDAO che implementa l'interfaccia DAO ha il compito
 * di interfacciarsi con il database
 */
public class utenteDAO implements DAO {
	
	public boolean insert(ArrayList<Object> args){
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean p=true;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		preparedStatement = connect.prepareStatement("INSERT INTO library.utenti(nome,cognome,email,password) VALUES (?,?,?,?)");
		preparedStatement.setString(1,(String)args.get(0));
		preparedStatement.setString(2,(String)args.get(1));
		preparedStatement.setString(3,(String)args.get(2));
		preparedStatement.setString(4,(String)args.get(3));
		preparedStatement.executeUpdate();
		}
			catch(SQLException e){
			p=false;
			new dialog().errorDialog("Errore Database: "+e.getMessage());
			}
			catch(ClassNotFoundException e){
			p=false;
			new dialog().errorDialog("Errore Database: "+e.getMessage());
			}
			catch(Exception e){
			p=false;
			new dialog().errorDialog("Errore generico:"+e.getMessage());
			}
				finally{
					try{
						if(connect!=null) connect.close();
						if(preparedStatement!=null) preparedStatement.close();
						return p;
						}
					catch(SQLException e){
						new dialog().errorDialog("Errore Database: "+e.getMessage());
						return false;
						}
					    }
			}
	public Object retrieve(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		utente utente = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			Statement = connect.createStatement();
			resultSet = Statement.executeQuery("SELECT * FROM library.utenti");
			while(resultSet.next()){
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			int permessi = resultSet.getInt("permessi");
			if(email.equals(args.get(0)) && password.equals(args.get(1))){
				utente = new utente(email,permessi);
			}
			else
				return null;	
			}
		}
				catch(SQLException e){
				new dialog().errorDialog("Errore Database: "+e.getMessage());
				}
				catch(ClassNotFoundException e){
				new dialog().errorDialog("Errore Database: "+e.getMessage());
				}
				catch(Exception e){
				new dialog().errorDialog("Errore generico:"+e.getMessage());
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
