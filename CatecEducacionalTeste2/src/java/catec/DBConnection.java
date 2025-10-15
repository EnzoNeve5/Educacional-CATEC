package catec; // Declara o pacote onde a classe DBConnection está localizada.

import java.sql.Connection; // Importa a interface Connection para gerenciar a conexão com o banco de dados.
import java.sql.DriverManager; // Importa a classe DriverManager para obter conexões.
import java.sql.SQLException; // Importa a exceção SQLException para lidar com erros de banco de dados.

public class DBConnection { // Declara a classe pública DBConnection para gerenciar a conexão com o banco de dados.
    
    private static final String URL = "jdbc:postgresql://localhost:5432/seu_banco_de_dados"; // Define a URL de conexão com o banco de dados PostgreSQL.
    private static final String USER = "postgres"; // Define o nome de usuário para a conexão com o banco de dados.
    private static final String PASSWORD = "cwarim199"; // Define a senha para a conexão com o banco de dados.
    
    public static Connection getConnection() throws SQLException { // Método estático para obter uma conexão com o banco de dados.
        try {
            Class.forName("org.postgresql.Driver"); // Carrega o driver JDBC do PostgreSQL.
            return DriverManager.getConnection(URL, USER, PASSWORD); // Retorna uma conexão usando a URL, usuário e senha definidos.
        }
        
        catch (ClassNotFoundException e) { // Captura a exceção se o driver JDBC não for encontrado.
            throw new SQLException("Driver JDBC do PostgreSQL não encontrado", e); // Lança uma nova SQLException com uma mensagem descritiva.
        }
    }
    
    public static void closeConnection(Connection conn) { // Método estático para fechar uma conexão com o banco de dados.
        if (conn != null) { // Verifica se a conexão não é nula.
            try {
                conn.close(); // Tenta fechar a conexão.
            }
            
            catch (SQLException e) { // Captura exceções SQL que podem ocorrer ao fechar a conexão.
                e.printStackTrace(); // Imprime o stack trace do erro.
            }
        }
    }
}