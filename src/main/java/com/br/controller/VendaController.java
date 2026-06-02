package com.br.controller;

import java.util.ArrayList;
import java.util.List;

import com.br.interfaces.VendaService;
import com.br.model.Cliente;
import com.br.model.ItemVenda;
import com.br.model.Medicamento;
import com.br.model.Venda;
import com.br.repository.VendaRepository;

public class VendaController implements VendaService {

    private final List<ItemVenda> carrinho = new ArrayList<>();

    private final VendaRepository vendaRepository;

    public VendaController(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public boolean adicionarItem(
            Medicamento medicamento,
            int quantidade,
            boolean receita) {

        if (quantidade <= 0) {
            return false;
        }

        if (medicamento.getQuantidadeEstoque() < quantidade) {
            System.out.println("Estoque insuficiente.");
            return false;
        }

        if (medicamento.isControlado() && !receita) {
            System.out.println("Receita obrigatória.");
            return false;
        }

        carrinho.add(
                new ItemVenda(medicamento, quantidade)
        );

        return true;
    }

    @Override
    public boolean efetuarVenda(
            Cliente cliente,
            double desconto,
            String formaPagamento) {

        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return false;
        }

        for (ItemVenda item : carrinho) {

            Medicamento medicamento = item.getMedicamento();

            medicamento.setQuantidadeEstoque(
                    medicamento.getQuantidadeEstoque()
                            - item.getQuantidade());
        }

        Venda venda = new Venda(
                cliente,
                carrinho,
                desconto,
                formaPagamento
        );

        vendaRepository.salvar(venda);

        System.out.println(
                "Venda realizada com sucesso. ID: "
                        + venda.getIdVenda());

        carrinho.clear();

        return true;
    }

    @Override
    public void cancelarVenda() {

        carrinho.clear();

        System.out.println("Venda cancelada.");
    }

    @Override
    public void imprimirNota() {

        System.out.println("\n========= CARRINHO =========");

        double total = 0;

        for (ItemVenda item : carrinho) {

            double subtotal = item.calcularSubtotal();

            System.out.printf(
                    "%s | Qtd: %d | R$ %.2f%n",
                    item.getMedicamento().getNome(),
                    item.getQuantidade(),
                    subtotal);

            total += subtotal;
        }

        System.out.printf(
                "TOTAL: R$ %.2f%n",
                total);
    }
}