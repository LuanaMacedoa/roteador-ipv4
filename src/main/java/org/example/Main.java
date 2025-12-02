package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TabelaRotas tabela = new TabelaRotas();
        Scanner keyboard = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 5) {
            exibirMenu();
            opcao = keyboard.nextInt();
            keyboard.nextLine();

            if (opcao == 1) {
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
                pausar(keyboard);
                
            } else if (opcao == 2) {
                tabela.exibirTabela();
                pausar(keyboard);
                
            } else if (opcao == 3) {
                tabela.exibirTabela();
                
                if (!tabela.getRotas().isEmpty()) {
                    System.out.print("\nDigite o número da rota a remover: ");
                    int numero = keyboard.nextInt();
                    keyboard.nextLine();
                    tabela.apagarRota(numero);
                }
                
                pausar(keyboard);
                
            } else if (opcao == 4) {
                tabela.apagarTabela();
                pausar(keyboard);
                
            } else if (opcao == 5) {
                System.out.println("\nEncerrando...");
                
            } else {
                System.out.println("\nOpçao inválida! Tente novamente.");
                pausar(keyboard);
            }
        }

        keyboard.close();
    }

    private static void exibirMenu() {
        limparTela();
        System.out.println("========================================");
        System.out.println("          ROTEADOR IPv4 - MENU");
        System.out.println("========================================");
        System.out.println("1 - Adicionar Rota");
        System.out.println("2 - Exibir Tabela de Rotas");
        System.out.println("3 - Remover Rota");
        System.out.println("4 - Apagar Toda a Tabela");
        System.out.println("5 - Sair");
        System.out.println("========================================");
        System.out.print("Escolha um numero: ");
    }

    private static void pausar(Scanner scanner) {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
