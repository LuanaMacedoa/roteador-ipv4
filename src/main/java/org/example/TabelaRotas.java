package org.example;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class TabelaRotas {
    private List<Rota> rotas = new ArrayList<>();

    // INFO: metodo para adicionar uma rota na tabela de rotas
    public void adicionarRota(Rota novaRota) {
        if (rotaExiste(novaRota)) {
            System.out.println("\nRota duplicada! Já existe uma rota para " + 
            novaRota.getRotaDestino() + "/" + novaRota.getMascara());
        }
        this.rotas.add(novaRota);
        System.out.println("\nNova rota adicionada! -> " + novaRota);
    }

    // INFO: metodo verifica se a rota e duplicada antes de inserir
    private boolean rotaExiste(Rota novaRota) {
        for (Rota r : rotas) {
            if (novaRota.getRotaDestino().equals(r.getRotaDestino()) && novaRota.getMascara().equals(r.getMascara())) {
                return true;
            }
        }
        return false;
    }

    // INFO: metodo de exibir a tabela toda
    public void exibirTabela() {
        if (rotas.isEmpty()) {
            System.out.println("Nenhuma rota cadastrada...");
        } else {
            System.out.println("\n                              Tabela de Rotas");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.printf("%-4s | %-18s | %-18s | %-18s | %-15s%n", "#", "IP", "Máscara", "Gateway", "Interface");
            System.out.println("--------------------------------------------------------------------------------------");
            for (int i = 0; i < rotas.size(); i++) {
                Rota r = rotas.get(i);
                System.out.printf("%-4d | %-18s | %-18s | %-18s | %-15s%n", 
                    i + 1, 
                    r.getRotaDestino(), 
                    r.getMascara(), 
                    r.getGateway(), 
                    r.getInterfaceFisica());
            }
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Total de rotas: " + rotas.size());
        }
    }
    // INFO: metodo para limpar a tabela de rotas
    public void apagarTabela() {
        if (rotas.isEmpty()) {
            System.out.println("Tabela já está vazia.");
        } else {
            rotas.clear();
            System.out.println("\nTabela de rotas apagada!");
        }
    }
    
    // INFO: metodo que apaga apenas 1 rota
    public void apagarRota(int numero) {
        if (rotas.isEmpty()) {
            System.out.println("Tabela vazia, nenhuma rota para remover.");
            return;
        } else if (numero < 1 || numero > rotas.size()) {
            System.out.println("\nIndice inválido! Escolha entre 1 e " + rotas.size() + "\n");
            return;
        } else {
            Rota removida = rotas.remove(numero - 1);
            System.out.println("\nRota removida: " + removida.getRotaDestino() + " via " + removida.getInterfaceFisica() + "\n");
        }
    }
}
