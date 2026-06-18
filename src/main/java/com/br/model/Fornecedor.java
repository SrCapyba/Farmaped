package com.br.model;

public class Fornecedor {
    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;
    private boolean ativo;

    // Construtor completo
    public Fornecedor(int id, String nome, String cnpj, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.ativo = true;
    }

    // Construtor simplificado (para compatibilidade)
    public Fornecedor(int id, String nome, String cnpj) {
        this(id, nome, cnpj, "", "", "");
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | CNPJ: %s | Ativo: %s",
                id, nome, cnpj, ativo ? "Sim" : "Não");
    }
}