package br.com.gestaopessoas2026.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.gestaopessoas2026.exceptions.RequisicaoInvalidaException;
import br.com.gestaopessoas2026.model.Pessoa;

@Service
public class PessoaService {

    private static final List<Pessoa> pessoas = new ArrayList<>();
    private static final AtomicLong contadorId = new AtomicLong(1);

    static {
        pessoas.add(new Pessoa(contadorId.getAndIncrement(), "Ana", "Beatriz",
                "Rua das Alveneiros, 1 - Morrinhos/GO", "Feminino"));
        pessoas.add(new Pessoa(contadorId.getAndIncrement(), "Felipe", "Pereira",
                "Rua das Alveneiros, 2 - Morrinhos/GO", "Masculino"));
        pessoas.add(new Pessoa(contadorId.getAndIncrement(), "Heloisa", "Gomes",
                "Rua das Alveneiros, 3 - Morrinhos/GO", "Feminino"));
    }

  
    public Pessoa buscarPorId(String id) {
        Long idLong = parseId(id);
        return pessoas.stream()
                .filter(p -> p.getId().equals(idLong))
                .findFirst()
                .orElseThrow(() -> new RequisicaoInvalidaException(
                        "Nenhuma pessoa com o ID: " + id));
    }

   
    public List<Pessoa> buscarTodas() {
        return new ArrayList<>(pessoas);
    }

 
    public Pessoa criar(Pessoa pessoa) {
        validarPessoa(pessoa);
        pessoa.setId(contadorId.getAndIncrement());
        pessoas.add(pessoa);
        return pessoa;
    }

   
    public Pessoa atualizar(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            throw new RequisicaoInvalidaException(
                    "O ID deve ser informado para atualização.");
        }
        validarPessoa(pessoa);

        Pessoa pessoaExistente = pessoas.stream()
                .filter(p -> p.getId().equals(pessoa.getId()))
                .findFirst()
                .orElseThrow(() -> new RequisicaoInvalidaException(
                        "Nenhuma pessoa encontrada com o ID: " + pessoa.getId()));

        pessoaExistente.setNome(pessoa.getNome());
        pessoaExistente.setSobrenome(pessoa.getSobrenome());
        pessoaExistente.setEndereco(pessoa.getEndereco());
        pessoaExistente.setGenero(pessoa.getGenero());

        return pessoaExistente;
    }

   
    public void deletar(String id) {
        Long idLong = parseId(id);
        Pessoa pessoa = pessoas.stream()
                .filter(p -> p.getId().equals(idLong))
                .findFirst()
                .orElseThrow(() -> new RequisicaoInvalidaException(
                        "Nenhuma pessoa encontrada com o ID: " + id));
        pessoas.remove(pessoa);
    }

   
    private Long parseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new RequisicaoInvalidaException(
                    "ID inválido: '" + id + "'. O ID deve ser um número inteiro.");
        }
    }

    private void validarPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            throw new RequisicaoInvalidaException("Os dados não podem ser nulos.");
        }
        if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new RequisicaoInvalidaException("O nome da pessoa é obrigatório.");
        }
        if (pessoa.getSobrenome() == null || pessoa.getSobrenome().isBlank()) {
            throw new RequisicaoInvalidaException("O sobrenome da pessoa é obrigatório.");
        }
    }
}
