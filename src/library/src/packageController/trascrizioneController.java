package packageController;

import java.util.ArrayList;

import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.trascrizione;
import packageBusiness.utente;
import packageDAO.immagineDAO;
import packageDAO.operaDAO;
import packageDAO.trascrizioneDAO;
import packageGUI.operaPage;
import packageGUI.trascrizionePage;
import packageView.acquisizioneView;
import packageView.operaView;
import packageView.trascrizioneView;


/**
 * Classe controller trascrizioneController 
 */
public class trascrizioneController {

	/**
	 * Il metodo richiama la classe trascrizioneView con il compito di chiudere la finestra 
	 * 
	 * @param finestra Finestra trascrizionePage da chiudere 
	 */
	public void exitAction(trascrizionePage finestra){
		new trascrizioneView().dispose(finestra);
	}
	
	/**
	 * Il metodo richiama la classe operaView con il compito di chiudere la finestra e istanziare 
	 * la finestra di loginPage
	 * 
	 * @param finestra Finestra trascrizionePage da chiudere 
	 */
	public void logOutAction(trascrizionePage finestra){
		new trascrizioneView().dispose(finestra);
		new trascrizioneView().istanziaLoginPage();
	}
	
	/**
	 * Il metodo gestisce il click del pulsante "Indietro"
	 * 
	 * @param finestra Finestra trascrizionePage da chiudere 
	 * @param user Utente che ha effettuato l'accesso al sistema 
	 */
	public void indietroAction(trascrizionePage finestra, utente user){
		new trascrizioneView().dispose(finestra);
		new trascrizioneView().istanziaRicercaPage(user);
	}
	
	/**
	 * Il metodo ritorna l'oggetto immagine richiesto 
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return l'oggetto immagine richiesta 
	 */
	public immagine vistaActionimmagine(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		immagine immagine = (immagine)new immagineDAO().retrieve(args);
		return immagine;
	}
	
	/**
	 * Il metodo ritorna l'oggetto trascrizione richiesto 
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return l'oggetto trascrizione richiesta 
	 */
	public trascrizione vistaActiontrascrizione(String titolo_opera, int numero_pagina, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(numero_pagina); 
		args.add(titolo_opera); 
		args.add(utente); 
		trascrizione trascrizione = (trascrizione)new trascrizioneDAO().retrieve(args);
		return trascrizione;
	}
	
	/**
	 * Il metodo ritorna l'oggetto opera richiesto 
	 * 
	 * @param titolo Stringa che rappresenta il titolo dell'opera 
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return l'oggetto opera richiesto 
	 */
	public opera getOpera(String titolo, utente utente){
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
 		
		return opera; 
	}
	
	/**
	 * Il metodo gestisce il click del bottone "conferma" su trascrizionePage 
	 * 
	 * @param trascrizione Oggetto trascrizione da importare sul database 
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return booleano, true se l'operazione va a buon fine, false altrimenti 
	 */
	public boolean confermaAction(trascrizione trascrizione, utente utente){
	
			
			boolean exist = new trascrizioneView().esistePagina(trascrizione.getNumero_pagina(), trascrizione.getTitolo_opera(), utente); 
			
			if(exist){
				new trascrizioneView().errorMessage("trascrizione già effettuata per questa pagina");
				return false;
			}	
					
			
			ArrayList<Object> trascrizioneArgs = new ArrayList<Object>();
			trascrizioneArgs.add(trascrizione);
			boolean success = new trascrizioneDAO().insert(trascrizioneArgs); 
			
			if(success){
				new trascrizioneView().infoMessage("trascrizione effettuata con successo");
				return true; 
			}
			else {
				new trascrizioneView().errorMessage("trascrizione fallita");
				return false; 
			}
			
		} 

	/**
	 * Il metodo ritorna true se tutte le trascrizioni di quell'opera sono state inserite
	 * 
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera 
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return booleano true se tutte le trascrizioni di quell'opera sono state inserite
	 */
	public boolean tutteTrascritteAction(String titolo_opera, utente utente){
		
		ArrayList<Object> args = new ArrayList<Object>();  
		args.add(titolo_opera); 
		args.add(utente); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
		int numero_pagine = opera.getNumero_pagine(); 
		
		args.clear();
		args.add(titolo_opera); 
		args.add(numero_pagine); 
		args.add(utente); 
		
		boolean pubblicazione = new trascrizioneDAO().controllaValidate(args); 
			
		return pubblicazione; 
	}
	
	/**
	 * Il metodo ritorna true se la pagina numero_pagina in titolo_opera esiste 
	 * 
	 * @param numero_pagina Intero che rappresenta il numero della pagina
	 * @param titolo_opera Stringa che rappresenta il titolo dell'opera
	 * @param utente Utente che ha effettuato l'accesso al sistema 
	 * @return booleano true se la pagina numero_pagina in titolo_opera esiste 
	 */
	public boolean esistePaginaAction(int numero_pagina, String titolo_opera, utente utente){
	ArrayList<Object> trascrizioneArgs = new ArrayList<Object>();
	trascrizione trascrizione;
	
	trascrizioneArgs.add(numero_pagina);
	trascrizioneArgs.add(titolo_opera);
	trascrizioneArgs.add(utente);
	trascrizione = (trascrizione)new trascrizioneDAO().retrieve(trascrizioneArgs);
	
	if(!trascrizione.getTesto().equals("<h2>Testo non disponibile</h2>")) 
		return true;
	
	return false;
}
	
}
