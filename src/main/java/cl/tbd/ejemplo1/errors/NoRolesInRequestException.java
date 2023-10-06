package cl.tbd.ejemplo1.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoRolesInRequestException extends RuntimeException {
    public NoRolesInRequestException(String message) {
        super(message);
    }
}