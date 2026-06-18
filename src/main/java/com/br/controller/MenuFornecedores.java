package com.br.controller;

import com.br.model.Fornecedor;
import com.br.repository.FornecedorRepository;
import java.util.List;
import java.util.Scanner;

public class MenuFornecedores {
    private final Scanner scanner;
    private final FornecedorRepository repository;

    public MenuFornecedores(FornecedorRepository repository) {
        this.repository = repository;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n==============================");
            System.out.println("      MENU FORNECEDORES");
            System.out.println("==============================");
            System.out.println("1 - Cadastrar Fornecedor");
            System.out.println("2 - Listar Fornecedores");
            System.out.println("3 - Buscar Fornecedor por ID");
            System.out.println("4 - Atualizar Fornecedor");
            System.out.println("5 - Inativar Fornecedor");
            System.out.println("0 - Voltar");
            System.out.println("==============================");
            System.out.print("Escolha: ");

            opcao = lerInteiro();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarFornecedor();
                    break;
                case 2:
                    listarFornecedores();
                    break;
                case 3:
                    buscarPorId();
                    break;
                case 4:
                    atualizarFornecedor();
                    break;
                case 5:
                    inativarFornecedor();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarFornecedor() {
        System.out.println("\n===== CADASTRAR FORNECEDOR =====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine().trim();

        // Valida CNPJ duplicado
        if (repository.buscarPorCnpj(cnpj) != null) {
            System.out.println("CNPJ já cadastrado!");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine().trim();

        int id = repository.gerarProximoId();
        Fornecedor fornecedor = new Fornecedor(id, nome, cnpj, telefone, email, endereco);
        repository.salvar(fornecedor);

        System.out.println("Fornecedor cadastrado com sucesso! ID: " + id);
    }

    private void listarFornecedores() {
        List<Fornecedor> lista = repository.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
            return;
        }
        System.out.println("\n===== LISTA DE FORNECEDORES =====");
        for (Fornecedor f : lista) {
            System.out.println(f);
        }
    }

    private void buscarPorId() {
        System.out.print("ID do fornecedor: ");
        int id = lerInteiro();

        Fornecedor fornecedor = repository.buscarPorId(id);
        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        System.out.println("\n===== DADOS DO FORNECEDOR =====");
        System.out.println(fornecedor);
        System.out.printf("Telefone: %s%n", fornecedor.getTelefone());
        System.out.printf("Email: %s%n", fornecedor.getEmail());
        System.out.printf("Endereço: %s%n", fornecedor.getEndereco());
    }

    private void atualizarFornecedor() {
        System.out.print("ID do fornecedor: ");
        int id = lerInteiro();
        scanner.nextLine();

        Fornecedor existente = repository.buscarPorId(id);
        if (existente == null) {
            System.out.println("Fornecedor não encontrado.");
            return;
        }

        System.out.println("Dados atuais:");
        System.out.println(existente);

        System.out.print("Novo nome (Enter para manter): ");
        String nome = scanner.nextLine().trim();
        if (!nome.isEmpty()) existente.setNome(nome);

        System.out.print("Novo telefone (Enter para manter): ");
        String telefone = scanner.nextLine().trim();
        if (!telefone.isEmpty()) existente.setTelefone(telefone);

        System.out.print("Novo email (Enter para manter): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) existente.setEmail(email);

        System.out.print("Novo endereço (Enter para manter): ");
        String endereco = scanner.nextLine().trim();
        if (!endereco.isEmpty()) existente.setEndereco(endereco);

        System.out.println("Fornecedor atualizado com sucesso!");
    }

    private void inativarFornecedor() {
        System.out.print("ID do fornecedor: ");
        int id = lerInteiro();

        if (repository.inativarPorId(id)) {
            System.out.println("Fornecedor inativado com sucesso!");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    private int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}