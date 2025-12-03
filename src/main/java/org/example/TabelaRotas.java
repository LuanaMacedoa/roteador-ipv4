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

    public void adicionarRota(Rota novaRota) {
        if (rotaDuplicada(novaRota)) {
            System.out.println(Personalizar.VERMELHO + "\nRota duplicada! Já existe uma rota para " + 
            novaRota.getIpDestino() + "/" + novaRota.getMascara() + Personalizar.RESET);
        } else {
            this.rotas.add(novaRota);
        System.out.println(Personalizar.VERDE + "\nNova rota adicionada! " + Personalizar.RESET + "-> " + Personalizar.SUBLINHADO + novaRota + Personalizar.RESET);
        } 
    }

    private boolean rotaDuplicada(Rota novaRota) {
        for (Rota r : rotas) {
            if (novaRota.getIpDestino().equals(r.getIpDestino()) && novaRota.getMascara().equals(r.getMascara())) {
                return true;
            }
        }
        return false;
    }

}
