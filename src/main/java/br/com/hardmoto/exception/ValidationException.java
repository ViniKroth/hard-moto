package br.com.hardmoto.exception;

public class ValidationException extends Exception {

    private static final long serialVersionUID = -3517170452748690517L;

    public ValidationException(String error) {
        super(error);
    }

    public ValidationException(Exception exception) {
        super(exception);
    }

    public ValidationException(String error, Exception exception) {
        super(error, exception);

    }
}
