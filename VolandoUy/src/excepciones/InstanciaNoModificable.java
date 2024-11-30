package excepciones;

@SuppressWarnings("serial")
public class InstanciaNoModificable extends Exception{
	public InstanciaNoModificable(String text) {
		super(text);
	}
}