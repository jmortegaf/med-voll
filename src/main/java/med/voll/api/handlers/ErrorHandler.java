package med.voll.api.handlers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Handler(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity error404Handler(NoResourceFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found.");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity error403Handler(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bad credentials.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity error400Handler(){
        return ResponseEntity.badRequest().body("Bad formated request");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Handler(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getFieldErrors().stream()
                .map(ErrorData::new));
    }

    private record ErrorData(String field,String msg){
        public ErrorData(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }
}
