package com.br.repository;

import com.br.model.Medicamento;

import java.util.List;

public class MedicamentoRepository {

    private final List<Medicamento> medicamentos;

    public MedicamentoRepository(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void salvar(Medicamento medicamento) {
        medicamentos.add(medicamento);
    }

    public List<Medicamento> listarTodos() {
        return medicamentos;
    }

    public Medicamento buscarPorId(int id) {
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getId() == id) {
                return medicamento;
            }
        }

        return null;
    }

    public boolean removerPorId(int id) {
        Medicamento medicamento = buscarPorId(id);

        if (medicamento == null) {
            return false;
        }

        medicamentos.remove(medicamento);
        return true;
    }

    public int gerarProximoId() {
        int maiorId = 0;

        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getId() > maiorId) {
                maiorId = medicamento.getId();
            }
        }

        return maiorId + 1;
    }
}