package packageGUI;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import packageBusiness.utente;

/**
 * Classe alertDialog che permette di chiamare MessageDialogs e metodi JFrame
 */

public class dialog {
	
	/**
	 * Il metodo crea un MessageDialog di informazione
	 * @param info Stringa che descrive l'informazione
	 */
	public void infoDialog(String info){
		JOptionPane.showMessageDialog(null, info , "Messaggio", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Il metodo crea un MessageDialog di errore
	 * @param error Stringa che descrive l'errore
	 */
	public void errorDialog(String error){
		JOptionPane.showMessageDialog(null, error , "ERRORE", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Il metodo ha il compito di chiamare il metodo dispose() su un oggetto di tipo JFrame
	 * 
	 * @param window La finestra sulla quale chiamare il metodo dispose()
	 */
	public void disposeDialog(Object window){
		
		if(window instanceof JFrame){
			JFrame finestra = (JFrame)window;
			finestra.dispose();
		} else 
			throw new RuntimeException(); 
		
	}
	
	public int modalit‡AccessoForm(utente utente){
		
		int permessi = utente.getPermessi(); 
		Object[] choices = new Object[2]; 
		choices[0] = "Utente Avanzato"; 
		
		switch(permessi){
		
		case 2: 
			choices[1] = "Acquisitore"; 
			break; 
		
		case 3: 
			choices[1] = "Revisore Acquisizioni"; 
			break;
			
		case 4: 
			choices[1] = "Trascrizione"; 
			break;
			
		case 5: 
			choices[1] = "Revisore Trascrizioni"; 
			break;
			
		case 6: 
			choices[1] = "Amministratore"; 
			break;
			
		}
		
		int choice = JOptionPane.showOptionDialog(null, "Scegliere modalit‡ accesso", "Messaggio", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices , "Acquisitore");
		
		if(choice == 0){
			return 1; //utente avanzato
		}
		else if(choice == 1){
			return 2; //acquisitore
		} else 
			return -1; 
		
	}
	
	public BufferedImage fileChooser(JLabel labelImmagine, Component acquisizionePage){
		Image img = null;
		BufferedImage immagine = null;
		
		JFileChooser fileChooser = new JFileChooser();
		
		int returnVal = fileChooser.showOpenDialog(acquisizionePage);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File imported = fileChooser.getSelectedFile();
			try{
				
			immagine = ImageIO.read(imported);
			img = immagine.getScaledInstance(labelImmagine.getWidth(),labelImmagine.getHeight(),java.awt.Image.SCALE_SMOOTH);
			labelImmagine.setIcon(new ImageIcon(img));
			
			return immagine; 
			
			}
			catch(Exception e){
				this.errorDialog("Il file non Ë un immagine");
				return null; 
			}
		
		} else {
			return null; 
		}
	}
	
}
