package packageGUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	
}
