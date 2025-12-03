package org.example;

public class UtilsIP {

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

    public static int ipToInt(String ip) {
        String[] partes = ip.split("\\.");
        int resultado = 0;
        for (String p : partes) {
            resultado = (resultado << 8) | Integer.parseInt(p);
        }
        return resultado;
    }

    public static int mascaraToInt(String mask) {
        String[] partes = mask.split("\\.");
        int resultado = 0;
        for (String p : partes) {
            resultado = (resultado << 8) | Integer.parseInt(p);
        }
        return resultado;
    }
}
