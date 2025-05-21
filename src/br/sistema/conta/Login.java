package br.sistema.conta;

import java.util.Scanner;

public class Login {
    private static final String ADMIN_USUARIO = "admin";
    private static final String ADMIN_SENHA = "1234";

    public static boolean realizarLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Usuário: ");
        String usuario = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        return usuario.equals(ADMIN_USUARIO) && senha.equals(ADMIN_SENHA);
    }
}