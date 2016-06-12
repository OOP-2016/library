package packageView;

import packageController.acquisizioneController;

public class acquisizioneView {
public boolean esistePagina(int numeroPagina, String titolo){
	boolean p = new acquisizioneController().esistePaginaAction(numeroPagina, titolo);
	return p;
}
}
