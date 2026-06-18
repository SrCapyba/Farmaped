package com.br.controller;

import com.br.model.Cliente;
import com.br.repository.ClienteRepository;
import java.util.List;
import java.util.stream.Collectors;

public class BuscaAvancadaCliente {
    private final ClienteRepository repository;

    public BuscaAvancadaCliente(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    public Cliente buscarPorCpf(String cpf) {
        return repository.buscarPorCpf(cpf);
    }

    public List<Cliente> buscarPorFaixaEtaria(int idadeMin, int idadeMax) {
        return repository.listarTodos().stream()
                .filter(c -> c.getIdade() >= idadeMin && c.getIdade() <= idadeMax)
                .collect(Collectors.toList());
    }

    public List<Cliente> buscarPorLocalizacao(String cidade, String estado) {
        return repository.listarTodos().stream()
                .filter(c -> (cidade.isEmpty() || c.getCidade().equalsIgnoreCase(cidade)) &&
                        (estado.isEmpty() || c.getEstado().equalsIgnoreCase(estado)))
                .collect(Collectors.toList());
    }

    public List<Cliente> buscarPorAlergia(String nomeAlergia) {
        return repository.listarTodos().stream()
                .filter(c -> c.isAlergia() && c.getNomeAlergia().stream()
                        .anyMatch(a -> a.equalsIgnoreCase(nomeAlergia)))
                .collect(Collectors.toList());
    }

    public List<Cliente> buscarPorRemedioControlado(String nomeRemedio) {
        return repository.listarTodos().stream()
                .filter(c -> c.isRemedioControlado() && c.getNomeRemedioControlado().stream()
                        .anyMatch(r -> r.equalsIgnoreCase(nomeRemedio)))
                .collect(Collectors.toList());
    }

    public List<Cliente> buscaCombinada(String nome, String cpf, int idadeMin, int idadeMax,
                                        String cidade, String estado, boolean filtrarAlergia,
                                        boolean filtrarControlado) {
        return repository.listarTodos().stream()
                .filter(c -> nome.isEmpty() || c.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(c -> cpf.isEmpty() || c.getCpf().equals(cpf))
                .filter(c -> c.getIdade() >= idadeMin && c.getIdade() <= idadeMax)
                .filter(c -> cidade.isEmpty() || c.getCidade().equalsIgnoreCase(cidade))
                .filter(c -> estado.isEmpty() || c.getEstado().equalsIgnoreCase(estado))
                .filter(c -> !filtrarAlergia || c.isAlergia())
                .filter(c -> !filtrarControlado || c.isRemedioControlado())
                .collect(Collectors.toList());
    }
}