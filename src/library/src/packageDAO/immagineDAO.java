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

import packageGUI.dialog;

public class immagineDAO {
public boolean insert(ArrayList<Object> args){
		
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		boolean success=true;
		
		immagine arg = (immagine)args.get(0);
		
		int numero_pagina = arg.getNumero_pagina(); 
		String titolo_opera = arg.getTitolo_opera();  
		BufferedImage img = arg.getImmagine(); 
		String data_scatto = arg.getData_scatto(); 
		String risoluzione = arg.getRisoluzione(); 
		String acquisitore = arg.getAcquisitore();
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(img, "jpg", baos);
	    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		
		preparedStatement = connect.prepareStatement("INSERT INTO library.acquisizione(numero_pagina,titolo_opera,immagine,validata,data_scatto,risoluzione,acquisitore) VALUES (?,?,?,?,?,?,?)");
		preparedStatement.setInt(1, numero_pagina);
		preparedStatement.setString(2,titolo_opera);
		preparedStatement.setBlob(3, bais);
		preparedStatement.setBoolean(4,false);
		preparedStatement.setString(5,data_scatto);
		preparedStatement.setString(6,risoluzione);
		preparedStatement.setString(7, acquisitore);
		
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
		
		int numero_pagina = (int)args.get(0); 
		String titolo_opera = (String)args.get(1); 
		String acquisitore = null;
		String data_scatto = null;
		String risoluzione = null;
		boolean validata = false;
		immagine immagine = null;
		
		Blob binary; 
		InputStream in; 
		BufferedImage img = null; 
		
	try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			Statement = connect.createStatement();
			String query = String.format("SELECT * FROM library.acquisizione WHERE titolo_opera='%s' AND numero_pagina=%d", titolo_opera, numero_pagina); 
			resultSet = Statement.executeQuery(query);
			
			while(resultSet.next()){
				 
				validata = resultSet.getBoolean("validata"); 
				
				binary = (Blob)resultSet.getBlob("immagine"); 
		    	in = binary.getBinaryStream(); 
		    	img = ImageIO.read(in);  
		    	acquisitore = resultSet.getString("acquisitore");
		    	data_scatto = resultSet.getString("data_scatto");
		        risoluzione = resultSet.getString("risoluzione");
		    	
				
			}
			
			immagine = new immagine(img,numero_pagina,titolo_opera, data_scatto, risoluzione,
					                              validata, acquisitore);
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
							return immagine;
							
							}
						
						catch(SQLException e){
							
							new dialog().errorDialog("Errore Database: "+e.getMessage());
							return null;
							}
						
				      }
	}

}
