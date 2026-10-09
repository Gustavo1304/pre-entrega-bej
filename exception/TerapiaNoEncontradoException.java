package exception;

public class TerapiaNoEncontradoException extends RuntimeException {
    public TerapiaNoEncontradoException(String mensaje){
        super(mensaje);
    }
}