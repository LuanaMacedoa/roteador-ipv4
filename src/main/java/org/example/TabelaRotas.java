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
    private List<String> rotas = new ArrayList();





    // INFO: metodo para adicionar uma rota na tabela de rotas
    public void adicionarRota(String rota) {
        rotas.add(rota);
    }
}
