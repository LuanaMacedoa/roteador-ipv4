package org.example;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Rota {
    private String rotaDestino;
    private String mascara;
    private String gateway;
    private String interfaceFisica;

    @Override
    public String toString() {
        // TODO: atualizar p/ ficar igual ao do prof
        return "Rota de Destino: " + rotaDestino
        + "\nMáscara: " + mascara
        + "\nGateway: " + gateway
        +"\nInterface: " + interfaceFisica;
    }
}
