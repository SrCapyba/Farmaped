package com.br.controller;

import com.br.interfaces.MedicamentoService;
import com.br.model.Medicamento;
import com.br.repository.MedicamentoRepository;

import java.util.List;

public class MedicamentoController implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoController(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public Medicamento cadastrar(String nome, double preco, int quantidadeEstoque, boolean controlado) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do medicamento não pode ser vazio.");
            return null;
        }

        if (preco < 0) {
            System.out.println("Preço não pode ser negativo.");
            return null;
        }

        if (quantidadeEstoque < 0) {
            System.out.println("Quantidade em estoque não pode ser negativa.");
            return null;
        }

        int id = medicamentoRepository.gerarProximoId();

        Medicamento medicamento = new Medicamento(
                id,
                nome,
                preco,
                quantidadeEstoque,
                controlado
        );

        medicamentoRepository.salvar(medicamento);

        return medicamento;
    }

    @Override
    public List<Medicamento> listarTodos() {
        return medicamentoRepository.listarTodos();
    }

    @Override
    public Medicamento buscarPorId(int id) {
        return medicamentoRepository.buscarPorId(id);
    }

    @Override
    public boolean atualizar(int id, String nome, double preco, int quantidadeEstoque, boolean controlado) {
        Medicamento medicamento = medicamentoRepository.buscarPorId(id);

        if (medicamento == null) {
            return false;
        }

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do medicamento não pode ser vazio.");
            return false;
        }

        if (preco < 0) {
            System.out.println("Preço não pode ser negativo.");
            return false;
        }

        if (quantidadeEstoque < 0) {
            System.out.println("Quantidade em estoque não pode ser negativa.");
            return false;
        }

        medicamento.setNome(nome);
        medicamento.setPreco(preco);
        medicamento.setQuantidadeEstoque(quantidadeEstoque);
        medicamento.setControlado(controlado);

        return true;
    }

    @Override
    public boolean remover(int id) {
        return medicamentoRepository.removerPorId(id);
    }
}