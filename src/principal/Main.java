package principal;

import sistema.Gravadora;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class Main {
    public static void main(String[] args) {

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Connection connection = null;
        try {
            // Carregar o driver JDBC para o PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Estabelecer a conexão com o banco de dados PostgreSQL
            // Ler as propriedades
            String dbUrl = props.getProperty("db.url");
            String dbUsername = props.getProperty("db.username");
            String dbPassword = props.getProperty("db.password");

            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Agora você tem uma conexão com o banco de dados PostgreSQL
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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
                    break;
                case 6:
                    System.out.println("Encerrando o programa");
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
        System.out.println("MOSTRAR DISCOS........8");
        System.out.println("MOSTRAR INSTRUMENTOS..9");
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

    public static void mostrarCriadores(){System.out.println(gravadora.mostraCriadores());}
    public static void mostrarDiscos(){System.out.println(gravadora.mostrarDiscos());}
    public static void mostrarInstrumentos(){System.out.println(gravadora.mostrarInstrumentos());}
}