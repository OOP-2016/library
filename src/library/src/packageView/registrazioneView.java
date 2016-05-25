package packageView;

import packageGUI.registrazionePage;
import packageController.registrazione;

public class registrazioneView {
	public void istanziaController(){
		new registrazione();
	}
	public void istanziaGUI(){
		registrazionePage a = new registrazionePage();
		a.setVisible(true);
	}
}
