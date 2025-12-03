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

        while (opcao != 5) {
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
                    tabela.apagarTabela();
                    pausar();
                    break;
                    
                case 5:
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
        System.out.println("4 - Apagar Toda a Tabela");
        System.out.println("5 - Sair");
        System.out.println("========================================");
        System.out.print("Escolha uma opção: ");
    }

    private void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        keyboard.nextLine();
    }
}
