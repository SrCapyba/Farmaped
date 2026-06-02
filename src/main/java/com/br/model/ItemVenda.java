package com.br.model;

public class ItemVenda {

    private Medicamento medicamento;
    private int quantidade;
    private double precoUnitario;

    public ItemVenda(Medicamento medicamento, int quantidade) {
        this.medicamento = medicamento;
        this.quantidade = quantidade;
        this.precoUnitario = medicamento.getPreco();
    }

    public double calcularSubtotal() {
        return quantidade * precoUnitario;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }
}