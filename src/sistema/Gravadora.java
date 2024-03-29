package sistema;

import dados.*;

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

public class Gravadora {
    private final ArrayList<Banda> bandas = new ArrayList<>();
    private final ArrayList<Musico> musicos = new ArrayList<>();
    private final ArrayList<Disco> discos = new ArrayList<>();
    private final ArrayList<Instrumento> instrumentos = new ArrayList<>();
    private final ArrayList<Musica> musicas = new ArrayList<>();
    private final ArrayList<Produtor> produtores = new ArrayList<>();

    public static Properties getProperties() {
        String PROPERTIES_FILE = "database.properties";
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
    public static Connection getConnection(Properties properties) throws ClassNotFoundException, SQLException {
        try{
            Class.forName("org.postgresql.Driver");

            // Estabelecer a conexão com o banco de dados PostgreSQL
            String url = properties.getProperty("DB_PATH");
            String username = properties.getProperty("DB_USER");
            String password = properties.getProperty("DB_PASSWORD");
            return DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    public static Statement getStatement(Connection conn) throws SQLException {
        return conn.createStatement();
    }
    public static ResultSet getResultSet(Statement stmt, String sql) throws SQLException {
        return stmt.executeQuery(sql);
    }

    public void inserirMusico(String nome, String descricao, String genero, String cep, String rua, String cidade, String estado, String telefone){
        Musico musico = new Musico(nome, descricao, genero, cep, rua, cidade, estado, telefone);
        musicos.add(musico);
    }
    public void inserirBanda(String nome, String descricao, String genero, String dataDeFormacao){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate localDate = LocalDate.parse(dataDeFormacao, formatter);
            Banda banda = new Banda(nome, descricao, genero, localDate);
            bandas.add(banda);
        } catch (Exception e) {
            System.out.println("Error parsing the date: " + e.getMessage());
        }
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            assert connection != null;
            Statement statement = getStatement(connection);
            String sql = "INSERT INTO criador(nome, descricao, genero) VALUES ('Guilherme', 'desc1', 'M')";
            ResultSet resultSet = getResultSet(statement, sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println("ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void inserirDisco(String dataLancamento, double preco, int platinas, String titulo, String formato, String descricao, String genero){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate localDate = LocalDate.parse(dataLancamento, formatter);
            Disco disco = new Disco(localDate,preco,platinas,titulo,formato,descricao,genero);
            discos.add(disco);
        } catch (Exception e) {
            System.out.println("Error parsing the date: " + e.getMessage());
        }
    }
    public void inserirInstrumento(String marca, String tipo, String nome){
        Instrumento instrumento = new Instrumento(marca, tipo, nome);
        instrumentos.add(instrumento);
    }
    public void inserirMusica(float duracao, int faixa, String autores, String titulo, String letra){
        Musica musica = new Musica(duracao, faixa, autores, titulo, letra);
        musicas.add(musica);
    }
    public void inserirProdutor(String nome, String biografia){
        Produtor produtor = new Produtor(nome, biografia);
        produtores.add(produtor);
    }
    public String mostraCriadores(){return "Bandas="+bandas+"\nMúsicos="+musicos;}
    public String mostrarDiscos(){return "Discos="+discos;}
    public String mostrarInstrumentos(){return "Instrumentos="+instrumentos;}
    public String mostrarMusicas(){return "Músicas=" + musicas;}
    public String mostrarProdutores(){return "Produtores=" + produtores;}
    @Override
    public String toString() {
        return "Gravadora{" +
                "bandas=" + bandas +
                ", musicos=" + musicos +
                ", discos=" + discos +
                ", instrumentos=" + instrumentos +
                ", musicas=" + musicas +
                ", produtores=" + produtores +
                '}';
    }
}
