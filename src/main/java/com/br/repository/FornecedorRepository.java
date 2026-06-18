package com.br.repository;

import com.br.model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FornecedorRepository {
    private final List<Fornecedor> fornecedores = new ArrayList<>();
    private int contadorId = 1;

    public void salvar(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    public List<Fornecedor> listarTodos() {
        return new ArrayList<>(fornecedores);
    }

    public List<Fornecedor> listarAtivos() {
        return fornecedores.stream()
                .filter(Fornecedor::isAtivo)
                .collect(Collectors.toList());
    }

    public Fornecedor buscarPorId(int id) {
        return fornecedores.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Fornecedor buscarPorCnpj(String cnpj) {
        return fornecedores.stream()
                .filter(f -> f.getCnpj().equals(cnpj))
                .findFirst()
                .orElse(null);
    }

    public boolean removerPorId(int id) {
        Fornecedor fornecedor = buscarPorId(id);
        if (fornecedor != null) {
            fornecedores.remove(fornecedor);
            return true;
        }
        return false;
    }

    public boolean inativarPorId(int id) {
        Fornecedor fornecedor = buscarPorId(id);
        if (fornecedor != null) {
            fornecedor.setAtivo(false);
            return true;
        }
        return false;
    }

    public boolean atualizar(Fornecedor fornecedorAtualizado) {
        Fornecedor existente = buscarPorId(fornecedorAtualizado.getId());
        if (existente != null) {
            existente.setNome(fornecedorAtualizado.getNome());
            existente.setCnpj(fornecedorAtualizado.getCnpj());
            existente.setTelefone(fornecedorAtualizado.getTelefone());
            existente.setEmail(fornecedorAtualizado.getEmail());
            existente.setEndereco(fornecedorAtualizado.getEndereco());
            return true;
        }
        return false;
    }

    public int gerarProximoId() {
        return contadorId++;
    }
}