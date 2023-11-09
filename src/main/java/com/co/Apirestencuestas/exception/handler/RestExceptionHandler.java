package com.co.Apirestencuestas.exception.handler;

import com.co.Apirestencuestas.dto.ErrorDetail;
import com.co.Apirestencuestas.dto.ValidationError;
import com.co.Apirestencuestas.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {                                                 //Este es el controlador de excepciones.

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest httpServletRequest) {
        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Recurso no encontrado");
        errorDetail.setDetail(exception.getClass().getName());
        errorDetail.setDeveloperMessage(exception.getMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest) {
        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

        String requestPath = (String) httpServletRequest.getAttribute("javax.servlet.error.request_uri");                   //Esto es para obtener la ruta.

        if (requestPath == null) {
            requestPath = httpServletRequest.getRequestURI();
        }

        errorDetail.setTitle("Validación Fallida");
        errorDetail.setDetail("La validacion de entrada falló");
        errorDetail.setDeveloperMessage(exception.getMessage());

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();                           //Esto es para obtener todos los errores en cada campo.
        for (FieldError fieldError : fieldErrors) {
            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fieldError.getField());     //Estamos obteniendo los errores de cada campo.

            if (validationErrorList == null){
                validationErrorList = new ArrayList<ValidationError>();
                errorDetail.getErrors().put(fieldError.getField(), validationErrorList);                        //Estamos pasando los errores a errorDetail.
            }

            ValidationError validationError = new ValidationError();

            validationError.setCode(fieldError.getCode());
            validationError.setMessage(fieldError.getDefaultMessage());
            validationErrorList.add(validationError);
        }

        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }


}
