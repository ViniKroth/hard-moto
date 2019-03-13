package br.com.hardmoto.exception;

public class UnexpectedDatabaseException extends Exception {

    private static final long serialVersionUID = -3517170452748690517L;

    public UnexpectedDatabaseException(String error) {
        super(error);
    }

    public UnexpectedDatabaseException(Exception exception) {
        super(exception);
    }

    public UnexpectedDatabaseException(String error, Exception exception) {
        super(error, exception);

    }
}
