package org.example;

public class Main {
    public static void main(String[] args) {

        TabelaRotas tabela = new TabelaRotas();

        // TODO: atualizar p/ ficar igual ao do prof
        Rota r1 = new Rota("192.168.1.100", "10.0.3.2", "255.255.255.255", "eth1");
        Rota r2 = new Rota("192.168.1.100", "255.0.0.0", "255.255.255.255", "eth1");
        Rota r3 = new Rota("192.168.1.100", "255.255.0.0", "255.255.255.255", "eth1");
        Rota r4 = new Rota("10.0.0.0", "255.0.0.0", "10.0.0.1", "lo0");
        Rota r5 = new Rota("192.168.1.100", "255.255.255.255", "192.168.1.1", "eth0");
        System.out.println(r1);

        tabela.adicionarRota(r1);
        tabela.adicionarRota(r2);
        tabela.adicionarRota(r3);
        tabela.adicionarRota(r4);
        tabela.adicionarRota(r5);

        tabela.exibirTabela();
    }
}