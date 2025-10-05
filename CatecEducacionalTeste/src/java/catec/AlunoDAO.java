package catec; // Declara o pacote onde a classe AlunoDAO está localizada.

import catec.Aluno; // Importa a classe Aluno.
import catec.DBConnection; // Importa a classe DBConnection para gerenciar a conexão com o banco de dados.

import java.sql.*; // Importa todas as classes do pacote java.sql para operações de banco de dados.
import java.time.LocalDate; // Importa a classe LocalDate para lidar com datas.

public class AlunoDAO { // Declara a classe pública AlunoDAO (Data Access Object).

    public boolean salvarAluno(Aluno aluno) { // Método para salvar um aluno no banco de dados.
        String sql = "INSERT INTO alunos (nome, email, senha, data_nascimento) VALUES (?, ?, ?, ?)"; // Define a query SQL para inserção.
        try (Connection conn = DBConnection.getConnection(); // Obtém uma conexão com o banco de dados usando try-with-resources.
            PreparedStatement pstmt = conn.prepareStatement(sql)) { // Cria um PreparedStatement para executar a query.
             
            pstmt.setString(1, aluno.getNome()); // Define o primeiro parâmetro (nome) da query.
            pstmt.setString(2, aluno.getEmail()); // Define o segundo parâmetro (email) da query.
            pstmt.setString(3, aluno.getSenha()); // Define o terceiro parâmetro (senha) da query.
             
            // Tratamento para data nula (linha ~21)
            if (aluno.getDataNascimento() != null) { // Verifica se a data de nascimento não é nula.
                pstmt.setDate(4, java.sql.Date.valueOf(aluno.getDataNascimento())); // Define o quarto parâmetro (data de nascimento) como um java.sql.Date.
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);  // Passe null para o banco // Define o quarto parâmetro como NULL no banco de dados se a data for nula.
            }
             
            int rowsAffected = pstmt.executeUpdate(); // Executa a query de inserção e retorna o número de linhas afetadas.
            return rowsAffected > 0; // Retorna true se pelo menos uma linha foi afetada (inserção bem-sucedida).
        } catch (SQLException e) { // Captura exceções SQL.
            e.printStackTrace();  // Log o erro (use um logger em produção) // Imprime o stack trace do erro (para depuração, usar logger em produção).
            return false; // Retorna false em caso de erro.
        }
     }

    public Aluno buscarAlunoPorEmail(String email) { // Método para buscar um aluno pelo e-mail.
    String sql = "SELECT * FROM alunos WHERE email = ?"; // Define a query SQL para seleção.
    try (Connection conn = DBConnection.getConnection(); // Obtém uma conexão com o banco de dados.
         PreparedStatement pstmt = conn.prepareStatement(sql)) { // Cria um PreparedStatement.
        
        pstmt.setString(1, email); // Define o parâmetro (email) da query.
        ResultSet rs = pstmt.executeQuery(); // Executa a query e obtém o ResultSet.
        
        if (rs.next()) { // Se houver um resultado (aluno encontrado).
            String nome = rs.getString("nome"); // Obtém o nome do aluno do ResultSet.
            String senha = rs.getString("senha"); // Obtém a senha do aluno do ResultSet.
            
            Date dataSql = rs.getDate("data_nascimento"); // Obtém a data de nascimento como java.sql.Date.
            LocalDate dataNascimento = null; // Inicializa LocalDate como nulo.
            if (dataSql != null) { // Se a data do banco não for nula.
                dataNascimento = dataSql.toLocalDate(); // Converte java.sql.Date para LocalDate.
            }
            
            return new Aluno(nome, email, senha, dataNascimento); // Retorna um novo objeto Aluno com os dados encontrados.
        }
    } catch (SQLException e) { // Captura exceções SQL.
        e.printStackTrace(); // Imprime o stack trace do erro.
    }
    return null; // Retorna null se nenhum aluno for encontrado ou ocorrer um erro.
}
}