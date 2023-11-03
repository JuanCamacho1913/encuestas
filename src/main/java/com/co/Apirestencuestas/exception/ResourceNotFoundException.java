package com.co.Apirestencuestas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)                                                    //Indicar el codigo de estado que va a devolver (NOT_FOUND).
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;                                    //Todas la clases que se devuelvan asi (NOT_FOUND) Tienen que tener una serialVersionUID.

    public ResourceNotFoundException() {                                                //Constructor normal.
    }

    public ResourceNotFoundException(String message) {                                  //Constructor con un mensaje.
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {                 //Constructor con un mensaje y el error que esta devolviendo.
        super(message, cause);
    }
}
