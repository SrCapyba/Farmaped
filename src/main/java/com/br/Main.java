package com.br;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.br.controller.MenuController;
import com.br.model.Cliente;
import com.br.model.Medicamento;
import com.br.repository.FornecedorRepository;
import com.br.repository.ClienteRepository;

public class Main {

    public static void main(String[] args) {

        // Dados iniciais - Medicamentos
        List<Medicamento> medicamentos = new ArrayList<>();

        medicamentos.add(
                new Medicamento(
                        1,
                        "Dipirona",
                        10.50,
                        50,
                        false));

        medicamentos.add(
                new Medicamento(
                        2,
                        "Rivotril",
                        35.90,
                        20,
                        true));

        // Dados iniciais - Clientes (com ID)
        List<Cliente> clientes = new ArrayList<>();

        // ORDEM CORRETA DOS PARÂMETROS:
        // id, nome, sexo, idade, cpf, dataNascimento, telefone, estado,
        // cidade, bairro, logradouro, numeroResidencia, cep, email,
        // alergia, nomeAlergia, remedioControlado, nomeRemedioControlado
        clientes.add(
                new Cliente(
                        1,
                        "João Silva",
                        "M",
                        30,
                        "12345678900",
                        LocalDate.of(1996, 1, 15),
                        "(11) 99999-8888",
                        "SP",
                        "São Paulo",
                        "Centro",
                        "Rua das Flores",
                        100,
                        "01000-000",
                        "joao@email.com",
                        false,
                        new ArrayList<>(),
                        false,
                        new ArrayList<>()));

        clientes.add(
                new Cliente(
                        2,
                        "Maria Souza",
                        "F",
                        25,
                        "98765432100",
                        LocalDate.of(2001, 5, 20),
                        "(11) 98888-7777",
                        "RJ",
                        "Rio de Janeiro",
                        "Copacabana",
                        "Av. Atlântica",
                        500,
                        "22000-000",
                        "maria@email.com",
                        true,
                        Arrays.asList("Dipirona"),
                        true,
                        Arrays.asList("Rivotril")));

        // Repositories
        FornecedorRepository fornecedorRepository = new FornecedorRepository();
        ClienteRepository clienteRepository = new ClienteRepository();

        // Adiciona os clientes iniciais ao repositório
        for (Cliente c : clientes) {
            clienteRepository.salvar(c);
        }

        MenuController menu = new MenuController(
                medicamentos,
                clientes,
                fornecedorRepository,
                clienteRepository);

        menu.exibirMenu();
    }
}