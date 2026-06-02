package com.br.model;

public class Medicamento {

    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private boolean controlado;

    public Medicamento(
            int id,
            String nome,
            double preco,
            int quantidadeEstoque,
            boolean controlado) {

        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.controlado = controlado;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean isControlado() {
        return controlado;
    }
}