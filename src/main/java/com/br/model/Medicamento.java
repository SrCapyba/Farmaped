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
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setControlado(boolean controlado) {
        this.controlado = controlado;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d | Controlado: %s",
                id,
                nome,
                preco,
                quantidadeEstoque,
                controlado ? "Sim" : "Não"
        );
    }
}