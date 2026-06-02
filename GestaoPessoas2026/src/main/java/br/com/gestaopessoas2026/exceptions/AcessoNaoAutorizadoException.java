package br.com.gestaopessoas2026.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AcessoNaoAutorizadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AcessoNaoAutorizadoException(String message) {
        super(message);
    }

    public AcessoNaoAutorizadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
