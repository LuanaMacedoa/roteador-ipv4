package org.example;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TabelaRotas {
    private List<Rota> rotas = new ArrayList<>();


    // INFO: metodo para adicionar uma rota na tabela de rotas
    public void adicionarRota(Rota novaRota) {
        this.rotas.add(novaRota);
        System.out.println("\nNova rota adicionada!");
    }

    // INFO: metodo de exibir a tabela toda
    public void exibirTabela() {
        if (rotas.isEmpty()) {
            System.out.println("Nenhuma rota cadastrada...");
        } else {
            System.out.printf("%-18s | %-18s | %-18s | %-15s%n", "IP", "Máscara", "Gateway", "Interface");
            for (Rota R : rotas) {
                System.out.printf("%-18s | %-18s | %-18s | %-15s%n", R.getRotaDestino(), R.getMascara(), R.getGateway(), R.getInterfaceFisica());
            }
        }
        

    // TODO: fazer o metodo p/ apagar td a tabela
    }
}