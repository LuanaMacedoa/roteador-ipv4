package org.example;
import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.List;


@Data
@AllArgsConstructor

public class Roteador {
    List<InterfaceFisica> interfaces;
    TabelaRotas tabela;
    boolean exibirCIDR;

    public boolean cadastrarInterface(String nome, String ip){
        if (nome == null || nome.isEmpty()){
            return false;
        }
        for (InterfaceFisica i : interfaces){
            if (i.getNome().equalsIgnoreCase(nome)){
                return false;
            }
        } // "\\b\\d{1,3}(\\.\\d{1,3}){3}\\b"
        if(ip == null || !ip.matches("^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.|$)){4}$")){
            return false;
        }
        InterfaceFisica novaInterface = new InterfaceFisica(nome,ip);
        interfaces.add(novaInterface);
        return true;
    }

    public boolean cadastrarRota(String destino, String mask, String gateway, InterfaceFisica iface ) {
        if (destino == null || mask == null || gateway == null || iface == null) {
            return false;
        } // impede duplicação de rotas com o mesmo destino
        Rota rotaNova = new Rota(destino, gateway, mask, iface);
        tabela.adicionarRota(rotaNova);
        return true;

    }
    public boolean alterarRota(String destino, String mask, String novoGateway, InterfaceFisica novaIface) {

        if (destino == null || mask == null || novoGateway == null || novaIface == null) {
            return false;
        }
        // procura rota existente
        for (Rota r : tabela.getRotas()) {
            if (r.getIpDestino().equals(destino) && r.getMascara().equals(mask)) {
                r.setGateway(novoGateway);
                r.setInterfaceFisica(novaIface);
                return true;
            }
        }
        return false; // rota não encontrada
    }
    public boolean removerRota(String destino, String mask) {
        if (destino == null || mask == null) {
            return false;
        }

        Iterator<Rota> it = tabela.getRotas().iterator();
        while (it.hasNext()) {
            Rota r = it.next();
            if (r.getIpDestino().equals(destino) && r.getMascara().equals(mask)) {
                it.remove(); // remove de forma segura
                return true;
            }
        }


        return false; // rota não encontrada
    }
    public void listarRotas() {
        if (tabela.getRotas().isEmpty()) {
            System.out.println("Nenhuma rota cadastrada.");
            return;
        }
        for (Rota r : tabela.getRotas()) {
            String mascaraExibida = r.getMascara();
            if (exibirCIDR) {
                // converte máscara para /CIDR ADICIONAR CLASS UTILSIP
                mascaraExibida = "/" + UtilsIP.mascaraParaCIDR(r.getMascara());
            }
            System.out.println(
                    "Destino: " + r.getIpDestino() +
                            " | Máscara: " + mascaraExibida +
                            " | Gateway: " + r.getGateway() +
                            " | Interface: " + r.getInterfaceFisica().getNome()
            );
        }
    }
    public Rota rotear(String ip) {
        if (ip == null) return null;
        for (Rota r : tabela.getRotas()) {
            if (UtilsIP.ipPertenceARede(ip, r.getIpDestino(), r.getMascara())) {
                return r;
            }
        }
        return null; // nenhuma rota encontrada
    }
}
