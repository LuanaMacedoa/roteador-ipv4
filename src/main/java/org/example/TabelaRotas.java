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
        System.out.println("\nRota: " + novaRota.getRotaDestino() + " adicionada com sucesso!");
    }

    // INFO: metodo q lista todas as rotas ou q a tabela esta vazia
    public void exibirTabela() {
        if (rotas.isEmpty()) {
            System.out.println("Nenhuma rota cadastrada...");
        } else {
            System.out.printf("%-18s | %-18s | %-18s | %-15s%n", "IP", "Gateway", "Mascara", "Interface");
            for (Rota rota : rotas) {
                System.out.printf("%-18s | %-18s | %-18s | %-15s%n", rota.getRotaDestino(), rota.getGateway(), rota.getMascara(), rota.getInterfaceFisica());
            }
        }   
    }

    // TODO: metodo q reseta a tabela - literalmente um delete em td

}
