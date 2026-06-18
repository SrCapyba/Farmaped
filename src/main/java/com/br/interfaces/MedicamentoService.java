package com.br.interfaces;

import com.br.model.Medicamento;

import java.util.List;

public interface MedicamentoService {

    Medicamento cadastrar(String nome, double preco, int quantidadeEstoque, boolean controlado);

    List<Medicamento> listarTodos();

    Medicamento buscarPorId(int id);

    boolean atualizar(int id, String nome, double preco, int quantidadeEstoque, boolean controlado);

    boolean remover(int id);
}