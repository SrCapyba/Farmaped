package com.br.interfaces;

import com.br.model.Cliente;
import com.br.model.Medicamento;

public interface VendaService {

    boolean adicionarItem(
            Medicamento medicamento,
            int quantidade,
            boolean receita);

    boolean efetuarVenda(
            Cliente cliente,
            double desconto,
            String formaPagamento);

    void cancelarVenda();

    void imprimirNota();
}