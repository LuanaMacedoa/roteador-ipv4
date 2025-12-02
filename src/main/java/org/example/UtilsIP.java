package org.example;

public class UtilsIP {
    // para converter a mascara em /24 ou / 16...
    public static int mascaraParaCIDR(String mask){
        String[] partes = mask.split("\\.");
        int cidr = 0;
        for (String p : partes){
            int val = Integer.parseInt(p);
            cidr += Integer.bitCount(val);
        }
        return cidr;
    }

    public static boolean ipPertenceARede(String ip, String destino, String mask){
        int ipInt = ipToInt(ip);
        int destInt = ipToInt(destino);
        int maskInt = mascaraToInt(mask);
        return (ipInt & maskInt) == (destInt & maskInt);
    }

    // Converte IP String "192.168.0.1" para int
    public static int ipToInt(String ip) {
        String[] partes = ip.split("\\.");
        int resultado = 0;
        for (String p : partes) {
            resultado = (resultado << 8) | Integer.parseInt(p);
        }
        return resultado;
    }

    // Converte máscara String "255.255.255.0" para int
    public static int mascaraToInt(String mask) {
        String[] partes = mask.split("\\.");
        int resultado = 0;
        for (String p : partes) {
            resultado = (resultado << 8) | Integer.parseInt(p);
        }
        return resultado;
    }
}
