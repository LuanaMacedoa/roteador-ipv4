package org.example;

public class Main {
    public static void main(String[] args) {
        Roteador r = new Roteador();
        Menu menu = new Menu(r);
        menu.executar();
    }
}
