package org.example;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor

public class Rota {
    private String ipDestino;
    private String gateway;
    private String mascara;
    private InterfaceFisica interfaceFisica;

    @Override
    public String toString() {
        return "Rota de Destino: " + ipDestino
        + " | Gateway: " + gateway
        + " | Máscara: " + mascara
        + " | Interface: " + interfaceFisica.getNome();
    }
}
