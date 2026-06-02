package br.com.gestaopessoas2026.handle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.gestaopessoas2026.exceptions.AcessoNaoAutorizadoException;
import br.com.gestaopessoas2026.exceptions.ExceptionResponse;
import br.com.gestaopessoas2026.exceptions.RequisicaoInvalidaException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

   
    @ExceptionHandler(RequisicaoInvalidaException.class)
    public final ResponseEntity<ExceptionResponse> handleRequisicaoInvalidaException(
            RequisicaoInvalidaException ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

   
    @ExceptionHandler(AcessoNaoAutorizadoException.class)
    public final ResponseEntity<ExceptionResponse> handleAcessoNaoAutorizadoException(
            AcessoNaoAutorizadoException ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
