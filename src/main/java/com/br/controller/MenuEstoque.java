package com.br.controller;

import com.br.model.Medicamento;
import java.util.List;
import java.util.Scanner;

public class MenuEstoque {
    private final Scanner scanner;
    private final List<Medicamento> medicamentos;

    public MenuEstoque(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n==============================");
            System.out.println("        MENU ESTOQUE");
            System.out.println("==============================");
            System.out.println("1 - Consultar Estoque");
            System.out.println("2 - Adicionar ao Estoque");
            System.out.println("3 - Ver medicamentos com estoque baixo");
            System.out.println("4 - Relatório completo de estoque");
            System.out.println("0 - Voltar");
            System.out.println("==============================");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    consultarEstoque();
                    break;
                case 2:
                    adicionarSaldo();
                    break;
                case 3:
                    verEstoqueBaixo();
                    break;
                case 4:
                    relatorioCompleto();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void consultarEstoque() {
        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
            return;
        }
        System.out.println("\n===== ESTOQUE ATUAL =====");
        for (Medicamento m : medicamentos) {
            System.out.printf("ID: %d | %s | Estoque: %d unidades | Preço: R$ %.2f%n",
                    m.getId(), m.getNome(), m.getQuantidadeEstoque(), m.getPreco());
        }
    }

    private void adicionarSaldo() {
        consultarEstoque();
        if (medicamentos.isEmpty()) return;

        System.out.print("\nDigite o ID do medicamento: ");
        int id = lerInteiro();

        Medicamento medSelecionado = null;
        for (Medicamento m : medicamentos) {
            if (m.getId() == id) {
                medSelecionado = m;
                break;
            }
        }

        if (medSelecionado == null) {
            System.out.println("Medicamento não encontrado.");
            return;
        }

        System.out.print("Quantidade a adicionar: ");
        int qtd = lerInteiro();

        if (qtd > 0) {
            medSelecionado.setQuantidadeEstoque(
                    medSelecionado.getQuantidadeEstoque() + qtd
            );
            System.out.printf("Estoque atualizado! Novo saldo: %d unidades%n",
                    medSelecionado.getQuantidadeEstoque());
        } else {
            System.out.println("A quantidade deve ser maior que zero.");
        }
    }

    private void verEstoqueBaixo() {
        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
            return;
        }

        System.out.println("\n===== MEDICAMENTOS COM ESTOQUE BAIXO (<= 10) =====");
        boolean encontrou = false;
        for (Medicamento m : medicamentos) {
            if (m.getQuantidadeEstoque() <= 10) {
                System.out.printf("ID: %d | %s | Estoque: %d unidades%n",
                        m.getId(), m.getNome(), m.getQuantidadeEstoque());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum medicamento com estoque baixo.");
        }
    }

    private void relatorioCompleto() {
        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
            return;
        }

        System.out.println("\n===== RELATÓRIO COMPLETO DE ESTOQUE =====");
        int totalItens = 0;
        double valorTotalEstoque = 0;

        for (Medicamento m : medicamentos) {
            int qtd = m.getQuantidadeEstoque();
            totalItens += qtd;
            valorTotalEstoque += qtd * m.getPreco();

            System.out.printf("ID: %d | %s | Qtd: %d | Valor: R$ %.2f%n",
                    m.getId(), m.getNome(), qtd, qtd * m.getPreco());
        }

        System.out.println("\n--- RESUMO ---");
        System.out.printf("Total de itens em estoque: %d%n", totalItens);
        System.out.printf("Valor total do estoque: R$ %.2f%n", valorTotalEstoque);
        System.out.printf("Quantidade de medicamentos: %d%n", medicamentos.size());
    }

    private int lerInteiro() {
        while (true) {
            try {
                String entrada = scanner.nextLine();
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}