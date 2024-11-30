package excepciones;

@SuppressWarnings("serial")
public class InstanciaRepetida extends Exception {

    public InstanciaRepetida(String string) {
        super(string);
    }
}