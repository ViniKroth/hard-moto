package br.com.hardmoto.exception;

public class BusinessRuleException extends Exception {

    private static final long serialVersionUID = -3517170452748690517L;

    public BusinessRuleException(String error) {
        super(error);
    }

    public BusinessRuleException(Exception exception) {
        super(exception);
    }

    public BusinessRuleException(String error, Exception exception) {
        super(error, exception);

    }
}
