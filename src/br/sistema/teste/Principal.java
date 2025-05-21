package br.sistema.teste;

import br.sistema.menu.Menu;
import br.sistema.conta.Estoque;
import br.sistema.conta.Login;

public class Principal {
    public static void main(String[] args) {
        if (Login.realizarLogin()) {
            Estoque estoque = new Estoque();
            Menu menu = new Menu(estoque);
            menu.exibirMenu();
        } else {
            System.out.println("Acesso negado.");
        }
    }
}
