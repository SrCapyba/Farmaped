package com.br.repository;

import java.util.ArrayList;
import java.util.List;

import com.br.model.Venda;

public class VendaRepository {

    private final List<Venda> vendas = new ArrayList<>();

    public void salvar(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> listarTodas() {
        return vendas;
    }

    public Venda buscarPorId(int id) {

        for (Venda venda : vendas) {
            if (venda.getIdVenda() == id) {
                return venda;
            }
        }

        return null;
    }
}