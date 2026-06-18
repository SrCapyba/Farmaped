package com.br.controller;

import com.br.model.Cliente;
import com.br.model.Medicamento;

public class ControleRestricoes {
    private Cliente cliente;
    private boolean restricaoAtiva;
    private String mensagemAlerta;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.restricaoAtiva = false;
        this.mensagemAlerta = "";
    }

    public boolean verificarAlergia(Medicamento medicamento) {
        if (cliente.isAlergia() && cliente.getNomeAlergia().stream()
                .anyMatch(a -> a.equalsIgnoreCase(medicamento.getNome()))) {
            this.restricaoAtiva = true;
            this.mensagemAlerta = "ALERTA CRÍTICO: O cliente possui alergia registrada ao medicamento: " + medicamento.getNome();
            return true;
        }
        return false;
    }

    public boolean verificarMedicamentoControlado(Medicamento medicamento, boolean receitaApresentada) {
        if (medicamento.isControlado()) {
            if (!receitaApresentada) {
                this.restricaoAtiva = true;
                this.mensagemAlerta = "BLOQUEIO: Medicamento controlado. Retenção de receita obrigatória para venda!";
                return true;
            }
            // Verifica se ele já faz uso contínuo cadastrado
            boolean cadastrado = cliente.getNomeRemedioControlado().stream()
                    .anyMatch(r -> r.equalsIgnoreCase(medicamento.getNome()));
            if (!cadastrado) {
                System.out.println("Aviso: Medicamento controlado apresentado com receita, mas não consta na lista de uso contínuo do cliente.");
            }
        }
        return false;
    }

    public boolean verificarIdadePermitida(Medicamento medicamento) {
        // Verifica se o medicamento tem idade mínima definida
        // Nota: seu Medicamento atual não tem idadeMinima, então isso fica como sugestão
        // Se quiser adicionar, precisa modificar a classe Medicamento
        return false;
    }

    public boolean validarRestricoes(Medicamento medicamento, boolean receitaApresentada) {
        this.restricaoAtiva = false;

        if (verificarAlergia(medicamento)) {
            emitirAlerta();
            bloquearMedicamento(medicamento);
            return false;
        }

        if (verificarMedicamentoControlado(medicamento, receitaApresentada)) {
            emitirAlerta();
            bloquearMedicamento(medicamento);
            return false;
        }

        liberarMedicamento(medicamento);
        return true;
    }

    private void emitirAlerta() {
        System.out.println("\n====================================================================================================");
        System.out.println(mensagemAlerta);
        System.out.println("====================================================================================================");
    }

    private void bloquearMedicamento(Medicamento m) {
        System.out.println("Venda do item '" + m.getNome() + "' IMPEDIDA para o cliente " + cliente.getNome() + ".");
    }

    private void liberarMedicamento(Medicamento m) {
        System.out.println("Medicamento '" + m.getNome() + "' LIBERADO com sucesso para " + cliente.getNome() + ".");
    }

    public boolean isRestricaoAtiva() {
        return restricaoAtiva;
    }

    public String getMensagemAlerta() {
        return mensagemAlerta;
    }
}