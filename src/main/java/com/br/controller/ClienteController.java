package com.br.controller;

import com.br.model.Cliente;
import com.br.model.Medicamento;
import com.br.repository.ClienteRepository;
import java.time.LocalDate;
import java.util.List;

public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(String nome, String sexo, int idade, String cpf,
                             LocalDate dataNascimento, String telefone, String estado,
                             String cidade, String bairro, String logradouro,
                             int numeroResidencia, String cep, String email,
                             boolean alergia, List<String> nomeAlergia,
                             boolean remedioControlado, List<String> nomeRemedioControlado) {

        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return null;
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            System.out.println("CPF não pode ser vazio.");
            return null;
        }
        if (clienteRepository.buscarPorCpf(cpf) != null) {
            System.out.println("CPF já cadastrado!");
            return null;
        }

        int id = clienteRepository.gerarProximoId();
        Cliente cliente = new Cliente(id, nome, sexo, idade, cpf, dataNascimento,
                telefone, estado, cidade, bairro, logradouro,
                numeroResidencia, cep, email, alergia,
                nomeAlergia, remedioControlado, nomeRemedioControlado);

        clienteRepository.salvar(cliente);
        return cliente;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.buscarPorCpf(cpf);
    }

    public Cliente buscarPorId(int id) {
        return clienteRepository.buscarPorId(id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.buscarPorNome(nome);
    }

    public boolean atualizar(int id, String nome, String sexo, int idade, String cpf,
                             LocalDate dataNascimento, String telefone, String estado,
                             String cidade, String bairro, String logradouro,
                             int numeroResidencia, String cep, String email,
                             List<String> nomeAlergia, List<String> nomeRemedioControlado) {

        Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return false;
        }

        cliente.setNome(nome);
        cliente.setSexo(sexo);
        cliente.setIdade(idade);
        cliente.setDataNascimento(dataNascimento);
        cliente.setTelefone(telefone);
        cliente.setEstado(estado);
        cliente.setCidade(cidade);
        cliente.setBairro(bairro);
        cliente.setLogradouro(logradouro);
        cliente.setNumeroResidencia(numeroResidencia);
        cliente.setCep(cep);
        cliente.setEmail(email);
        cliente.setNomeAlergia(nomeAlergia);
        cliente.setNomeRemedioControlado(nomeRemedioControlado);

        return true;
    }

    public boolean remover(int id) {
        return clienteRepository.removerPorId(id);
    }
}