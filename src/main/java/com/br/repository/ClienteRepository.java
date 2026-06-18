package com.br.repository;

import com.br.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();
    private int contadorId = 1;

    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Cliente buscarPorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clientes.stream()
                .filter(c -> c.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean removerPorId(int id) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
            return true;
        }
        return false;
    }

    public int gerarProximoId() {
        return contadorId++;
    }
}