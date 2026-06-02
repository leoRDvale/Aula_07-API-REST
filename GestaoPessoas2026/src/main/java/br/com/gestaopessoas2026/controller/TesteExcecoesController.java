package br.com.gestaopessoas2026.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestaopessoas2026.exceptions.AcessoNaoAutorizadoException;
import br.com.gestaopessoas2026.exceptions.RequisicaoInvalidaException;

@RestController
@RequestMapping("/teste")
public class TesteExcecoesController {

  
    @GetMapping(
            value = "/int/{v1}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String testarErroInterno(@PathVariable String v1) {
        if ("erro".equalsIgnoreCase(v1)) {
            throw new RuntimeException(
                    "Erro simulado. Valor recebido: " + v1);
        }
        return "Requisição processada com sucesso. Valor: " + v1;
    }

   
    @GetMapping(
            value = "/bad/{v1}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String testarRequisicaoInvalida(@PathVariable String v1) {
        if ("invalido".equalsIgnoreCase(v1)) {
            throw new RequisicaoInvalidaException(
                    "Requisição simulada. Valor recebido: " + v1);
        }
        return "Requisição processada com sucesso. Valor: " + v1;
    }

   
    @GetMapping(
            value = "/nao/{v1}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String testarAcessoNaoAutorizado(@PathVariable String v1) {
        if ("bloqueado".equalsIgnoreCase(v1)) {
            throw new AcessoNaoAutorizadoException(
                    "Acesso não autorizado simulado. Valor recebido: " + v1);
        }
        return "Acesso permitido. Valor: " + v1;
    }
}
