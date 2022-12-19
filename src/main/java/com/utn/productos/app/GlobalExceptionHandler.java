package com.utn.productos.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice //maneja los errores en general
public class GlobalExceptionHandler {
    @ExceptionHandler( IllegalStateException.class )
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String objetoMalArmado( IllegalStateException ex ){
        return ex.getLocalizedMessage();
    }
}
