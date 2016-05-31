package packageController;
import packageView.registrazioneView;

public class registrazioneController {
	
	/**
	 * Il metodo istanzia la classe registrazioneView e chiama il metodo che permette di istanziare 
	 * la finestra di registrazionePage
	 */
	public void instanziaRegistrazioneAction(){
		
		new registrazioneView().istanziaRegistrazionePage();
		
	}
	
	/**
	 * Il metodo istanzia la classe registrazioneView e chiama il metodo che permette di istanziare 
	 * la finestra di loginPage 
	 */
	public void istanziaLoginAction() {
		
		new registrazioneView().istanziaLoginPage();
		
	}
	
}	