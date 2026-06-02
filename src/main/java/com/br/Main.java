package com.br;

import java.util.ArrayList;
import java.util.List;

import com.br.controller.MenuController;
import com.br.model.Cliente;
import com.br.model.Medicamento;

public class Main {

    public static void main(String[] args) {

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

        List<Cliente> clientes = new ArrayList<>();

        clientes.add(
                new Cliente(
                        "João Silva",
                        "12345678900"));

        clientes.add(
                new Cliente(
                        "Maria Souza",
                        "98765432100"));

        MenuController menu =
                new MenuController(
                        medicamentos,
                        clientes);

        menu.exibirMenu();
    }
}