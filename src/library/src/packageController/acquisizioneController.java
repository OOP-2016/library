package packageController;

import java.util.ArrayList;

import packageBusiness.pagina;
import packageBusiness.utente;
import packageDAO.paginaDAO;
import packageDAO.utenteDAO;

public class acquisizioneController {
public boolean esistePaginaAction(int numeroPagina, String titolo){
	ArrayList<Object> pagina = new ArrayList<Object>();
	pagina oggettoPagina;
	pagina.add(numeroPagina);
	pagina.add(titolo);
	oggettoPagina = (pagina)new paginaDAO().retrieve(pagina);
	if(oggettoPagina.getImmagine().getImmagine() != null) return true;
	return false;
}
}
