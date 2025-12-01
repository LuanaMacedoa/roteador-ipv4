package org.example;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor

public class Rota {
    private String rotaDestino;
    private String gateway;
    private String mascara;
    private InterfaceFisica interfaceFisica;


    @Override
    public String toString() {
        return "Rota de Destino: " + rotaDestino
        + " | Gateway: " + gateway
        + " | Máscara: " + mascara
        + " | Interface: " + interfaceFisica;
    }
}
