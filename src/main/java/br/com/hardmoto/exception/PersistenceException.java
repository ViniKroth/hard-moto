package br.com.hardmoto.exception;

public class PersistenceException extends Exception {

    private static final long serialVersionUID = -3517170452748690517L;

    public PersistenceException(String error) {
        super(error);
    }

    public PersistenceException(Exception exception) {
        super(exception);
    }

    public PersistenceException(String error, Exception exception) {
        super(error, exception);

    }
}