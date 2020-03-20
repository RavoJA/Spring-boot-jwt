package jean.aime.ravo.springjwtsecurity.users.exceptions.erreur;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class AppHandlingError {
    private HttpStatus status;
    private String message;
    private List<String> listErrors;

    public AppHandlingError(HttpStatus status, String message, List<String> listErrors) {
        this.status = status;
        this.message = message;
        this.listErrors = listErrors;
    }

    public AppHandlingError(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        this.listErrors = Arrays.asList(error);
    }

    public AppHandlingError() {
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getListErrors() {
        return listErrors;
    }

    public void setListErrors(List<String> listErrors) {
        this.listErrors = listErrors;
    }
}
