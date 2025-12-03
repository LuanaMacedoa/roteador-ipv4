package org.example;

import java.util.Scanner;

public class Menu {
    private TabelaRotas tabela;
    private Scanner keyboard;

    public Menu() {
        this.tabela = new TabelaRotas();
        this.keyboard = new Scanner(System.in);
    }

    public void executar() {
        int opcao = 0;

        while (opcao != 7) {
            exibirMenu();
            opcao = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Adicionar Nova Rota ---");
                    
                    System.out.print("IP de Destino: ");
                    String destino = keyboard.nextLine();
                    
                    System.out.print("Gateway: ");
                    String gateway = keyboard.nextLine();
                    
                    System.out.print("Máscara: ");
                    String mascara = keyboard.nextLine();
                    
                    System.out.print("Nome da Interface: ");
                    String nomeInterface = keyboard.nextLine();
                    
                    System.out.print("IP da Interface: ");
                    String ipInterface = keyboard.nextLine();

                    InterfaceFisica iface = new InterfaceFisica(nomeInterface, ipInterface);
                    Rota novaRota = new Rota(destino, gateway, mascara, iface);
                    
                    tabela.adicionarRota(novaRota);
                    pausar();
                    break;
                    
                case 2:
                    tabela.exibirTabela();
                    pausar();
                    break;
                    
                case 3:
                    tabela.exibirTabela();
                    
                    if (!tabela.getRotas().isEmpty()) {
                        System.out.print("\nDigite o número da rota a remover: ");
                        int numero = keyboard.nextInt();
                        keyboard.nextLine();
                        tabela.apagarRota(numero);
                    }
                    
                    pausar();
                    break;
                    
                case 4:
                    alterarRota();
                    break;
                    
                case 5:
                    buscarRotaPorIP();
                    break;
                    
                case 6:
                    tabela.apagarTabela();
                    pausar();
                    break;
                    
                case 7:
                    System.out.println("\nEncerrando...");
                    break;
                    
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
                    pausar();
                    break;
            }
        }

        keyboard.close();
    }

    private void exibirMenu() {
        System.out.println("\n========================================");
        System.out.println("          ROTEADOR IPv4 - MENU");
        System.out.println("========================================");
        System.out.println("1 - Adicionar Rota");
        System.out.println("2 - Exibir Tabela de Rotas");
        System.out.println("3 - Remover Rota");
        System.out.println("4 - Alterar Rota");
        System.out.println("5 - Buscar Rota por IP");
        System.out.println("6 - Apagar Toda a Tabela");
        System.out.println("7 - Sair");
        System.out.println("========================================");
        System.out.print("Escolha uma opção: ");
    }

    private void alterarRota() {
        tabela.exibirTabela();
        
        if (tabela.getRotas().isEmpty()) {
            pausar();
            return;
        }
        
        System.out.print("\nDigite o número da rota a alterar: ");
        int numero = keyboard.nextInt();
        keyboard.nextLine();
        
        if (numero < 1 || numero > tabela.getRotas().size()) {
            System.out.println("\nÍndice inválido!");
            pausar();
            return;
        }
        
        Rota rotaAntiga = tabela.getRotas().get(numero - 1);
        
        System.out.println("\n--- Alterar Rota ---");
        System.out.println("Deixe em branco para manter o valor atual");
        
        System.out.print("Novo IP de Destino [" + rotaAntiga.getRotaDestino() + "]: ");
        String destino = keyboard.nextLine();
        if (destino.isEmpty()) destino = rotaAntiga.getRotaDestino();
        
        System.out.print("Novo Gateway [" + rotaAntiga.getGateway() + "]: ");
        String gateway = keyboard.nextLine();
        if (gateway.isEmpty()) gateway = rotaAntiga.getGateway();
        
        System.out.print("Nova Máscara [" + rotaAntiga.getMascara() + "]: ");
        String mascara = keyboard.nextLine();
        if (mascara.isEmpty()) mascara = rotaAntiga.getMascara();
        
        System.out.print("Novo Nome da Interface [" + rotaAntiga.getInterfaceFisica().getNome() + "]: ");
        String nomeInterface = keyboard.nextLine();
        if (nomeInterface.isEmpty()) nomeInterface = rotaAntiga.getInterfaceFisica().getNome();
        
        System.out.print("Novo IP da Interface [" + rotaAntiga.getInterfaceFisica().getEnderecoIP() + "]: ");
        String ipInterface = keyboard.nextLine();
        if (ipInterface.isEmpty()) ipInterface = rotaAntiga.getInterfaceFisica().getEnderecoIP();
        
        InterfaceFisica iface = new InterfaceFisica(nomeInterface, ipInterface);
        Rota novaRota = new Rota(destino, gateway, mascara, iface);
        
        tabela.getRotas().set(numero - 1, novaRota);
        System.out.println("\nRota alterada com sucesso!");
        pausar();
    }
    
    private void buscarRotaPorIP() {
        System.out.print("\nDigite o IP de destino para buscar: ");
        String ip = keyboard.nextLine();
        
        boolean encontrou = false;
        System.out.println("\n--- Rotas Encontradas ---");
        
        for (int i = 0; i < tabela.getRotas().size(); i++) {
            Rota r = tabela.getRotas().get(i);
            if (r.getRotaDestino().equals(ip)) {
                System.out.println("\nRota #" + (i + 1) + ":");
                System.out.println(r);
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("\nNenhuma rota encontrada para o IP: " + ip);
        }
        
        pausar();
    }

    private void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        keyboard.nextLine();
    }
}
