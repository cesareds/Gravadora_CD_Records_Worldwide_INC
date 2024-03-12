package principal;

import sistema.Gravadora;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gravadora CD Records Worldwide INC.");
        int opcao;
        do{
            menu();
            opcao = scanner.nextInt();
            switch (opcao){
                case 1:
                    inserirCriador();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Encerrando o programa");
                    break;
                case 7:
                    mostrarCriadores();
                    break;
            }
        }while(opcao !=6);
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scannerS = new Scanner(System.in);

    private static final Gravadora gravadora = new Gravadora();

    public static void menu(){
        System.out.println("INSERIR CRIADOR.......1");
        System.out.println("INSERIR DISCO.........2");
        System.out.println("INSERIR INSTRUMENTO...3");
        System.out.println("INSERIR MUSICA........4");
        System.out.println("INSERIR PRODUTOR......5");
        System.out.println("SAIR..................6");
        System.out.println("MOSTRAR CRIADORES.....7");
    }
    public static void inserirCriador(){
        int tipo;
        String nome;
        String genero;
        String descricao;
        System.out.println("QUAL O TIPO DE CRIADOR? (BANDA: 0 | MUSICO: 1)");
        tipo = scanner.nextInt();
        System.out.print("NOME:\t");
        nome = scannerS.nextLine();
        System.out.print("\nGÊNERO:\t");
        genero = scannerS.nextLine();
        System.out.print("\nDESCRIÇÃO:\t");
        descricao = scannerS.nextLine();
        if(tipo==1){
            String cep;
            String rua;
            String cidade;
            String estado;
            String telefone;
            System.out.print("\nCEP:\t");
            cep = scannerS.nextLine();
            System.out.print("\nRUA:\t");
            rua = scannerS.nextLine();
            System.out.print("\nCIDADE:\t");
            cidade = scannerS.nextLine();
            System.out.print("\nESTADO:\t");
            estado = scannerS.nextLine();
            System.out.print("\nTELEFONE:\t");
            telefone = scannerS.nextLine();
            gravadora.inserirMusico(nome, descricao, genero, cep, rua, cidade, estado, telefone);
        }else{
            String dataDeFormacao;
            System.out.print("\nDATA DE FORMAÇÃO DA BANDA:\t");
            dataDeFormacao = scannerS.nextLine();
            gravadora.inserirBanda(nome, descricao, genero, dataDeFormacao);
        }
    }
    public static void mostrarCriadores(){
        System.out.println(gravadora.mostraCriadores());
    }

}