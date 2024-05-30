package sistema;

import dados.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Gravadora {
    private final ArrayList<Banda> bandas = new ArrayList<>();
    private final ArrayList<Musico> musicos = new ArrayList<>();
    private ArrayList<Disco> discos = new ArrayList<>();
    private ArrayList<Instrumento> instrumentos = new ArrayList<>();
    private ArrayList<Musica> musicas = new ArrayList<>();
    private ArrayList<Produtor> produtores = new ArrayList<>();


    private ArrayList<Musica> getMusicas(){
        ArrayList<Musica> musicas1 = new ArrayList<>();
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
            assert connection != null;
            Statement statement = getStatement(connection);
            String sql = "SELECT * FROM Musica";
            ResultSet resultSet = getResultSet(statement, sql);
            while (resultSet.next()) {
                Musica musica = new Musica(
                        resultSet.getFloat("duracao"),
                        resultSet.getInt("faixa"),
                        resultSet.getString("autores"),
                        resultSet.getString("titulo"),
                        resultSet.getString("letra")
                );
                int identificador = resultSet.getInt("id");
                musica.setId(identificador);
                musicas1.add(musica);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return musicas1;
    }
    public void getRelacao(String function){
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
            assert connection != null;
            Statement statement = getStatement(connection);
            String sql = "SELECT * FROM " + function;
            ResultSet resultSet = getResultSet(statement, sql);
            while (resultSet.next()) {
                String nome1 = resultSet.getString("nome1");
                String nome2 = resultSet.getString("nome2");
                long identificador = resultSet.getLong("identificador");
                long id = resultSet.getLong("id");
                System.out.println("Nome 1:\t" + nome1 + "\tcom id:\t" +  identificador + "\nNome 2:\t" + nome2 + "\tcom id:\t" + id);
                System.out.println("---------------------------------------------------------------------");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
    private ArrayList<Instrumento> getInstrumentos(){
        ArrayList<Instrumento> instrumentos1 = new ArrayList<>();
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
            assert connection != null;
            Statement statement = getStatement(connection);
            String sql = "SELECT * FROM Instrumento";
            ResultSet resultSet = getResultSet(statement, sql);
            while (resultSet.next()) {
                Instrumento instrumento = new Instrumento(
                        resultSet.getString("marca"),
                        resultSet.getString("tipo"),
                        resultSet.getString("nome")
                );
                int identificador = resultSet.getInt("codigointerno");
                instrumento.setCodigoInterno(identificador);
                instrumentos1.add(instrumento);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return instrumentos1;
    }
    private ArrayList<Disco> getDiscos() {
        ArrayList<Disco> discos = new ArrayList<>();
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
            assert connection != null;
            Statement statement = getStatement(connection);
            String sql = "SELECT * FROM Disco";
            ResultSet resultSet = getResultSet(statement, sql);
            while (resultSet.next()) {
                Disco disco = new Disco(
                        resultSet.getDate("dataLancamento").toLocalDate(),
                        resultSet.getFloat("preco"),
                        resultSet.getInt("platinas"),
                        resultSet.getString("titulo"),
                        resultSet.getString("formato"),
                        resultSet.getString("descricao"),
                        resultSet.getString("descricao")
                );
                int identificador = resultSet.getInt("identificador");
                disco.setIdentificador(identificador);
                discos.add(disco);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return discos;
    }

    private ArrayList<Produtor> getProdutor() {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
            assert connection != null;
            Statement statement = getStatement(connection);
            String sql = "SELECT * FROM produtor";
            ResultSet resultSet = getResultSet(statement, sql);
            while (resultSet.next()) {
                Produtor produtor = new Produtor(
                        resultSet.getString("nome"),
                        resultSet.getString("biografia")
                );
                int identificador = resultSet.getInt("id");
                produtor.setIdentificador(identificador);
                produtores.add(produtor);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return produtores;
    }

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
        try {
            Class.forName("org.postgresql.Driver");

            // Estabelecer a conexão com o banco de dados PostgreSQL
            String url = properties.getProperty("DB_PATH");
            String username = properties.getProperty("DB_USER");
            String password = properties.getProperty("DB_PASSWORD");
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
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

    public void inserirMusico(String nome, String descricao, String genero, String cep, String rua, String cidade, String estado, String telefone) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Prepare the SQL insert statement
            String insertSQL = "INSERT INTO MUSICO (nome, descricao, genero, cep, rua, cidade, estado, telefone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set the values for the prepared statement
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, descricao);
            preparedStatement.setString(3, genero);
            preparedStatement.setString(4, cep);
            preparedStatement.setString(5, rua);
            preparedStatement.setString(6, cidade);
            preparedStatement.setString(7, estado);
            preparedStatement.setString(8, telefone);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // Optionally, you can retrieve the generated keys if the table has an auto-increment column
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                System.out.println("Inserted Musico ID: " + generatedId);
            }

            // Add the new Musico to the in-memory list (assuming musicos is defined elsewhere)
            Musico musico = new Musico(nome, descricao, genero, cep, rua, cidade, estado, telefone);
            musicos.add(musico);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the connection and statement
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    public void inserirBanda(String nome, String descricao, String genero, String dataDeFormacao) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Parse the date
            assert connection != null;
            String insertSQL = "INSERT INTO BANDA (nome, descricao, genero, dataDeFormacao) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            LocalDate localDate = LocalDate.parse(dataDeFormacao, formatter);

            // Set the values for the prepared statement
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, descricao);
            preparedStatement.setString(3, genero);
            preparedStatement.setDate(4, java.sql.Date.valueOf(localDate));

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // Optionally, you can retrieve the generated keys if the table has an auto-increment column
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                System.out.println("Inserted Banda ID: " + generatedId);
            }

            // Add the new Banda to the in-memory list (assuming bandas is defined elsewhere)
            Banda banda = new Banda(nome, descricao, genero, localDate);
            bandas.add(banda);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the connection and statement
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    public void inserirDisco(String dataLancamento, double preco, int platinas, String titulo, String formato, String descricao, String genero){
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Parse the date
            LocalDate localDate = LocalDate.parse(dataLancamento, formatter);

            // Prepare the SQL insert statement
            String insertSQL = "INSERT INTO DISCO (dataLancamento, preco, platinas, titulo, formato, descricao, genero) VALUES (?, ?, ?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set the values for the prepared statement
            preparedStatement.setDate(1, java.sql.Date.valueOf(localDate));
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, platinas);
            preparedStatement.setString(4, titulo);
            preparedStatement.setString(5, formato);
            preparedStatement.setString(6, descricao);
            preparedStatement.setString(7, genero);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // Optionally, you can retrieve the generated keys if the table has an auto-increment column
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                System.out.println("Inserted Disco ID: " + generatedId);
            }

            // Add the new Disco to the in-memory list (assuming discos is defined elsewhere)
            Disco disco = new Disco(localDate, preco, platinas, titulo, formato, descricao, genero);
            discos.add(disco);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the connection and statement
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    public void inserirInstrumento(String marca, String tipo, String nome) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Prepare the SQL insert statement
            String insertSQL = "INSERT INTO INSTRUMENTO (marca, tipo, nome) VALUES (?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set the values for the prepared statement
            preparedStatement.setString(1, marca);
            preparedStatement.setString(2, tipo);
            preparedStatement.setString(3, nome);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // Optionally, you can retrieve the generated keys if the table has an auto-increment column
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                System.out.println("Inserted Instrumento ID: " + generatedId);
            }

            // Add the new Instrumento to the in-memory list (assuming instrumentos is defined elsewhere)
            Instrumento instrumento = new Instrumento(marca, tipo, nome);
            instrumentos.add(instrumento);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the connection and statement
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    public void inserirMusica(float duracao, int faixa, String autores, String titulo, String letra) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Prepare the SQL insert statement
            String insertSQL = "INSERT INTO MUSICA (duracao, faixa, autores, titulo, letra) VALUES (?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set the values for the prepared statement
            preparedStatement.setFloat(1, duracao);
            preparedStatement.setInt(2, faixa);
            preparedStatement.setString(3, autores);
            preparedStatement.setString(4, titulo);
            preparedStatement.setString(5, letra);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // Optionally, you can retrieve the generated keys if the table has an auto-increment column
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                System.out.println("Inserted Musica ID: " + generatedId);
            }

            // Add the new Musica to the in-memory list (assuming musicas is defined elsewhere)
            Musica musica = new Musica(duracao, faixa, autores, titulo, letra);
            musicas.add(musica);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the connection and statement
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public void inserirProdutor(String nome, String biografia) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Prepare the SQL insert statement
            String insertSQL = "INSERT INTO PRODUTOR (nome, biografia) VALUES (?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set the values for the prepared statement
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, biografia);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // Optionally, you can retrieve the generated keys if the table has an auto-increment column
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                System.out.println("Inserted Produtor ID: " + generatedId);
            }

            // Add the new Produtor to the in-memory list (assuming produtores is defined elsewhere)
            Produtor produtor = new Produtor(nome, biografia);
            produtores.add(produtor);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the connection and statement
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    public String mostraCriadores() {
        Properties properties = getProperties();
        Connection connection = null;

        // Clear existing lists
        bandas.clear();
        musicos.clear();

        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            assert connection != null;
            Statement statement = connection.createStatement();

            // Retrieve Bandas
            String selectBandasSQL = "SELECT nome, descricao, genero, dataDeFormacao FROM BANDA";
            ResultSet rsBandas = statement.executeQuery(selectBandasSQL);
            while (rsBandas.next()) {
                String nome = rsBandas.getString("nome");
                String descricao = rsBandas.getString("descricao");
                String genero = rsBandas.getString("genero");
                LocalDate dataDeFormacao = rsBandas.getDate("dataDeFormacao").toLocalDate();
                Banda banda = new Banda(nome, descricao, genero, dataDeFormacao);
                bandas.add(banda);
            }

            // Retrieve Musicos
            String selectMusicosSQL = "SELECT nome, descricao, genero, cep, rua, cidade, estado, telefone FROM MUSICO";
            ResultSet rsMusicos = statement.executeQuery(selectMusicosSQL);
            while (rsMusicos.next()) {
                String nome = rsMusicos.getString("nome");
                String descricao = rsMusicos.getString("descricao");
                String genero = rsMusicos.getString("genero");
                String cep = rsMusicos.getString("cep");
                String rua = rsMusicos.getString("rua");
                String cidade = rsMusicos.getString("cidade");
                String estado = rsMusicos.getString("estado");
                String telefone = rsMusicos.getString("telefone");
                Musico musico = new Musico(nome, descricao, genero, cep, rua, cidade, estado, telefone);
                musicos.add(musico);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the connection
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }

        // Build the result string

        return "Bandas=" + bandas + "\n" +
                "Músicos=" + musicos + "\n";
    }
    public String mostrarDiscos(){
        discos.clear();
        discos = getDiscos();
        int i = 0;
        String string_discos  = "";
        for (Disco disco : discos) {
            string_discos += disco;
        }
        return "Discos:\n" + string_discos;
    }
    public String mostrarInstrumentos(){
        instrumentos.clear();
        instrumentos = getInstrumentos();
        String string_instrumentos  = "";
        for (Instrumento instrumento : instrumentos) {
            string_instrumentos += instrumento;
        }
        return "Instrumentos:\n" + string_instrumentos;
    }
    public String mostrarMusicas(){
        musicas.clear();
        musicas = getMusicas();
        String string_musicas  = "";
        for (Musica musica : musicas) {
            string_musicas += musica;
        }
        return "Musicas:\n" + string_musicas;
    }
    public String mostrarProdutores(){
        produtores.clear();
        produtores = getProdutor();
        String string_produtores  = "";
        for (Produtor produtor : produtores) {
            string_produtores += produtor;
        }
        return "Produtores:\n" + string_produtores;
    }

    public void produzirDisco(long idDisco, long idProdutor) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String insertSQL = "INSERT INTO Produzir (idDisco, idProdutor) VALUES (?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setLong(1, idDisco);
            preparedStatement.setLong(2, idProdutor);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    public void tocar(long idInstrumento, long idMusico) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String insertSQL = "INSERT INTO Tocar (idinstrumento, idmusico) VALUES (?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setLong(1, idInstrumento);
            preparedStatement.setLong(2, idMusico);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    public void participar(long idMusica, long idCriador) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String insertSQL = "INSERT INTO participacao (idMusica, idCriador) VALUES (?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setLong(1, idMusica);
            preparedStatement.setLong(2, idCriador);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    public void lancar(long idDisco, long idCriador) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String insertSQL = "INSERT INTO lancamento (idDisco, idCriador) VALUES (?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setLong(1, idDisco);
            preparedStatement.setLong(2, idCriador);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }


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

    public void integrar(long idMusico, long idBanda) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String insertSQL = "INSERT INTO integrar (idMusico, idBanda) VALUES (?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setLong(1, idMusico);
            preparedStatement.setLong(2, idBanda);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public void incluir(long idMusica, long iddisco) {
        Properties properties = getProperties();
        Connection connection = null;
        try {
            connection = getConnection(properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String insertSQL = "INSERT INTO incluir (idMusica, iddisco) VALUES (?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setLong(1, idMusica);
            preparedStatement.setLong(2, iddisco);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
