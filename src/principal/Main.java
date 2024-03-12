package principal;

import sistema.Gravadora;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gravadora CD Records Worldwide INC.");
        int opcao;
        do{
            menu();
            opcao = scanner.nextInt();
        }while(opcao !=6);
    }
    private static final Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println("INSERIR CRIADOR.....1");
        System.out.println("INSERIR DISCO.......2");
        System.out.println("INSERIR INSTRUMENTO.3");
        System.out.println("INSERIR MUSICA......4");
        System.out.println("INSERIR PRODUTOR....5");
        System.out.println("SAIR................6");
    }
    private static Gravadora gravadora = new Gravadora();
}