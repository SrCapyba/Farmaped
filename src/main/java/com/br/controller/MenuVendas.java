package com.br.controller;

import java.util.List;
import java.util.Scanner;

import com.br.model.Cliente;
import com.br.model.Medicamento;
import com.br.model.Venda;

import com.br.repository.VendaRepository;

public class MenuVendas {

    private final Scanner scanner;
    private final VendaController vendaController;
    private final VendaRepository vendaRepository;
    private final List<Medicamento> medicamentos;
    private final List<Cliente> clientes;

    public MenuVendas(
            VendaController vendaController,
            VendaRepository vendaRepository,
            List<Medicamento> medicamentos,
            List<Cliente> clientes) {

        this.vendaController = vendaController;
        this.vendaRepository = vendaRepository;
        this.medicamentos = medicamentos;
        this.clientes = clientes;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {

        int opcao;

        do {

            System.out.println("\n==============================");
            System.out.println("        MENU VENDAS");
            System.out.println("==============================");
            System.out.println("1 - Adicionar Item");
            System.out.println("2 - Visualizar Carrinho");
            System.out.println("3 - Efetuar Venda");
            System.out.println("4 - Cancelar Venda");
            System.out.println("5 - Histórico de Vendas");
            System.out.println("0 - Voltar");
            System.out.println("==============================");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {

                case 1:
                    adicionarItem();
                    break;

                case 2:
                    vendaController.imprimirNota();
                    break;

                case 3:
                    efetuarVenda();
                    break;

                case 4:
                    vendaController.cancelarVenda();
                    break;

                case 5:
                    listarHistorico();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void adicionarItem() {

        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
            return;
        }

        System.out.println("\n===== MEDICAMENTOS =====");

        for (Medicamento medicamento : medicamentos) {

            System.out.printf(
                    "ID: %d | %s | R$ %.2f | Estoque: %d%n",
                    medicamento.getId(),
                    medicamento.getNome(),
                    medicamento.getPreco(),
                    medicamento.getQuantidadeEstoque());
        }

        System.out.print("ID do medicamento: ");
        int id = lerInteiro();

        Medicamento medicamento = buscarMedicamento(id);

        if (medicamento == null) {
            System.out.println("Medicamento não encontrado.");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = lerInteiro();

        boolean receita = true;

        if (medicamento.isControlado()) {

            System.out.print("Receita apresentada? (s/n): ");

            // CORRIGIDO: usar nextLine() em vez de next()
            String resposta = scanner.nextLine();

            receita = resposta.equalsIgnoreCase("s");
        }

        boolean sucesso = vendaController.adicionarItem(
                medicamento,
                quantidade,
                receita);

        if (sucesso) {
            System.out.println("Item adicionado ao carrinho.");
        }
    }

    private void efetuarVenda() {

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n===== CLIENTES =====");

        for (int i = 0; i < clientes.size(); i++) {

            Cliente cliente = clientes.get(i);

            System.out.printf(
                    "%d - %s (%s)%n",
                    i + 1,
                    cliente.getNome(),
                    cliente.getCpf());
        }

        System.out.print("Escolha o cliente: ");
        int indice = lerInteiro() - 1;

        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Cliente inválido.");
            return;
        }

        Cliente cliente = clientes.get(indice);

        System.out.print("Desconto: ");
        // CORRIGIDO: usar lerDouble() em vez de scanner.nextDouble()
        double desconto = lerDouble();

        System.out.print("Forma de pagamento: ");
        String formaPagamento = scanner.nextLine();

        vendaController.efetuarVenda(
                cliente,
                desconto,
                formaPagamento);
    }

    private void listarHistorico() {

        List<Venda> vendas = vendaRepository.listarTodas();

        if (vendas.isEmpty()) {

            System.out.println("Nenhuma venda encontrada.");
            return;
        }

        System.out.println("\n===== HISTÓRICO =====");

        for (Venda venda : vendas) {

            System.out.printf(
                    "Venda #%d | Cliente: %s | Total: R$ %.2f | %s%n",
                    venda.getIdVenda(),
                    venda.getCliente().getNome(),
                    venda.getValorTotal(),
                    venda.getData());
        }
    }

    private Medicamento buscarMedicamento(int id) {

        for (Medicamento medicamento : medicamentos) {

            if (medicamento.getId() == id) {
                return medicamento;
            }
        }

        return null;
    }

    // CORRIGIDO: Método lerInteiro melhorado
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

    // NOVO: Método para ler double corretamente
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
}