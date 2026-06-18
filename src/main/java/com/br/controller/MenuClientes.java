package com.br.controller;

import com.br.model.Cliente;
import com.br.model.Medicamento;
import com.br.repository.ClienteRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuClientes {
    private final Scanner scanner;
    private final ClienteController clienteController;
    private final BuscaAvancadaCliente buscaAvancada;
    private final ControleRestricoes controleRestricoes;
    private final List<Medicamento> medicamentos;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuClientes(ClienteRepository clienteRepository, List<Medicamento> medicamentos) {
        this.scanner = new Scanner(System.in);
        this.clienteController = new ClienteController(clienteRepository);
        this.buscaAvancada = new BuscaAvancadaCliente(clienteRepository);
        this.controleRestricoes = new ControleRestricoes();
        this.medicamentos = medicamentos;
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n==============================");
            System.out.println("      MENU CLIENTES");
            System.out.println("==============================");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Buscar Cliente por CPF");
            System.out.println("4 - Buscar Cliente por Nome");
            System.out.println("5 - Atualizar Cliente");
            System.out.println("6 - Remover Cliente");
            System.out.println("7 - Busca Avançada");
            System.out.println("8 - Simular Restrições de Venda");
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
                    buscarPorCpf();
                    break;
                case 4:
                    buscarPorNome();
                    break;
                case 5:
                    atualizar();
                    break;
                case 6:
                    remover();
                    break;
                case 7:
                    buscaAvancada();
                    break;
                case 8:
                    simularRestricoes();
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
        System.out.println("\n===== CADASTRAR CLIENTE =====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Sexo (M/F): ");
        String sexo = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = lerInteiro();

        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        LocalDate dataNascimento = null;
        try {
            dataNascimento = LocalDate.parse(scanner.nextLine(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato inválido! Usando data atual.");
            dataNascimento = LocalDate.now();
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Estado (UF): ");
        String estado = scanner.nextLine();

        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Logradouro (Rua/Av): ");
        String logradouro = scanner.nextLine();

        System.out.print("Número da Residência: ");
        int numeroResidencia = lerInteiro();

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        System.out.print("Possui alergia a medicamentos? (s/n): ");
        boolean temAlergia = scanner.nextLine().equalsIgnoreCase("s");
        List<String> alergias = new ArrayList<>();
        if (temAlergia) {
            System.out.print("Digite as alergias separadas por vírgula: ");
            String alergiasStr = scanner.nextLine();
            alergias = Arrays.asList(alergiasStr.split(","));
        }

        System.out.print("Faz uso de medicamento controlado? (s/n): ");
        boolean temControlado = scanner.nextLine().equalsIgnoreCase("s");
        List<String> controlados = new ArrayList<>();
        if (temControlado) {
            System.out.print("Digite os medicamentos controlados separados por vírgula: ");
            String controladosStr = scanner.nextLine();
            controlados = Arrays.asList(controladosStr.split(","));
        }

        Cliente cliente = clienteController.cadastrar(nome, sexo, idade, cpf, dataNascimento,
                telefone, estado, cidade, bairro, logradouro, numeroResidencia,
                cep, email, temAlergia, alergias, temControlado, controlados);

        if (cliente != null) {
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println(cliente);
        }
    }

    private void listar() {
        System.out.println("\n===== LISTA DE CLIENTES =====");
        List<Cliente> clientes = clienteController.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private void buscarPorCpf() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteController.buscarPorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println(cliente);
    }

    private void buscarPorNome() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        List<Cliente> clientes = clienteController.buscarPorNome(nome);

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    // CORRIGIDO: Método atualizar com data de nascimento
    private void atualizar() {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteController.buscarPorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Cliente encontrado:");
        System.out.println(cliente);

        System.out.println("\nDigite os novos dados (Enter para manter o atual):");

        System.out.print("Nome [" + cliente.getNome() + "]: ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) nome = cliente.getNome();

        System.out.print("Sexo [" + cliente.getSexo() + "]: ");
        String sexo = scanner.nextLine();
        if (sexo.isEmpty()) sexo = cliente.getSexo();

        System.out.print("Idade [" + cliente.getIdade() + "]: ");
        String idadeStr = scanner.nextLine();
        int idade = idadeStr.isEmpty() ? cliente.getIdade() : Integer.parseInt(idadeStr);

        // CORRIGIDO: Adicionando data de nascimento
        System.out.print("Data de Nascimento [" + cliente.getDataNascimento() + "]: ");
        String dataStr = scanner.nextLine();
        LocalDate dataNascimento = cliente.getDataNascimento();
        if (!dataStr.isEmpty()) {
            try {
                dataNascimento = LocalDate.parse(dataStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido! Mantendo data atual.");
            }
        }

        System.out.print("Telefone [" + cliente.getTelefone() + "]: ");
        String telefone = scanner.nextLine();
        if (telefone.isEmpty()) telefone = cliente.getTelefone();

        System.out.print("E-mail [" + cliente.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (email.isEmpty()) email = cliente.getEmail();

        System.out.print("Cidade [" + cliente.getCidade() + "]: ");
        String cidade = scanner.nextLine();
        if (cidade.isEmpty()) cidade = cliente.getCidade();

        System.out.print("Estado [" + cliente.getEstado() + "]: ");
        String estado = scanner.nextLine();
        if (estado.isEmpty()) estado = cliente.getEstado();

        System.out.print("Bairro [" + cliente.getBairro() + "]: ");
        String bairro = scanner.nextLine();
        if (bairro.isEmpty()) bairro = cliente.getBairro();

        System.out.print("Logradouro [" + cliente.getLogradouro() + "]: ");
        String logradouro = scanner.nextLine();
        if (logradouro.isEmpty()) logradouro = cliente.getLogradouro();

        System.out.print("Número [" + cliente.getNumeroResidencia() + "]: ");
        String numStr = scanner.nextLine();
        int numero = numStr.isEmpty() ? cliente.getNumeroResidencia() : Integer.parseInt(numStr);

        System.out.print("CEP [" + cliente.getCep() + "]: ");
        String cep = scanner.nextLine();
        if (cep.isEmpty()) cep = cliente.getCep();

        // CORRIGIDO: Passando a data de nascimento atualizada
        boolean atualizado = clienteController.atualizar(
                cliente.getId(), nome, sexo, idade, cliente.getCpf(),
                dataNascimento, // <- DATA ATUALIZADA
                telefone, estado, cidade,
                bairro, logradouro, numero, cep, email,
                cliente.getNomeAlergia(), cliente.getNomeRemedioControlado()
        );

        if (atualizado) {
            System.out.println("Cliente atualizado com sucesso!");
        }
    }

    private void remover() {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteController.buscarPorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Tem certeza que deseja remover o cliente?");
        System.out.println(cliente);
        System.out.print("Confirmar? (s/n): ");

        if (scanner.nextLine().equalsIgnoreCase("s")) {
            boolean removido = clienteController.remover(cliente.getId());
            if (removido) {
                System.out.println("Cliente removido com sucesso!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private void buscaAvancada() {
        System.out.println("\n===== BUSCA AVANÇADA =====");
        System.out.println("Deixe em branco para ignorar o filtro.");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Idade mínima: ");
        String idadeMinStr = scanner.nextLine();
        int idadeMin = idadeMinStr.isEmpty() ? 0 : Integer.parseInt(idadeMinStr);

        System.out.print("Idade máxima: ");
        String idadeMaxStr = scanner.nextLine();
        int idadeMax = idadeMaxStr.isEmpty() ? 150 : Integer.parseInt(idadeMaxStr);

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        System.out.print("Filtrar por alergia? (s/n): ");
        boolean filtrarAlergia = scanner.nextLine().equalsIgnoreCase("s");

        System.out.print("Filtrar por medicamento controlado? (s/n): ");
        boolean filtrarControlado = scanner.nextLine().equalsIgnoreCase("s");

        List<Cliente> resultados = buscaAvancada.buscaCombinada(
                nome, cpf, idadeMin, idadeMax, cidade, estado,
                filtrarAlergia, filtrarControlado
        );

        if (resultados.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        System.out.println("\n===== RESULTADOS (" + resultados.size() + ") =====");
        for (Cliente cliente : resultados) {
            System.out.println(cliente);
        }
    }

    private void simularRestricoes() {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = clienteController.buscarPorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println(cliente);

        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado para simular.");
            return;
        }

        System.out.println("\n===== MEDICAMENTOS DISPONÍVEIS =====");
        for (Medicamento m : medicamentos) {
            System.out.printf("ID: %d | Nome: %s | Controlado: %s%n",
                    m.getId(), m.getNome(), m.isControlado() ? "Sim" : "Não");
        }

        System.out.print("ID do medicamento para simular: ");
        int idMed = lerInteiro();

        Medicamento medicamento = null;
        for (Medicamento m : medicamentos) {
            if (m.getId() == idMed) {
                medicamento = m;
                break;
            }
        }

        if (medicamento == null) {
            System.out.println("Medicamento não encontrado.");
            return;
        }

        System.out.print("Cliente apresentou receita? (s/n): ");
        boolean receita = scanner.nextLine().equalsIgnoreCase("s");

        controleRestricoes.setCliente(cliente);
        boolean liberado = controleRestricoes.validarRestricoes(medicamento, receita);

        System.out.println("\nResultado: " + (liberado ? "✅ Venda LIBERADA" : "❌ Venda BLOQUEADA"));
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