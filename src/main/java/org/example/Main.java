package org.example;

public class Main {
    public static void main(String[] args) {

        TabelaRotas tabela = new TabelaRotas();

        Rota r1 = new Rota("0.0.0.0", "192.168.1.1", "0.0.0.0", "eth0");              // rota padrão via gateway
        Rota r2 = new Rota("192.168.1.0", "0.0.0.0", "255.255.255.0", "eth0");           // rede local /24 sem gateway
        Rota r3 = new Rota("192.168.1.100", "192.168.1.1", "255.255.255.255", "eth0"); // host específico via gateway
        Rota r4 = new Rota("10.0.0.0", "192.168.1.254", "255.0.0.0", "eth0");            // rede remota via gateway

        tabela.adicionarRota(r1);
        
        tabela.adicionarRota(r2);

        tabela.adicionarRota(r2);

        tabela.adicionarRota(r3);

        tabela.adicionarRota(r4);

        tabela.exibirTabela();

        tabela.apagarRota(2);
        tabela.apagarRota(7);
        tabela.exibirTabela();

        tabela.apagarTabela();
        tabela.exibirTabela();
    }
}