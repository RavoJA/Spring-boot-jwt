package jean.aime.ravo.springjwtsecurity.users.exceptions.erreur;

public class UserErreorException extends Exception {
    public UserErreorException() {
    }

    public UserErreorException(String message) {
        super(message);
    }

    public UserErreorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserErreorException(Throwable cause) {
        super(cause);
    }

    public UserErreorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
