package catec; // Declara o pacote onde a classe ProfessorDAO está localizada.

import catec.Professor; // Importa a classe Professor.
import catec.DBConnection; // Importa a classe DBConnection para gerenciar a conexão com o banco de dados.

import java.sql.*; // Importa todas as classes do pacote java.sql para operações de banco de dados.
import java.time.LocalDate; // Importa LocalDate para manuseio de datas.

public class ProfessorDAO { // Declara a classe pública ProfessorDAO (Data Access Object).

    public void salvarProfessor(Professor professor) throws SQLException { // Método para salvar um professor no banco de dados, lançando SQLException.
        String sql = "INSERT INTO professores (nome, instituicao, email, senha, data_nascimento, area_atuacao) VALUES (?, ?, ?, ?, ?, ?)"; // Define a query SQL para inserção.
        Connection conn = null; // Declara a variável de conexão.
        PreparedStatement stmt = null; // Declara a variável PreparedStatement.
        
        try {
            conn = DBConnection.getConnection(); // Obtém uma conexão com o banco de dados.
            stmt = conn.prepareStatement(sql); // Cria um PreparedStatement para executar a query.
            stmt.setString(1, professor.getNome()); // Define o primeiro parâmetro (nome) da query.
            stmt.setString(2, professor.getInstituicao()); // Define o segundo parâmetro (instituicao) da query.
            stmt.setString(3, professor.getEmail()); // Define o terceiro parâmetro (email) da query.
            stmt.setString(4, professor.getSenha()); // Define o quarto parâmetro (senha) da query (com lembrete para hash).
            
            // Verifica se dataNascimento é nula antes de definir
            if (professor.getDataNascimento() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(professor.getDataNascimento())); // Converte LocalDate para java.sql.Date
            }
            
            else {
                stmt.setDate(5, null); // Define como nulo no banco de dados
            }
            
            stmt.setString(6, professor.getAreaAtuacao()); // Define o sexto parâmetro (área de atuação) da query.
            stmt.executeUpdate(); // Executa a query de inserção.
        }
        
        finally { // Bloco finally para garantir o fechamento dos recursos.
            if (stmt != null) stmt.close(); // Fecha o PreparedStatement se não for nulo.
            DBConnection.closeConnection(conn); // Fecha a conexão com o banco de dados.
        }
    }

    public Professor buscarProfessorPorEmail(String email) throws SQLException { // Método para buscar um professor pelo e-mail, lançando SQLException.
        String sql = "SELECT id, nome, instituicao, email, senha, data_nascimento, area_atuacao FROM professores WHERE email = ?"; // Define a query SQL para seleção.
        Connection conn = null; // Declara a variável de conexão.
        PreparedStatement stmt = null; // Declara a variável PreparedStatement.
        ResultSet rs = null; // Declara a variável ResultSet.
        Professor professor = null; // Inicializa a variável professor como nula.
        
        try {
            conn = DBConnection.getConnection(); // Obtém uma conexão com o banco de dados.
            stmt = conn.prepareStatement(sql); // Cria um PreparedStatement.
            stmt.setString(1, email); // Define o parâmetro (email) da query.
            rs = stmt.executeQuery(); // Executa a query e obtém o ResultSet.
            
            if (rs.next()) { // Se houver um resultado (professor encontrado).
                professor = new Professor(); // Cria uma nova instância de Professor.
                professor.setId(rs.getInt("id")); // Define o ID do professor.
                professor.setNome(rs.getString("nome")); // Define o nome do professor.
                professor.setInstituicao(rs.getString("instituicao")); // Define a instituição do professor.
                professor.setEmail(rs.getString("email")); // Define o e-mail do professor.
                professor.setSenha(rs.getString("senha")); // Define a senha do professor.
                
                // Correção: Usa rs.getDate() e converte para LocalDate
                java.sql.Date sqlDate = rs.getDate("data_nascimento");  // Obtém como java.sql.Date
                if (sqlDate != null) {
                    professor.setDataNascimento(sqlDate.toLocalDate());  // Converte para LocalDate
                }
                
                else {
                    professor.setDataNascimento(null);  // Define como nulo se a data no banco for nula
                }
                
                professor.setAreaAtuacao(rs.getString("area_atuacao")); // Define a área de atuação do professor.
            }
        }
        
        finally { // Bloco finally para garantir o fechamento dos recursos.
            if (rs != null) rs.close(); // Fecha o ResultSet se não for nulo.
            if (stmt != null) stmt.close(); // Fecha o PreparedStatement se não for nulo.
            DBConnection.closeConnection(conn); // Fecha a conexão com o banco de dados.
        }
        
        return professor; // Retorna o objeto Professor encontrado ou null.
    }
}