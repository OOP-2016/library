package packageGUI;

import javax.swing.JOptionPane;

/**
 * Classe alertDialog che permette di creare MessageDialog 
 */

public class alertDialog {
	
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
		JOptionPane.showMessageDialog(null, error , "Messaggio", JOptionPane.ERROR_MESSAGE);
	}
	
}
