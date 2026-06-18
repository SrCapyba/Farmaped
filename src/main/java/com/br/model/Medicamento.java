package com.br.model;

public class Medicamento {

    private int id;
    private String nome;
    private String descricao;      // NOVO
    private String fabricante;     // NOVO
    private String categoria;      // NOVO
    private double preco;
    private int quantidadeEstoque;
    private String codigoBarras;   // NOVO
    private boolean controlado;
    private boolean ativo;         // NOVO

    // Construtor completo com todos os campos
    public Medicamento(int id, String nome, String descricao, String fabricante,
                       String categoria, double preco, int quantidadeEstoque,
                       String codigoBarras, boolean controlado, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.codigoBarras = codigoBarras;
        this.controlado = controlado;
        this.ativo = ativo;
    }

    // Construtor simplificado (para compatibilidade com código existente)
    public Medicamento(int id, String nome, double preco, int quantidadeEstoque, boolean controlado) {
        this(id, nome, "", "", "", preco, quantidadeEstoque, "", controlado, true);
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getFabricante() { return fabricante; }
    public String getCategoria() { return categoria; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public String getCodigoBarras() { return codigoBarras; }
    public boolean isControlado() { return controlado; }
    public boolean isAtivo() { return ativo; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
    public void setControlado(boolean controlado) { this.controlado = controlado; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d | Controlado: %s | Ativo: %s",
                id,
                nome,
                preco,
                quantidadeEstoque,
                controlado ? "Sim" : "Não",
                ativo ? "Sim" : "Não"
        );
    }
}