package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.estilo.Personalizar;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class TabelaRotas {
    private List<Rota> rotas = new ArrayList<>();

    // INFO: metodo para adicionar uma rota na tabela de rotas
    public void adicionarRota(Rota novaRota) {
        if (rotaDuplicada(novaRota)) {
            System.out.println(Personalizar.VERMELHO + "\nRota duplicada! Já existe uma rota para " + 
            novaRota.getRotaDestino() + "/" + novaRota.getMascara() + Personalizar.RESET);
        } else {
            this.rotas.add(novaRota);
        System.out.println(Personalizar.VERDE + "\nNova rota adicionada! " + Personalizar.RESET + "-> " + Personalizar.SUBLINHADO + novaRota + Personalizar.RESET);
        } 
    }

    // INFO: metodo verifica se a rota e duplicada antes de inserir
    private boolean rotaDuplicada(Rota novaRota) {
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
            System.out.println(Personalizar.VERMELHO + "Nenhuma rota cadastrada..." + Personalizar.RESET);
        } else {
            System.out.println(Personalizar.FUNDO_PRETO + "\n                                  Tabela de Rotas                                     " + Personalizar.RESET);
            System.out.println(Personalizar.FUNDO_PRETO + "--------------------------------------------------------------------------------------" + Personalizar.RESET);
            System.out.printf(Personalizar.FUNDO_PRETO + "%-4s | %-18s | %-18s | %-18s | %-15s%n", "#", "IP", "Máscara", "Gateway", "Interface" + Personalizar.RESET);
            System.out.println(Personalizar.FUNDO_PRETO + "--------------------------------------------------------------------------------------" + Personalizar.RESET);
            for (int i = 0; i < rotas.size(); i++) {
                Rota r = rotas.get(i);
                System.out.printf(Personalizar.FUNDO_PRETO + "%-4d | %-18s | %-18s | %-18s | %-15s%n" + Personalizar.RESET, 
                    i + 1, 
                    r.getRotaDestino(), 
                    r.getMascara(), 
                    r.getGateway(), 
                    r.getInterfaceFisica());
            }
            System.out.println(Personalizar.FUNDO_PRETO + "--------------------------------------------------------------------------------------" + Personalizar.RESET);
            System.out.println(Personalizar.AZUL + "Total de rotas: " + Personalizar.RESET + Personalizar.SUBLINHADO + rotas.size() + Personalizar.RESET);
        }
    }
    // INFO: metodo para limpar a tabela de rotas
    public void apagarTabela() {
        if (rotas.isEmpty()) {
            System.out.println(Personalizar.VERMELHO + "Tabela já está vazia." + Personalizar.RESET);
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
            System.out.println(Personalizar.VERMELHO + "\nIndice inválido! Escolha entre 1 e " + rotas.size() + "\n" + Personalizar.RESET);
            return;
        } else {
            Rota removida = rotas.remove(numero - 1);
            System.out.println(Personalizar.VERDE + "\nRota removida: " + Personalizar.RESET + Personalizar.SUBLINHADO + removida.getRotaDestino() + " via " + removida.getInterfaceFisica() + "\n" + Personalizar.RESET);
        }
    }
}
