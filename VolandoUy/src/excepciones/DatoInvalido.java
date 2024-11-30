package excepciones;

@SuppressWarnings("serial")
public class DatoInvalido extends Exception{
	public DatoInvalido(String text) {
		super(text);
	}
}