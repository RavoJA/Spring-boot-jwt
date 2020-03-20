package jean.aime.ravo.springjwtsecurity.users.exceptions.erreur;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@ControllerAdvice
public class ErrorController {
    private List<String> errors = new ArrayList<>();

    @ExceptionHandler({UserErreorException.class})
    public ResponseEntity<AppHandlingError> handleResponseError(HttpServletRequest req, Exception ex) {
        errors.add(ex.getLocalizedMessage());
        AppHandlingError appHandlingError = new AppHandlingError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(appHandlingError, appHandlingError.getStatus());
    }


}




