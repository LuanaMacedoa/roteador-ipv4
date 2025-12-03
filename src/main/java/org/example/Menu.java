package org.example;

import java.util.Scanner;

public class Menu {

    private Roteador roteador;
    private Scanner keyboard;

    public Menu(Roteador roteador) {
        this.roteador = roteador;
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
                    cadastrarInterfaceFisica();
                    break;

                case 2:
                    cadastrarRota();
                    break;

                case 3:
                    exibirTabela();
                    break;

                case 4:
                    removerRota();
                    break;

                case 5:
                    alterarRota();
                    break;

                case 6:
                    buscarRotaPorIP();
                    break;

                case 7:
                    System.out.println("\nEncerrando...");
                    break;
                case 8:
                    alternarExibicao();
                    break;
                case 9:
                    resetarTabela();
                    break;

                default:
                    System.out.println("\nOpção inválida!");
                    pausar();
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n========================================");
        System.out.println("          ROTEADOR IPv4 - MENU");
        System.out.println("========================================");
        System.out.println("1 - Cadastrar Interface Física");
        System.out.println("2 - Cadastrar Nova Rota");
        System.out.println("3 - Exibir Tabela de Rotas");
        System.out.println("4 - Remover Rota");
        System.out.println("5 - Alterar Rota");
        System.out.println("6 - Buscar Rota por Destino");
        System.out.println("7 - Sair");
        System.out.println("8 - Alternar exibição Máscara ↔ CIDR");
        System.out.println("9- Resetar Tabela de Rotas");
        System.out.println("========================================");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarInterfaceFisica() {
        System.out.println("\n--- Cadastro de Interface Física ---");
        System.out.print("Nome da interface: ");
        String nome = keyboard.nextLine();
        System.out.print("Endereço IP: ");
        String ip = keyboard.nextLine();

        boolean ok = roteador.cadastrarInterface(nome, ip);
        if (ok)
            System.out.println("Interface cadastrada com sucesso!");
        else
            System.out.println("Erro ao cadastrar interface (duplicada ou IP inválido)");

        pausar();
    }

    private void cadastrarRota() {
        System.out.println("\n--- Cadastro de Rota ---");
        if (roteador.getInterfaces().isEmpty()) {
            System.out.println("Nenhuma interface cadastrada! Cadastre uma primeiro.");
            pausar();
            return;
        }
        System.out.print("Destino: ");
        String dest = keyboard.nextLine();

        System.out.print("Máscara: ");
        String mask = keyboard.nextLine();

        System.out.print("Gateway: ");
        String gateway = keyboard.nextLine();

        System.out.print("Nome da Interface usada: ");
        String nomeIface = keyboard.nextLine();

        InterfaceFisica iface = roteador.getInterfaces()
                .stream()
                .filter(i -> i.getNome().equalsIgnoreCase(nomeIface))
                .findFirst()
                .orElse(null);

        if (iface == null) {
            System.out.println("Interface não encontrada!");
            pausar();
            return;
        }

        boolean ok = roteador.cadastrarRota(dest, mask, gateway, iface);

        if (ok)
            System.out.println("Rota cadastrada com sucesso!");
        else
            System.out.println("Erro ao cadastrar rota.");

        pausar();
    }

    private void exibirTabela() {
        System.out.println("\n--- Tabela de Rotas ---");
        roteador.listarRotas();
        pausar();
    }

    private void removerRota() {
        System.out.println("\n--- Remover Rota ---");

        System.out.print("Destino da rota: ");
        String dest = keyboard.nextLine();
        System.out.print("Máscara: ");
        String mask = keyboard.nextLine();
        System.out.print("Tem certeza que deseja remover esta rota? [S/N]: ");
        String resposta = keyboard.nextLine();

        if (!resposta.equalsIgnoreCase("S")) {
            System.out.println("Operação cancelada.");
            pausar();
            return;
        }
        boolean ok = roteador.removerRota(dest, mask);
        if (ok)
            System.out.println("Rota removida!");
        else
            System.out.println("Rota não encontrada.");

        pausar();

    }

    private void alterarRota() {
        System.out.println("\n--- Alterar Rota ---");

        System.out.print("Destino da rota existente: ");
        String dest = keyboard.nextLine();

        System.out.print("Máscara existente: ");
        String mask = keyboard.nextLine();

        System.out.print("Novo gateway: ");
        String novoGate = keyboard.nextLine();

        System.out.print("Nome da nova interface: ");
        String nomeIface = keyboard.nextLine();

        InterfaceFisica iface = roteador.getInterfaces()
                .stream()
                .filter(i -> i.getNome().equalsIgnoreCase(nomeIface))
                .findFirst()
                .orElse(null);

        if (iface == null) {
            System.out.println("Interface não encontrada!");
            pausar();
            return;
        }

        boolean ok = roteador.alterarRota(dest, mask, novoGate, iface);

        if (ok)
            System.out.println("Rota alterada com sucesso!");
        else
            System.out.println("Rota não encontrada!");

        pausar();
    }

    private void buscarRotaPorIP() {
        System.out.println("\n--- Buscar Rota por IP (roteamento real) ---");

        System.out.print("Digite o IP que deseja rotear: ");
        String ip = keyboard.nextLine();

        Rota rota = roteador.rotear(ip); // usa UtilsIP por dentro

        if (rota == null) {
            System.out.println("Nenhuma rota encontrada para esse IP.");
        } else {
            String maskExibida = rota.getMascara();
            if (roteador.isExibirCIDR()) {
                maskExibida = "/" + UtilsIP.mascaraParaCIDR(rota.getMascara());
            }

            System.out.println("\nRota encontrada:");
            System.out.println("(" +
                    rota.getIpDestino() + ", " +
                    rota.getGateway() + ", " +
                    maskExibida + ", " +
                    rota.getInterfaceFisica().getNome() +
                    ")");
        }

        pausar();
    }

    private void alternarExibicao() {
        roteador.setExibirCIDR(!roteador.isExibirCIDR());

        if (roteador.isExibirCIDR()) {
            System.out.println("Agora as rotas serão exibidas em notação CIDR (/24, /30...).");
        } else {
            System.out.println("Agora as rotas serão exibidas com máscara completa (255.255.255.0).");
        }

        pausar();
    }
    private void resetarTabela() {
        System.out.println("\n--- Resetar Tabela de Rotas ---");
        System.out.print("Tem certeza que deseja apagar todas as rotas? [S/N]: ");
        String resposta = keyboard.nextLine().trim().toUpperCase();

        if (resposta.equals("S")) {
            roteador.getTabela().getRotas().clear();
            System.out.println("Tabela de rotas resetada com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }

        pausar();
    }

    private void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        keyboard.nextLine();
    }
}
