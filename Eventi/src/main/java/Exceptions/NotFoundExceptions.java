package Exceptions;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(long id) {
        super("Elemento con id " + id + " non trovato!!!");
    }

    public NotFoundExceptions(String message) {
        super(message);
    }
}
