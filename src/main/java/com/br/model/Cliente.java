package com.br.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String sexo;
    private int idade;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numeroResidencia;
    private String cep;
    private String email;
    private boolean allergy;
    private List<String> nomeAlergia;
    private boolean remedioControlado;
    private List<String> nomeRemedioControlado;

    // Construtor completo com ID - ORDEM CORRIGIDA
    public Cliente(int id, String nome, String sexo, int idade, String cpf,
                   LocalDate dataNascimento, String telefone, String estado,
                   String cidade, String bairro, String logradouro, int numeroResidencia,
                   String cep, String email, boolean alergia, List<String> nomeAlergia,
                   boolean remedioControlado, List<String> nomeRemedioControlado) {

        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome e CPF são obrigatórios e não podem ser vazios.");
        }

        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numeroResidencia = numeroResidencia;
        this.cep = cep;
        this.email = email;
        this.allergy = alergia;
        this.nomeAlergia = alergia ? new ArrayList<>(nomeAlergia) : new ArrayList<>();
        this.remedioControlado = remedioControlado;
        this.nomeRemedioControlado = remedioControlado ? new ArrayList<>(nomeRemedioControlado) : new ArrayList<>();
    }

    // Construtor simplificado (para compatibilidade com o sistema existente)
    public Cliente(String nome, String cpf) {
        this(0, nome, "", 0, cpf, LocalDate.now(), "", "", "", "", "", 0, "", "", false, new ArrayList<>(), false, new ArrayList<>());
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSexo() { return sexo; }
    public int getIdade() { return idade; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getTelefone() { return telefone; }
    public String getEstado() { return estado; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getLogradouro() { return logradouro; }
    public int getNumeroResidencia() { return numeroResidencia; }
    public String getCep() { return cep; }
    public String getEmail() { return email; }
    public boolean isAlergia() { return allergy; }
    public List<String> getNomeAlergia() { return nomeAlergia; }
    public boolean isRemedioControlado() { return remedioControlado; }
    public List<String> getNomeRemedioControlado() { return nomeRemedioControlado; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public void setNumeroResidencia(int numeroResidencia) { this.numeroResidencia = numeroResidencia; }
    public void setCep(String cep) { this.cep = cep; }
    public void setEmail(String email) { this.email = email; }
    public void setNomeAlergia(List<String> nomeAlergia) {
        this.nomeAlergia = new ArrayList<>(nomeAlergia);
        this.allergy = !nomeAlergia.isEmpty();
    }
    public void setNomeRemedioControlado(List<String> nomeRemedioControlado) {
        this.nomeRemedioControlado = new ArrayList<>(nomeRemedioControlado);
        this.remedioControlado = !nomeRemedioControlado.isEmpty();
    }

    public void atualizarDados(String attNome, String attSexo, int attIdade, String attCpf,
                               LocalDate attDataNascimento, String attTelefone, String attEstado,
                               String attCidade, String attBairro, String attLogradouro,
                               int attNumeroResidencia, String attCep, String attEmail,
                               List<String> attNomeAlergia, List<String> attNomeRemedioControlado) {
        this.nome = attNome;
        this.sexo = attSexo;
        this.idade = attIdade;
        this.cpf = attCpf;
        this.dataNascimento = attDataNascimento;
        this.telefone = attTelefone;
        this.estado = attEstado;
        this.cidade = attCidade;
        this.bairro = attBairro;
        this.logradouro = attLogradouro;
        this.numeroResidencia = attNumeroResidencia;
        this.cep = attCep;
        this.email = attEmail;
        this.nomeAlergia = new ArrayList<>(attNomeAlergia);
        this.allergy = !attNomeAlergia.isEmpty();
        this.nomeRemedioControlado = new ArrayList<>(attNomeRemedioControlado);
        this.remedioControlado = !attNomeRemedioControlado.isEmpty();
        System.out.println("Dados do cliente " + this.nome + " atualizados com sucesso!");
    }

    @Override
    public String toString() {
        return "----------------------------------------------------------------------------\n" +
                "ID: " + id + " | Cliente: " + nome + " (" + sexo + ") | CPF: " + cpf + " | Idade: " + idade + "\n" +
                "Contato: " + telefone + " | Email: " + email + "\n" +
                "Localidade: " + cidade + "/" + estado + " - Bairro: " + bairro + "\n" +
                "Logradouro: " + logradouro + ", Nº " + numeroResidencia + " (CEP: " + cep + ")\n" +
                "Alergias: " + (allergy ? nomeAlergia : "Nenhuma") + " | Uso Controlados: " + (remedioControlado ? nomeRemedioControlado : "Não") + "\n" +
                "----------------------------------------------------------------------------";
    }
}