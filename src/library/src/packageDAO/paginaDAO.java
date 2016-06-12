package packageDAO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.mysql.jdbc.Blob;

import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.pagina;
import packageGUI.dialog;

public class paginaDAO implements DAO {
	
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
		
		pagina arg = (pagina)args.get(0);
		
		int numero = arg.getNumero(); 
		String opera = arg.getOpera(); 
		String trascrizione = arg.getTrascrizione(); 
		boolean trascrizione_validata = arg.gettrascrizione_validata(); 
		boolean immagine_validata = arg.getimmagine_validata(); 
		
		immagine immagine = arg.getImmagine(); 
		BufferedImage img = immagine.getImmagine(); 
		Date dataScatto = immagine.getDataScatto(); 
		String risoluzione = immagine.getRisoluzione(); 
		
		try{
		
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(img, "jpg", baos);
	    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		
		preparedStatement = connect.prepareStatement("INSERT INTO library.utenti(numero,opera,immagine,immagine_validata,trascrizione,trascrizione_validata,datascatto,risoluzione) VALUES (?,?,?,?,?,?,?,?)");
		preparedStatement.setInt(1, numero);
		preparedStatement.setString(2,opera);
		preparedStatement.setBlob(3, bais);
		preparedStatement.setBoolean(4,immagine_validata);
		preparedStatement.setString(5,trascrizione);
		preparedStatement.setBoolean(6,trascrizione_validata);
		preparedStatement.setDate(7, dataScatto);
		preparedStatement.setString(8,risoluzione);
		
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
	 * @param args ArrayList contententi i parametri da inserire nel database 
	 * @return oggetto preso dal database 
	 */
	@SuppressWarnings("finally")
	public Object retrieve(ArrayList<Object> args){
		Connection connect = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		
		int Npagina = (int)args.get(0); 
		String titolo = (String)args.get(1); 
		immagine immagine = null; 
		pagina pagina = null;
		String trascrizione = null; 
		
		Blob binary; 
		InputStream in; 
		BufferedImage img = null; 
		
	try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			Statement = connect.createStatement();
			String query = String.format("SELECT * FROM library.pagina WHERE opera='%s' AND numero=%d", titolo, Npagina); 
			resultSet = Statement.executeQuery(query);
			
			while(resultSet.next()){
				 
				boolean trascrizione_validata = resultSet.getBoolean("trascrizione_validata"); 
				
				if(trascrizione_validata){
				trascrizione = resultSet.getString("trascrizione"); 
				} else {
					trascrizione = "<h2>Trascrizione non disponibile</h2>"; 
				}
				
				binary = (Blob)resultSet.getBlob("immagine"); 
		    	in = binary.getBinaryStream(); 
		    	img = ImageIO.read(in);  
				
			}
			
			immagine = new immagine(img, null, null); 
			pagina = new pagina(Npagina, titolo, immagine, false, trascrizione, false); 
			
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
							return pagina;
							
							}
						
						catch(SQLException e){
							
							new dialog().errorDialog("Errore Database: "+e.getMessage());
							return null;
							}
						
				      }
	}

}
