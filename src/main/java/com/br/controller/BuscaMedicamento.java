package com.br.controller;

import com.br.model.Medicamento;
import java.util.List;
import java.util.stream.Collectors;

public class BuscaMedicamento {
    private final List<Medicamento> medicamentos;

    public BuscaMedicamento(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<Medicamento> buscarPorNome(String nome) {
        return medicamentos.stream()
                .filter(m -> m.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Medicamento> buscarPorCategoria(String categoria) {
        return medicamentos.stream()
                .filter(m -> m.getCategoria() != null &&
                        m.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public List<Medicamento> buscarPorFaixaPreco(double precoMin, double precoMax) {
        return medicamentos.stream()
                .filter(m -> m.getPreco() >= precoMin && m.getPreco() <= precoMax)
                .collect(Collectors.toList());
    }

    public List<Medicamento> buscarPorFabricante(String fabricante) {
        return medicamentos.stream()
                .filter(m -> m.getFabricante() != null &&
                        m.getFabricante().equalsIgnoreCase(fabricante))
                .collect(Collectors.toList());
    }

    public List<Medicamento> buscarPorControle(boolean controlado) {
        return medicamentos.stream()
                .filter(m -> m.isControlado() == controlado)
                .collect(Collectors.toList());
    }

    public List<Medicamento> buscaCombinada(String nome, String categoria,
                                            double precoMin, double precoMax,
                                            boolean medicamentoControlado) {
        return medicamentos.stream()
                .filter(m -> nome.isEmpty() || m.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(m -> categoria.isEmpty() || (m.getCategoria() != null &&
                        m.getCategoria().equalsIgnoreCase(categoria)))
                .filter(m -> m.getPreco() >= precoMin && m.getPreco() <= precoMax)
                .filter(m -> m.isControlado() == medicamentoControlado)
                .collect(Collectors.toList());
    }

    public void limparFiltros() {
        // Este método é apenas para compatibilidade com a documentação
        System.out.println("Filtros limpos (não há estado persistente).");
    }
}