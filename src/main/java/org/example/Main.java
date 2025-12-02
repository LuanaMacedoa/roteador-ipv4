package org.example;

public class Main {
    public static void main(String[] args) {

        // Cria a tabela de rotas
        TabelaRotas tabela = new TabelaRotas();

        // Cria interfaces físicas (dessa vez com a classe interfaceFIsica e nao apenas a string)
        InterfaceFisica eth0 = new InterfaceFisica("eth0", "192.168.1.2");

        // Cria rotas usando InterfaceFisica
        Rota r1 = new Rota("0.0.0.0", "192.168.1.1", "0.0.0.0", eth0);
        Rota r2 = new Rota("192.168.1.0", "0.0.0.0", "255.255.255.0", eth0);
        Rota r3 = new Rota("192.168.1.100", "192.168.1.1", "255.255.255.255", eth0);
        Rota r4 = new Rota("10.0.0.0", "192.168.1.254", "255.0.0.0", eth0);

        // Adiciona rotas na tabela
        tabela.adicionarRota(r1);
        tabela.adicionarRota(r2);

        // Testa duplicação
        tabela.adicionarRota(r2); // deve exibir mensagem de rota duplicada

        tabela.adicionarRota(r3);
        tabela.adicionarRota(r4);

        // Exibe tabela completa
        tabela.exibirTabela();

        // Remove rota pelo índice
        tabela.apagarRota(2); // remove a segunda rota
        tabela.apagarRota(7); // índice inválido

        // Exibe tabela novamente
        tabela.exibirTabela();

        // Apaga toda a tabela
        tabela.apagarTabela();
        tabela.exibirTabela();
    }
}
