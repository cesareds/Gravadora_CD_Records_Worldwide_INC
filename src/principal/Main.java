package principal;

import gui.Gui;
import sistema.Gravadora;

import javax.swing.*;
import java.util.Scanner;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Gui gui = new Gui();
        gui.setContentPane(gui.panelMain);
        gui.setTitle("GRAVADORA CD RECORDS WORLDWIDE INC");
        gui.setSize(720, 480);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                    inserirDisco();
                    break;
                case 3:
                    inserirInstrumento();
                    break;
                case 4:
                    inserirMusica();
                    break;
                case 5:
                    inserirProdutor();
                    break;
                case 6:
                    System.out.println("Encerrando o programa...");
                    break;
                case 7:
                    mostrarCriadores();
                    break;
                case 8:
                    mostrarDiscos();
                    break;
                case 9:
                    mostrarInstrumentos();
                    break;
                case 10:
                    mostrarMusicas();
                    break;
                case 11:
                    mostrarProdutores();
                    break;
                case 12:
                    produzirDisco();
                    break;
                case 13:
                    tocar();
                    break;
                case 14:
                    participar();
                    break;
                case 15:
                    lancar();
                    break;
                case 16:
                    integrar();
                    break;
                case 17:
                    incluir();
                    break;
                case 18:
                    System.out.print("\nO que voce quer ver??:\n"+ "1: producoes\n"+ "2: tocadas\n"+ "3: participacoes\n"+ "4: lançamentos"+ "\n5: integrações\n"+ "6: inclusoes\n");
                    int receba = scanner.nextInt();
                    mostrarAtrelamentos(receba);
                    break;
                default:
                    System.out.println("ESCOLHA UMA OPÇÃO VÁLIDA!!!!!!");
                    break;
            }
        }while(opcao !=6);
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scannerS = new Scanner(System.in);
    private static final Gravadora gravadora = new Gravadora();


    public static void menu(){
        System.out.println("INSERIR CRIADOR.......01");
        System.out.println("INSERIR DISCO.........02");
        System.out.println("INSERIR INSTRUMENTO...03");
        System.out.println("INSERIR MUSICA........04");
        System.out.println("INSERIR PRODUTOR......05");
        System.out.println("SAIR..................06");
        System.out.println("MOSTRAR CRIADORES.....07");
        System.out.println("MOSTRAR DISCOS........08");
        System.out.println("MOSTRAR INSTRUMENTOS..09");
        System.out.println("MOSTRAR MÚSICAS.......10");
        System.out.println("MOSTRAR PRODUTORES....11");
        System.out.println("PRODUZIR DISCO........12");
        System.out.println("TOCAR.................13");
        System.out.println("PARTICIPAÇÃO..........14");
        System.out.println("LANÇAMENTO............15");
        System.out.println("INTEGRAR..............16");
        System.out.println("INCLUIR...............17");
        System.out.println("MOSTRAR ATRELAMENTO...18");
    }
    public static void inserirCriador(){
        System.out.println("QUAL O TIPO DE CRIADOR? (BANDA: 0 | MUSICO: 1)");
        int tipo = scanner.nextInt();
        System.out.print("NOME:\t");
        String nome = scannerS.nextLine();
        System.out.print("\nGÊNERO:\t");
        String genero = scannerS.nextLine();
        System.out.print("\nDESCRIÇÃO:\t");
        String descricao = scannerS.nextLine();
        if(tipo==1){
            System.out.print("\nCEP:\t");
            String cep = scannerS.nextLine();
            System.out.print("\nRUA:\t");
            String rua = scannerS.nextLine();
            System.out.print("\nCIDADE:\t");
            String cidade = scannerS.nextLine();
            System.out.print("\nESTADO:\t");
            String estado = scannerS.nextLine();
            System.out.print("\nTELEFONE:\t");
            String telefone = scannerS.nextLine();
            gravadora.inserirMusico(nome, descricao, genero, cep, rua, cidade, estado, telefone);
        }else{
            System.out.print("\nDATA DE FORMAÇÃO DA BANDA:\t");
            String dataDeFormacao = scannerS.nextLine();
            gravadora.inserirBanda(nome, descricao, genero, dataDeFormacao);
        }
    }
    public static void inserirDisco(){
        System.out.print("\nDATA DE LANÇAMENTO DO DISCO (dd/mm/yyyy):\t");
        String dataLancamento = scannerS.nextLine();
        System.out.print("\nPREÇO:\t");
        double preco = scanner.nextDouble();
        System.out.print("\nPLATINAS:\t");
        int platinas = scanner.nextInt();
        System.out.print("\nTÍTULO:\t");
        String titulo = scannerS.nextLine();
        System.out.print("\nFORMATO(vinil|cd|vhs):\t");
        String formato = scannerS.nextLine();
        System.out.print("\nDESCRIÇÃO:\t");
        String descricao = scannerS.nextLine();
        System.out.print("\nGÊNERO:\t");
        String genero = scannerS.nextLine();
        gravadora.inserirDisco(dataLancamento,preco,platinas,titulo,formato,descricao,genero);
    }
    public static void inserirInstrumento(){
        System.out.print("\nMARCA:\t");
        String marca = scannerS.nextLine();
        System.out.print("\nTIPO:\t");
        String tipo = scannerS.nextLine();
        System.out.print("\nNOME:\t");
        String nome = scannerS.nextLine();
        gravadora.inserirInstrumento(marca, tipo, nome);
    }
    public static void inserirMusica(){
        System.out.print("\nDURAÇÃO:\t");
        float duracao = scanner.nextFloat();
        System.out.print("\nFAIXA:\t");
        int faixa = scanner.nextInt();
        System.out.print("\nAUTORES:\t");
        String autores = scannerS.nextLine();
        System.out.print("\nTÍTULO:\t");
        String titulo = scannerS.nextLine();
        System.out.print("\nLETRA:\t");
        String letra = scannerS.nextLine();
        gravadora.inserirMusica(duracao, faixa, autores, titulo, letra);
    }
    public static void inserirProdutor(){
        System.out.print("\nNOME:\t");
        String nome = scannerS.nextLine();
        System.out.println("\nBIOGRAFIA:\t");
        String biografia = scannerS.nextLine();
        gravadora.inserirProdutor(nome, biografia);
    }
    public static void produzirDisco() {
        System.out.print("\nIDDISCO:\t");
        long idDisco = scanner.nextLong();
        System.out.println("\nIDPRODUTOR:\t");
        long idProdutor = scanner.nextLong();
        gravadora.produzirDisco(idDisco, idProdutor);
    }
    public static void tocar(){
        System.out.print("\nIDINSTRUMENTO:\t");
        long idInstrumento = scanner.nextLong();
        System.out.println("\nIDMUSICO:\t");
        long idMusico = scanner.nextLong();
        gravadora.tocar(idInstrumento, idMusico);
    }
    public static void participar(){
        System.out.print("\nIDMUSICA:\t");
        long idMusica = scanner.nextLong();
        System.out.println("\nIDCRIADOR (MUSICO OU BANDA!!!):\t");
        long idCriador = scanner.nextLong();
        gravadora.participar(idMusica, idCriador);
    }
    public static void lancar(){
        System.out.print("\nIDDISCO:\t");
        long idDisco = scanner.nextLong();
        System.out.println("\nIDCRIADOR (MUSICO OU BANDA!!!):\t");
        long idCriador = scanner.nextLong();
        gravadora.lancar(idDisco, idCriador);
    }
    public static void integrar(){
        System.out.print("\nIDMUSICO:\t");
        long idMusico = scanner.nextLong();
        System.out.println("\nIDBANDA:\t");
        long idBanda = scanner.nextLong();
        gravadora.integrar(idMusico, idBanda);
    }
    public static void incluir(){
        System.out.print("\nIDMUSICA:\t");
        long idMusica = scanner.nextLong();
        System.out.println("\nIDDISCO:\t");
        long iddisco = scanner.nextLong();
        gravadora.incluir(idMusica, iddisco);
    }
    public static void mostrarAtrelamentos(int receba){
        String function = new String();
        switch (receba){
            case 1:
                function = "mostrarProduzir()";
                break;
            case 2:
                function = "mostrarTocar()";
                break;
            case 3:
                function = "mostrarParticipacao()";
                break;
            case 4:
                function = "mostrarLancamento()";
                break;
            case 5:
                function = "mostrarIntegramentacao()";
                break;
            case 6:
                function = "mostrarIncluir()";
                break;
        }
        gravadora.getRelacao(function);
    }

    public static void mostrarCriadores(){System.out.println(gravadora.mostraCriadores());}
    public static void mostrarDiscos(){System.out.println(gravadora.mostrarDiscos());}
    public static void mostrarInstrumentos(){System.out.println(gravadora.mostrarInstrumentos());}
    public static void mostrarMusicas(){System.out.println(gravadora.mostrarMusicas());}
    public static void mostrarProdutores(){System.out.println(gravadora.mostrarProdutores());}

}