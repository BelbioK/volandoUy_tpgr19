package excepciones;

@SuppressWarnings("serial")
public class InstanciaNoExiste extends Exception{
	public InstanciaNoExiste(String text) {
		super(text);
	}
}