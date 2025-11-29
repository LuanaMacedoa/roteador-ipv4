package org.example;

public class Main {
    public static void main(String[] args) {

        TabelaRotas tabela = new TabelaRotas();

        Rota r1 = new Rota("0.0.0.0", "192.168.1.1", "0.0.0.0", "eth0");              
        Rota r2 = new Rota("192.168.1.0", "0.0.0.0", "255.255.255.0", "eth0");          
        Rota r3 = new Rota("192.168.1.100", "192.168.1.1", "255.255.255.255", "eth0"); 
        Rota r4 = new Rota("10.0.0.0", "192.168.1.254", "255.0.0.0", "eth0");           

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