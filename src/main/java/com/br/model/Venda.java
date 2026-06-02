package com.br.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private static int contador = 1;

    private int idVenda;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private LocalDateTime data;
    private double valorTotal;
    private double desconto;
    private String formaPagamento;

    public Venda(
            Cliente cliente,
            List<ItemVenda> itens,
            double desconto,
            String formaPagamento) {

        this.idVenda = contador++;
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens);
        this.desconto = desconto;
        this.formaPagamento = formaPagamento;
        this.data = LocalDateTime.now();

        calcularValorTotal();
    }

    private void calcularValorTotal() {

        valorTotal = itens.stream()
                .mapToDouble(ItemVenda::calcularSubtotal)
                .sum();

        valorTotal -= desconto;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public LocalDateTime getData() {
        return data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
}