package ca.com.elastic.greetings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GreeetingExistsException extends Exception {

    public GreeetingExistsException(String message){
        super(message);
    }
}
