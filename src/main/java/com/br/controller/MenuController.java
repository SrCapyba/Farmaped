package com.br.controller;

import java.util.List;
import java.util.Scanner;

import com.br.interfaces.Menu;
import com.br.model.Cliente;
import com.br.model.Medicamento;
import com.br.repository.VendaRepository;
import com.br.repository.MedicamentoRepository;
import com.br.repository.FornecedorRepository;
import com.br.repository.ClienteRepository;

public class MenuController implements Menu {

    private final Scanner scanner;
    private final List<Medicamento> medicamentos;
    private final List<Cliente> clientes;
    private final FornecedorRepository fornecedorRepository;
    private final ClienteRepository clienteRepository;

    public MenuController(
            List<Medicamento> medicamentos,
            List<Cliente> clientes,
            FornecedorRepository fornecedorRepository,
            ClienteRepository clienteRepository) {

        this.scanner = new Scanner(System.in);
        this.medicamentos = medicamentos;
        this.clientes = clientes;
        this.fornecedorRepository = fornecedorRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("         FARMAPED");
            System.out.println("=================================");
            System.out.println("1 - Gestão de Clientes");
            System.out.println("2 - Gestão de Medicamentos");
            System.out.println("3 - Vendas");
            System.out.println("4 - Controle de Estoque");
            System.out.println("5 - Gestão de Fornecedores");
            System.out.println("0 - Sair");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    goCliente();
                    break;
                case 2:
                    goMedicamentos();
                    break;
                case 3:
                    goVendas();
                    break;
                case 4:
                    goEstoque();
                    break;
                case 5:
                    goFornecedores();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    @Override
    public void goCliente() {
        MenuClientes menuClientes = new MenuClientes(clienteRepository, medicamentos);
        menuClientes.iniciar();
    }

    @Override
    public void goMedicamentos() {
        MedicamentoRepository medicamentoRepository =
                new MedicamentoRepository(medicamentos);

        MedicamentoController medicamentoController =
                new MedicamentoController(medicamentoRepository);

        MenuMedicamentos menuMedicamentos =
                new MenuMedicamentos(medicamentoController);

        menuMedicamentos.iniciar();
    }

    @Override
    public void goVendas() {
        VendaRepository vendaRepository = new VendaRepository();
        VendaController vendaController = new VendaController(vendaRepository);

        MenuVendas menuVendas = new MenuVendas(
                vendaController,
                vendaRepository,
                medicamentos,
                clientes);

        menuVendas.iniciar();
    }

    @Override
    public void goEstoque() {
        MenuEstoque menuEstoque = new MenuEstoque(medicamentos);
        menuEstoque.iniciar();
    }

    @Override
    public void goFornecedores() {
        MenuFornecedores menuFornecedores = new MenuFornecedores(fornecedorRepository);
        menuFornecedores.iniciar();
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