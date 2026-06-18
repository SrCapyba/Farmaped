package com.br.controller;

import com.br.model.Medicamento;

import java.util.List;
import java.util.Scanner;

public class MenuMedicamentos {

    private final Scanner scanner;
    private final MedicamentoController medicamentoController;
    private final BuscaMedicamento buscaMedicamento;

    public MenuMedicamentos(MedicamentoController medicamentoController) {
        this.scanner = new Scanner(System.in);
        this.medicamentoController = medicamentoController;
        this.buscaMedicamento = new BuscaMedicamento(medicamentoController.listarTodos());
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n==============================");
            System.out.println(" MENU MEDICAMENTOS");
            System.out.println("==============================");
            System.out.println("1 - Cadastrar medicamento");
            System.out.println("2 - Listar medicamentos");
            System.out.println("3 - Buscar medicamento por ID");
            System.out.println("4 - Atualizar medicamento");
            System.out.println("5 - Remover medicamento");
            System.out.println("6 - Busca avançada de medicamentos"); // NOVO
            System.out.println("0 - Voltar");
            System.out.println("==============================");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscarPorId();
                    break;
                case 4:
                    atualizar();
                    break;
                case 5:
                    remover();
                    break;
                case 6:
                    buscaAvancada(); // NOVO
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.println("\n===== CADASTRAR MEDICAMENTO =====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = lerDouble();

        System.out.print("Quantidade em estoque: ");
        int quantidadeEstoque = lerInteiro();

        System.out.print("É controlado? (s/n): ");
        boolean controlado = lerSimOuNao();

        Medicamento medicamento = medicamentoController.cadastrar(
                nome,
                preco,
                quantidadeEstoque,
                controlado
        );

        if (medicamento != null) {
            System.out.println("Medicamento cadastrado com sucesso!");
            System.out.println(medicamento);
        }
    }

    private void listar() {
        System.out.println("\n===== LISTA DE MEDICAMENTOS =====");

        List<Medicamento> medicamentos = medicamentoController.listarTodos();

        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
            return;
        }

        for (Medicamento medicamento : medicamentos) {
            System.out.println(medicamento);
        }
    }

    private void buscarPorId() {
        System.out.println("\n===== BUSCAR MEDICAMENTO =====");

        System.out.print("ID: ");
        int id = lerInteiro();

        Medicamento medicamento = medicamentoController.buscarPorId(id);

        if (medicamento == null) {
            System.out.println("Medicamento não encontrado.");
            return;
        }

        System.out.println(medicamento);
    }

    // NOVO: Busca avançada de medicamentos
    private void buscaAvancada() {
        System.out.println("\n===== BUSCA AVANÇADA DE MEDICAMENTOS =====");
        System.out.println("Deixe em branco para ignorar o filtro.");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Preço mínimo: ");
        String precoMinStr = scanner.nextLine();
        double precoMin = precoMinStr.isEmpty() ? 0 : Double.parseDouble(precoMinStr.replace(",", "."));

        System.out.print("Preço máximo: ");
        String precoMaxStr = scanner.nextLine();
        double precoMax = precoMaxStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(precoMaxStr.replace(",", "."));

        System.out.print("Filtrar por controlado? (s/n): ");
        boolean controlado = scanner.nextLine().equalsIgnoreCase("s");

        List<Medicamento> resultados = buscaMedicamento.buscaCombinada(
                nome, categoria, precoMin, precoMax, controlado
        );

        if (resultados.isEmpty()) {
            System.out.println("Nenhum medicamento encontrado.");
            return;
        }

        System.out.println("\n===== RESULTADOS (" + resultados.size() + ") =====");
        for (Medicamento m : resultados) {
            System.out.println(m);
        }
    }

    private void atualizar() {
        System.out.println("\n===== ATUALIZAR MEDICAMENTO =====");

        System.out.print("ID do medicamento: ");
        int id = lerInteiro();

        Medicamento medicamento = medicamentoController.buscarPorId(id);

        if (medicamento == null) {
            System.out.println("Medicamento não encontrado.");
            return;
        }

        System.out.println("Medicamento atual:");
        System.out.println(medicamento);

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo preço: ");
        double preco = lerDouble();

        System.out.print("Nova quantidade em estoque: ");
        int quantidadeEstoque = lerInteiro();

        System.out.print("É controlado? (s/n): ");
        boolean controlado = lerSimOuNao();

        boolean atualizado = medicamentoController.atualizar(
                id,
                nome,
                preco,
                quantidadeEstoque,
                controlado
        );

        if (atualizado) {
            System.out.println("Medicamento atualizado com sucesso!");
        } else {
            System.out.println("Não foi possível atualizar o medicamento.");
        }
    }

    private void remover() {
        System.out.println("\n===== REMOVER MEDICAMENTO =====");

        System.out.print("ID do medicamento: ");
        int id = lerInteiro();

        boolean removido = medicamentoController.remover(id);

        if (removido) {
            System.out.println("Medicamento removido com sucesso!");
        } else {
            System.out.println("Medicamento não encontrado.");
        }
    }

    private int lerInteiro() {
        while (true) {
            try {
                String entrada = scanner.nextLine();
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número inteiro válido: ");
            }
        }
    }

    private double lerDouble() {
        while (true) {
            try {
                String entrada = scanner.nextLine().replace(",", ".");
                return Double.parseDouble(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor válido: ");
            }
        }
    }

    private boolean lerSimOuNao() {
        while (true) {
            String resposta = scanner.nextLine().trim();

            if (resposta.equalsIgnoreCase("s")) {
                return true;
            }

            if (resposta.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.print("Digite apenas s ou n: ");
        }
    }
}